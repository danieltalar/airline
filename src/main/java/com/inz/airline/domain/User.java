package com.inz.airline.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@NodeEntity
@Data
@NoArgsConstructor
public class User  implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String first_name;
    private String last_name;
    private String phone_number;
    private Integer age;
    private String nationality;
    private String username;
    private LocalDate dateOfBirth;


    @JsonIgnore
    private String password;

    private List<com.inz.airline.model.Authority> authorities;




    public User(String email, String first_name, String last_name, String phone_number, Integer age, String nationality) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.age = age;
        this.nationality = nationality;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
