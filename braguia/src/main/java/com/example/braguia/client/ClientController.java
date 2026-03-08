package com.example.braguia.client;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/client")
@Tag(name="Clients", description="Bank Clients management APIs")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Add a client to the bank")
    @PostMapping("/add")
    public String addClient(@Valid @RequestParam String name, @Valid @RequestParam String email, @Valid @RequestParam String password, @Valid @RequestParam String NIF, @Valid @RequestParam String NIC) {
        boolean result = clientService.addClient(name, email, password, NIF, NIC);

        if (result) { return "Saved"; }
        else { return "Could not add client"; }
    }

    @Operation(summary = "Get a list of all of the bank's clients")
    @GetMapping("/all")
    public Iterable<Client> getAllClients() {
        Iterable<Client> clients = clientService.getAllClients();

        return clients;
    }
}