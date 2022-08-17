package com.ominext.customer.feign;

import com.ominext.common.model.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service")
public interface CartClient {

    @GetMapping("/carts/{id}")
    CustomerResponse getCartInfo(@PathVariable Long id);
}
