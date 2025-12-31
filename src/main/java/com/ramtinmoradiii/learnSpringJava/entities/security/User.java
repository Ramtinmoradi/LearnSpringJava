package com.ramtinmoradiii.learnSpringJava.entities.security;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roles")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String lastName;
    private String firstName;
    private String email;

    @Column(unique = true, length = 11, nullable = false)
    private String mobile;

    private Boolean enable = true;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime registerDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
}
