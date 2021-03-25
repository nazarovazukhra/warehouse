package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.OutputDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    final OutputRepository outputRepository;
    final WarehouseRepository warehouseRepository;
    final ClientRepository clientRepository;
    final CurrencyRepository currencyRepository;

    public OutputService(OutputRepository outputRepository, WarehouseRepository warehouseRepository, ClientRepository clientRepository, CurrencyRepository currencyRepository) {
        this.outputRepository = outputRepository;
        this.warehouseRepository = warehouseRepository;
        this.clientRepository = clientRepository;
        this.currencyRepository = currencyRepository;
    }

    public Result add(OutputDTO outputDTO) {

        Output output = new Output();
        output.setCode(outputDTO.getCode());
        output.setFactureNumber(outputDTO.getFactureNumber());
        output.setDate(outputDTO.getDate());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouseId());
        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClientId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrencyId());

        if (optionalWarehouse.isPresent() && optionalClient.isPresent() && optionalCurrency.isPresent()) {

            Warehouse warehouse = optionalWarehouse.get();
            Client client = optionalClient.get();
            Currency currency = optionalCurrency.get();


            output.setWarehouse(warehouse);
            output.setClient(client);
            output.setCurrency(currency);

            outputRepository.save(output);
            return new Result("Input added", true);
        }
        return new Result("Such warehouse or supplier or currency not found", false);
    }

    public List<Output> get() {
        return outputRepository.findAll();
    }

    public Output getById(Integer id) {

        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElse(null);
    }

    public Result delete(Integer id) {

        boolean exists = outputRepository.existsById(id);
        if (exists) {
            outputRepository.deleteById(id);
            return new Result("Input deleted", true);
        }
        return new Result("Such inout not found", false);
    }

    public Result edit(Integer id, OutputDTO outputDTO) {

        Optional<Output> optionalOutput = outputRepository.findById(id);

        if (optionalOutput.isPresent()) {
            Output output = optionalOutput.get();

            output.setCode(output.getCode());
            output.setFactureNumber(output.getFactureNumber());
            output.setDate(output.getDate());

            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouseId());
            Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClientId());
            Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrencyId());

            if (optionalWarehouse.isPresent() && optionalClient.isPresent() && optionalCurrency.isPresent()) {

                Warehouse warehouse = optionalWarehouse.get();
                Client client = optionalClient.get();
                Currency currency = optionalCurrency.get();


                output.setWarehouse(warehouse);
                output.setClient(client);
                output.setCurrency(currency);

                outputRepository.save(output);
                return new Result("Input added", true);
            } else {
                return new Result("Such warehouse not found", false);
            }

        }
        return new Result("Such input not found", false);
    }
}
