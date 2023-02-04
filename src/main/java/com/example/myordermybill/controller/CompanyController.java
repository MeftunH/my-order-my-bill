package com.example.myordermybill.controller;

import com.example.myordermybill.entity.Bill;
import com.example.myordermybill.entity.Company;
import com.example.myordermybill.repository.BillRepository;
import com.example.myordermybill.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    private final BillRepository billRepository;
    private final CompanyRepository companyRepository;

    public CompanyController(BillRepository billRepository,
                             CompanyRepository companyRepository) {
        this.billRepository=billRepository;
        this.companyRepository=companyRepository;
    }

    //That lists the companies in which the average invoices for the month of June are below 750.
    @GetMapping("/listCompaniesWithAverageInvoicesUnderAmount")
    public ResponseEntity<List<Company>> listCompaniesWithAverageInvoicesUnderAmount() {
        List<Company> companies = companyRepository.findAll();
        List<Bill> billList =companies.stream().flatMap(c -> billRepository.findBillsByCompany(c).stream())
                .toList();
        List<Bill> billListUnderAmount = billList.stream().filter(i -> i.getTotalBill().compareTo(BigDecimal.valueOf(750)) < 0)
                .toList();
        List<Company> companyListUnderAmount = billListUnderAmount.stream().map(x->x.getCompany()).collect(Collectors.toList());

        return new ResponseEntity<>(companyListUnderAmount, HttpStatus.OK);
    }
}
