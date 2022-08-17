package com.ominext.cart.controller;

import com.ominext.common.model.response.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
@Slf4j
public class CartController {

    @Value("${message}")
    private String message;

    @GetMapping("/message")
    public String getMessage() {
        return message;
    }

    @GetMapping("/{id}")
    public CustomerResponse getInfo() {
        try {
//            String s = message.split(",")[1];
//            System.out.println(s);
            log.info("cart");
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setFirstName(message);
            return customerResponse;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
//        return null;
    }
}
