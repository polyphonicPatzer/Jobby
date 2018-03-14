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
public class Candidate implements UserDetails {
    @Id
    @Column(name="candidateId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 30)
    @Column(name="candidateName")
    private String name;

    @NaturalId
    @NotNull
    @Size(min = 5, max = 30)
    @Column(unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name="resumeId")
    private Resume resume;

    @OneToOne
    @JoinColumn(name="profilePicId")
    private ProfilePic profilePic;

    @NotNull
    @Column(length = 100)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public ProfilePic getProfilePic() { return profilePic; }

    public void setProfilePic(ProfilePic profilePic) { this.profilePic = profilePic; }

    public void setPassword(String password) {
        this.password = password;
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
