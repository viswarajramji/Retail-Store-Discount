package com.retailstore.service;

import com.retailstore.constants.DiscountConstants;
import com.retailstore.data.Bill;
import com.retailstore.data.Discount;
import com.retailstore.data.Item;
import com.retailstore.data.User;
import com.retailstore.enums.ItemType;
import com.retailstore.enums.UserType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DiscountService {

    public Discount calculateNetPayableAmount(Bill bill) {
        double totalAmount = calculateTotalAmount(bill.getItems());
        double percentageDiscount = calculatePercentageDiscount(bill.getUser(), bill.getItems());
        double bulkDiscount = calculateBulkDiscount(totalAmount);
        double discountedAmount = totalAmount - percentageDiscount - bulkDiscount;
        return new Discount(totalAmount, discountedAmount);
    }

    private double calculateTotalAmount(List<Item> items) {
        return items.stream()
                .mapToDouble(Item::getTotalPrice)
                .sum();
    }

    private double calculateTotalNonGroceryAmount(List<Item> items) {
        return items.stream()
                .filter(item -> !ItemType.GROCERIES.equals(item.getType()))
                .mapToDouble(Item::getTotalPrice)
                .sum();
    }

    private double calculatePercentageDiscount(User user,List<Item> items) {
        double totalNonGroceryAmount = calculateTotalNonGroceryAmount(items);
        double discountRate = determineMaxPercentageDiscount(user);
        return totalNonGroceryAmount * discountRate;
    }

    private double calculateBulkDiscount(double totalAmount) {
        return Math.floor(totalAmount / 100) * DiscountConstants.PER_100_DISCOUNT;
    }

    private double determineMaxPercentageDiscount(User user) {
        if (UserType.EMPLOYEE.equals(user.getUserType())) {
            return DiscountConstants.EMPLOYEE_DISCOUNT; // 30%
        } else if (UserType.AFFILIATE.equals(user.getUserType())) {
            return DiscountConstants.AFFILIATE_DISCOUNT; // 10%
        } else if (ChronoUnit.YEARS.between(user.getJoiningDate(), LocalDate.now()) > 2) {
            return DiscountConstants.LOYALTY_DISCOUNT; // 5%
        }
        return 0;
    }
}
