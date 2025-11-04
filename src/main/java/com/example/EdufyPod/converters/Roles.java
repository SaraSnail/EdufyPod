package com.example.EdufyPod.converters;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class Roles {

    public static List<String> getRoles(Authentication authentication) {
        List<String> roles = new java.util.ArrayList<>(authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("ROLE_"))
                .toList());

        roles.replaceAll(role -> role.replaceAll("ROLE_", ""));

        return roles;
    }
}
