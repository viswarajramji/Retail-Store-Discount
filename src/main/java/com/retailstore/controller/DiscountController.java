package com.retailstore.controller;
import com.retailstore.data.Bill;
import com.retailstore.data.Discount;
import com.retailstore.service.DiscountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/test")
    public String getInfo(){
        return "application running";
    }

    @PostMapping("/calculate")
    public ResponseEntity<Discount> calculateNetPayableAmount(@Valid @RequestBody Bill bill) {
        Discount discountResponse= discountService.calculateNetPayableAmount(bill);
        return ResponseEntity.ok(discountResponse);
    }
}
