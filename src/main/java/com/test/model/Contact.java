package com.test.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full Name is required")
    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String email;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Enter a valid 10-digit contact number")
    @Column(nullable = false, length = 10)
    private String contact;

    @Column(columnDefinition = "TEXT")
    private String message;

    // This will create a separate table `contact_loans` for storing loan types
    @ElementCollection
    @CollectionTable(
        name = "contact_loans",
        joinColumns = @JoinColumn(name = "contact_id")
    )
    @Column(name = "loan")
    private List<String> loans = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --------------------
    // Getters and Setters
    // --------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<String> getLoans() { return loans; }
    public void setLoans(List<String> loans) { this.loans = loans; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
