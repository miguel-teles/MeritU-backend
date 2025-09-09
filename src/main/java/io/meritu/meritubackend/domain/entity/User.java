package io.meritu.meritubackend.domain.entity;

import io.meritu.meritubackend.domain.dto.UserRQDTO;
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
    private Integer role;
    @Transient
    private Role roleTransiente;
    private Integer balance;
    @OneToOne(cascade = CascadeType.ALL)
    private Employee employee;
    private boolean isActive;

    public User() {
    }

    public User(String username, String password, Role role, Employee employee) {
        this.username = username;
        this.password = password;
        this.roleTransiente = role;
        this.isActive = true;
        this.employee = employee;
        this.balance = 0;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    @PostLoad
    void postLoad() {
        if (role != null) {
            this.roleTransiente = Role.fromValue(role);
        }
    }

    @PrePersist
    void prePersist() {
        if (roleTransiente != null) {
            this.role = roleTransiente.getValue();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roleTransiente.name()));
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
        return role;
    }

    public Integer getBalance() {
        return balance;
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
                roleTransiente,
                balance,
                employee.toDTO(),
                isActive);
    }
}
