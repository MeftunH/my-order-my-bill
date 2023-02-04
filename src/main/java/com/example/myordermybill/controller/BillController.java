package com.example.myordermybill.controller;

import com.example.myordermybill.request.BillSave;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/bill")
public class BillController {
    @PostMapping("/create")
    public void createBill(@RequestBody BillSave billSave) {

    }
}
