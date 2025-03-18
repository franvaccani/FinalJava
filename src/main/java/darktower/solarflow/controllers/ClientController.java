package darktower.solarflow.controllers;

import darktower.solarflow.dto.ClientDTO;
import darktower.solarflow.entities.Client;
import darktower.solarflow.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    @Lazy
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    public Client createClient(@Valid @RequestBody ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setDocnumber(clientDTO.getDocnumber());
        return clientService.saveClient(client);
    }
}