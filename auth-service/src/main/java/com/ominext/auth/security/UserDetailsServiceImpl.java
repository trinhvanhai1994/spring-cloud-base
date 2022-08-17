package com.ominext.auth.security;

import com.ominext.auth.feign.customer.CustomerClient;
import com.ominext.auth.repository.AccountRepository;
import com.ominext.common.model.response.CustomerResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final BCryptPasswordEncoder encoder;
    private final CustomerClient customerClient;
    private final AccountRepository accountRepository;

    public UserDetailsServiceImpl(BCryptPasswordEncoder encoder, CustomerClient customerClient, AccountRepository accountRepository) {
        this.encoder = encoder;
        this.customerClient = customerClient;
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            CustomerResponse customerResponse = customerClient.findByUsername(username);
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(customerResponse.getRole());

            String password = encoder.encode(customerResponse.getPassword());
            return new User(customerResponse.getUsername(), password, grantedAuthorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }
    }
}