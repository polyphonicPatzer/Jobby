package com.capstone.jobby.model;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class Company implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="companyID")
    private Long id;

    @NotNull
    @Size(min = 3, max = 40)
    @Column(name="companyName", unique = true)
    private String name;

    @NaturalId
    @NotNull
    @Size(min = 5, max = 30)
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(min = 3, max = 30)
    @Column
    private String city;

    @NotNull
    @Size(min = 3, max = 20)
    @Column
    private String state;

    @NotNull
    @Column(length = 100)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public void setPassword (String Password) {
        this.password = Password;
    }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }


    //OVERRIDE METHODS

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add((new SimpleGrantedAuthority(role.getName())));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
