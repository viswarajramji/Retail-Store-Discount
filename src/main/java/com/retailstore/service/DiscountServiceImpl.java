package com.retailstore.service;

import com.retailstore.constants.DiscountConstants;
import com.retailstore.model.Bill;
import com.retailstore.model.Discount;
import com.retailstore.model.Item;
import com.retailstore.model.User;
import com.retailstore.enums.ItemType;
import com.retailstore.enums.UserType;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    public Discount calculateNetPayableAmount(Bill bill) {
        BigDecimal totalAmount = calculateTotalAmount(bill.getItems());
        BigDecimal percentageDiscount = calculatePercentageDiscount(bill.getUser(), bill.getItems());
        BigDecimal bulkDiscount = calculateBulkDiscount(totalAmount);
        BigDecimal discountedAmount = totalAmount.subtract(percentageDiscount).subtract(bulkDiscount).setScale(2, RoundingMode.HALF_UP);
        return new Discount(totalAmount.toPlainString(), discountedAmount.toPlainString());
    }

    public BigDecimal calculateTotalAmount(List<Item> items) {
        return items.stream()
                .map(Item::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculatePercentageDiscount(User user , List<Item> items) {
        BigDecimal totalNonGroceryAmount = calculateTotalNonGroceryAmount(items);
        BigDecimal maxPercentageDiscount = determineMaxPercentageDiscount(user);
        return totalNonGroceryAmount.multiply(maxPercentageDiscount).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTotalNonGroceryAmount(List<Item> items) {
        return items.stream()
                .filter(item ->  !ItemType.GROCERIES.equals(item.getType()))
                .map(Item::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateBulkDiscount(BigDecimal totalAmount) {
        BigDecimal bulkDiscountCount = totalAmount.divide(BigDecimal.valueOf(100.00)).setScale(0, RoundingMode.DOWN);
        return bulkDiscountCount.multiply(BigDecimal.valueOf(DiscountConstants.PER_100_DISCOUNT)).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal determineMaxPercentageDiscount(User user) {
        if (UserType.EMPLOYEE.equals(user.getUserType())) {
            return BigDecimal.valueOf(DiscountConstants.EMPLOYEE_DISCOUNT); // 30%
        } else if (UserType.AFFILIATE.equals(user.getUserType())) {
            return BigDecimal.valueOf(DiscountConstants.AFFILIATE_DISCOUNT); // 10%
        } else if (isLongTermCustomer(user)) {
            return BigDecimal.valueOf(DiscountConstants.LOYALTY_DISCOUNT); // 5%
        }
        return BigDecimal.ZERO;
    }

    private boolean isLongTermCustomer(User user) {
        return ChronoUnit.YEARS.between(user.getJoiningDate(), LocalDate.now()) > 2;
    }
}
