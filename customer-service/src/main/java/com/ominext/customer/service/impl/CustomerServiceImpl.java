package com.ominext.customer.service.impl;

import com.ominext.common.model.request.CustomerRequest;
import com.ominext.common.model.response.CustomerResponse;
import com.ominext.customer.entity.Customer;
import com.ominext.customer.feign.CartClient;
import com.ominext.customer.repository.CustomerRepository;
import com.ominext.customer.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CartClient cartClient;

    public CustomerServiceImpl(CustomerRepository customerRepository, CartClient cartClient) {
        this.customerRepository = customerRepository;
        this.cartClient = cartClient;
    }

    @Override
    public CustomerResponse getById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        CustomerResponse response = new CustomerResponse();
        response.setId(id);
        response.setUsername("pick");
        response.setPassword("gunny");
        response.setPhone("0367201994");
        response.setRole("MEMBER");
        System.out.println("Response = " + response);
        return response;
    }

    @Override
    public void save(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setUsername(request.getUsername());
        customer.setPassword(request.getPassword());
        customer.setAddress(request.getAddress());
        customer.setGmail(request.getGmail());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhone(request.getPhone());
        System.out.println("save success");
    }

    @Override
    public CustomerResponse getUsername(String username) {
        try {
            CustomerResponse response = new CustomerResponse();
            response.setUsername("pick");
            response.setPassword("gunny");
            response.setPhone("0367201994");
            response.setRole("ROLE_MEMBER");
            System.out.println("Cart Response = " + cartClient.getCartInfo(1L));
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
