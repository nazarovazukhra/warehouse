package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.ClientRepository;


import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Result add(Client client) {

        Client newClient = new Client();
        newClient.setName(client.getName());
        newClient.setActive(client.isActive());
        newClient.setPhoneNumber(client.getPhoneNumber());

        clientRepository.save(newClient);
        return new Result("Client added", true);
    }

    public List<Client> get() {
        return clientRepository.findAll();
    }

    public Client getById(@PathVariable Integer id) {

        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
    }

    public Result delete(Integer id) {

        boolean exists = clientRepository.existsById(id);
        if (!exists)
            return new Result("Such client not found", false);
        clientRepository.deleteById(id);
        return new Result("Client deleted", true);

    }

    public Result edit(Integer id, Client client) {

        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client editingClient = optionalClient.get();
            editingClient.setName(client.getName());
            editingClient.setActive(client.isActive());
            editingClient.setPhoneNumber(client.getPhoneNumber());

            clientRepository.save(client);
            return new Result("Client edited", true);
        }
        return new Result("Such client not found", false);
    }
}
