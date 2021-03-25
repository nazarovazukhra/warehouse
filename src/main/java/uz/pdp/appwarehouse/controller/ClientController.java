package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Result addSupplier(@RequestBody Client client) {
        return clientService.add(client);
    }

    @GetMapping
    public List<Client> getClients() {
        return clientService.get();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id) {
        return clientService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        return clientService.delete(id);
    }

    @PutMapping("/{id}")
    public Result editById(@PathVariable Integer id, @RequestBody Client client) {
        return clientService.edit(id, client);
    }
}
