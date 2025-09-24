package com.springboot.member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryMemberService implements MemberService {
    private UserDetailsManager userDetailsManager;
    private PasswordEncoder passwordEncoder;

    public InMemoryMemberService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member createMember(Member member) {
        List<GrantedAuthority> authorities = createAuthorities(Member.MemberRole.ROLE_USER.name());

        String encryptedPassword = passwordEncoder.encode(member.getPassword()); // pw 암호화
        UserDetails userDetails = new User(member.getEmail(), encryptedPassword, authorities);

        userDetailsManager.createUser(userDetails);
        return member;
    }

    private List<GrantedAuthority> createAuthorities(String... roles) {
        return Arrays.stream(roles)
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }
}
