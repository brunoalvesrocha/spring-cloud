package com.techprimers.security.securitydbexample.service;

import com.techprimers.security.securitydbexample.model.CustomUserDetails;
import com.techprimers.security.securitydbexample.model.Users;
import com.techprimers.security.securitydbexample.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author brunorocha
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = usersRepository.findByName(username);
        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username not fond"));

        return optionalUsers.map(CustomUserDetails::new).get();
    }
}
