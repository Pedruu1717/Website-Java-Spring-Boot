package com.example.braguia.client;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "client")
@Schema(description="Client entity")
public class Client {
    @Bean
    private Argon2PasswordEncoder passwordEncoder() {
        int saltLength = 16;     // in bytes
        int hashLength = 32;     // in bytes
        int parallelism = 1;     // threads
        int memory = 1 << 13;    // 8 MB
        int iterations = 3;      // number of iterations
        return new Argon2PasswordEncoder(saltLength, hashLength, parallelism, memory, iterations);
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @NotNull
    @Schema(description="Unique identifier of the client", example="1")
    private Long id;

    @Column(nullable = false, unique = false)
    @NotNull
    @Schema(description="Name of the client", example="Pedro Melo")
    private String name;

    @Column(nullable = false, unique = false)
    @Email
    @NotNull
    @Schema(description="Email of the client", example="pedromelo@gmail.com")
    private String email;

    @Column(nullable = false, unique = false)
    @NotNull
    @Schema(description="The password the client uses to be able to perform actions on his account(s)", example="verySecret1!")
    private String password;

    @Column(nullable = false, unique = true)
    @NotNull
    @Schema(description="Taxpayer number of the client", example="145623987")
    private String NIF;

    @Column(nullable = false, unique = true)
    @NotNull
    @Schema(description="Identity card number of the client", example="45987620")
    private String NIC;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password; // @TODO passwordEncoder().encode(password);
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }
}