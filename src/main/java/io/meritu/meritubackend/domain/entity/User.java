package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.UserRSDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "AppUser")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Integer userRole;
    @Transient
    private UserRole userRoleTransiente;
    @OneToOne(cascade = CascadeType.ALL)
    private Employee employee;
    private boolean isActive;

    public User() {
    }

    public User(String username, String password, UserRole userRole, Employee employee) {
        this.username = username;
        this.password = password;
        this.userRoleTransiente = userRole;
        this.isActive = true;
        this.employee = employee;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    @PostLoad
    void postLoad() {
        if (userRole != null) {
            this.userRoleTransiente = UserRole.fromValue(userRole);
        }
    }

    @PrePersist
    void prePersist() {
        if (userRoleTransiente != null) {
            this.userRole = userRoleTransiente.getValue();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRoleTransiente.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }

    public Long getId() {
        return id;
    }

    public Integer getRoleEnum() {
        return userRole;
    }

    public Employee getEmployee() {
        return employee;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserRSDTO toDTO() {
        return new UserRSDTO(id,
                username,
                userRoleTransiente,
                employee.toDTO(),
                isActive);
    }
}
