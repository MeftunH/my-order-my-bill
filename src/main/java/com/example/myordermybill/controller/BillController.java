package com.example.myordermybill.controller;

import com.example.myordermybill.entity.Bill;
import com.example.myordermybill.entity.Company;
import com.example.myordermybill.entity.User;
import com.example.myordermybill.repository.BillRepository;
import com.example.myordermybill.repository.CompanyRepository;
import com.example.myordermybill.repository.UserRepository;
import com.example.myordermybill.request.BillSave;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Date date = Calendar.getInstance().getTime();
        if (company.isPresent() && user.isPresent()) {
            _bill.setCompany(company.get());
            _bill.setUser(user.get());
            _bill.setTotalBill(billSave.getTotal());
            _bill.setCreatedDate(date);
            billRepository.save(_bill);
        }
    }
    @GetMapping("/totalAmountOfInvoicesInJune")
    public ResponseEntity<BigDecimal> totalAmountOfInvoicesInJune() {
        List<User> users = userRepository.findAll();
        List<Bill> billList =users.stream().flatMap(c -> billRepository.findBillsByUser(c).stream())
                .toList();
        BigDecimal totalBillAmount= billList.stream().map(x->x.getTotalBill()).reduce(BigDecimal.ZERO.stripTrailingZeros(), BigDecimal::add);

        return new ResponseEntity<>(totalBillAmount, HttpStatus.OK);
    }

    @GetMapping("/listInvoicesOverTheAmount")
    public ResponseEntity<List<Bill>> listInvoicesOverTheAmount() {
        List<User> users = userRepository.findAll();
        List<Bill> billList =users.stream().flatMap(c -> billRepository.findBillsByUser(c).stream())
                .toList();
        List<Bill> billListOverAmount = billList.stream().filter(i -> i.getTotalBill().compareTo(BigDecimal.valueOf(1500)) > 0)
                .toList();

        return new ResponseEntity<>(billListOverAmount, HttpStatus.OK);
    }
    @GetMapping("/averageOfInvoicesOverTheAmount")
    public ResponseEntity<BigDecimal> averageOfInvoicesOverTheAmount() {
        List<User> users = userRepository.findAll();
        List<Bill> billList =users.stream().flatMap(c -> billRepository.findBillsByUser(c).stream())
                .toList();
        List<Bill> billListOverAmount = billList.stream().filter(i -> i.getTotalBill().compareTo(BigDecimal.valueOf(1500)) > 0)
                .toList();
        BigDecimal totalBillAmount= billListOverAmount.stream().map(x->x.getTotalBill()).reduce(BigDecimal.ZERO.stripTrailingZeros(), BigDecimal::add);
        BigDecimal averageBillAmount=totalBillAmount.divide(BigDecimal.valueOf(billListOverAmount.size()));

        return new ResponseEntity<>(averageBillAmount, HttpStatus.OK);
    }
    //List the names of customers with invoices under 500TL in the system
    @GetMapping("/listCustomersWithInvoicesUnderAmount")
    public ResponseEntity<List<User>> listCustomersWithInvoicesUnderAmount() {
        List<User> users = userRepository.findAll();
        List<Bill> billList =users.stream().flatMap(c -> billRepository.findBillsByUser(c).stream())
                .toList();
        List<Bill> billListUnderAmount = billList.stream().filter(i -> i.getTotalBill().compareTo(BigDecimal.valueOf(500)) < 0)
                .toList();
        List<User> userListUnderAmount = billListUnderAmount.stream().map(x->x.getUser()).collect(Collectors.toList());

        return new ResponseEntity<>(userListUnderAmount, HttpStatus.OK);
    }
}
