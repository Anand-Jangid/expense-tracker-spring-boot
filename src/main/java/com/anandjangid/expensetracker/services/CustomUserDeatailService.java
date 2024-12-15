package com.anandjangid.expensetracker.services;

import com.anandjangid.expensetracker.entities.Users;
import com.anandjangid.expensetracker.repositories.UsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomUserDeatailService implements UserDetailsService {
    private final UsersRepository usersRepository;

    public CustomUserDeatailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Users users = usersRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new UsernameNotFoundException("User with id: " + userId + " not found") );

        return new org.springframework.security.core.userdetails.User(
                users.getEmail(),
                users.getPassword(),
                users.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }
}
