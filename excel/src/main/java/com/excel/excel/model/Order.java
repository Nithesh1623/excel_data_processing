package com.excel.excel.model;

import java.security.SecureRandom;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private static String shortCode = "EX";
    private Integer orderNumber;
    private Double orderAmount;

    public Order(int orderNumber, double orderAmount) {
        this.id = SequenceGenerator(shortCode);
        this.orderNumber = orderNumber;
        this.orderAmount = orderAmount;
    }

    // need to write this as common for single model . this snippet comes here.
    public String SequenceGenerator(String shortCode) {
        int length = 15;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sequence = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sequence.append(characters.charAt(index));
        }
        String id = shortCode + sequence;
        return id.toString();
    }
}

