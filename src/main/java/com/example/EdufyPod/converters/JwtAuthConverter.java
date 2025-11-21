package com.example.EdufyPod.converters;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//ED-166-SA
@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Value("${keycloak.client-id}")
    private String podClientId;

    //ED-166-SA
    @Override
    public AbstractAuthenticationToken convert(@Nonnull Jwt source) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                Stream.concat(
                        jwtGrantedAuthoritiesConverter.convert(source).stream(),
                        extractRoles(source).stream()
                ),
                    extractRealmRoles(source).stream()
                ).collect(Collectors.toSet()
        );

        return new JwtAuthenticationToken(source, authorities);
    }

    //ED-166-SA
    private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt) {
        Map<String, Object> resourceAccess;
        Map<String, Object> resources;
        Collection<String> resourceRoles;

        if(!jwt.hasClaim("resource_access")) {
            return Set.of();
        }

        resourceAccess = jwt.getClaimAsMap("resource_access");

        if(!resourceAccess.containsKey(podClientId)){
            return Set.of();
        }

        resources = (Map<String, Object>) resourceAccess.get(podClientId);

        if(!resources.containsKey("roles")) {
            return Set.of();
        }

        resourceRoles = (Collection<String>) resources.get("roles");

        System.out.println("Resource Roles: "+resourceRoles);

        return resourceRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role)).collect(Collectors.toSet());

    }

    //ED-83-SA
    private Collection<? extends GrantedAuthority> extractRealmRoles(Jwt jwt) {
        Map<String, Object> realmAccess;
        Collection<String> realmRoles;

        if(!jwt.hasClaim("realm_access")) {
            return Set.of();
        }

        realmAccess = jwt.getClaimAsMap("realm_access");

        if(!realmAccess.containsKey("roles")) {
            return Set.of();
        }

        realmRoles = (Collection<String>) realmAccess.get("roles");

        System.out.println("Realm Roles: "+realmRoles);

        return realmRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role)).collect(Collectors.toSet());
    }
}
