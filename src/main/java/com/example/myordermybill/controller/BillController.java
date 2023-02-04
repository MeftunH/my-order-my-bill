package com.example.myordermybill.controller;

import com.example.myordermybill.entity.Bill;
import com.example.myordermybill.entity.Company;
import com.example.myordermybill.entity.User;
import com.example.myordermybill.repository.BillRepository;
import com.example.myordermybill.repository.CompanyRepository;
import com.example.myordermybill.repository.UserRepository;
import com.example.myordermybill.request.BillSave;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/bill")
public class BillController {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final BillRepository billRepository;

    public BillController(CompanyRepository companyRepository,
                          UserRepository userRepository,
                          BillRepository billRepository) {
        this.companyRepository=companyRepository;
        this.userRepository=userRepository;
        this.billRepository=billRepository;
    }

    @PostMapping("/create")
    public void createBill(@RequestBody BillSave billSave) {
        Optional<Company> company = companyRepository.findById(billSave.getCompanyId());
        Optional<User> user = userRepository.findById(billSave.getUserId());
        Bill _bill = new Bill();
        if (company.isPresent() && user.isPresent()) {
            _bill.setCompany(company.get());
            _bill.setUser(user.get());
            _bill.setTotalBill(billSave.getTotal());
            billRepository.save(_bill);
        }
    }
}
