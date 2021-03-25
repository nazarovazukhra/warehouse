package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.InputDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CurrencyRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.SupplierRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    final InputRepository inputRepository;
    final WarehouseRepository warehouseRepository;
    final SupplierRepository supplierRepository;
    final CurrencyRepository currencyRepository;

    public InputService(InputRepository inputRepository, WarehouseRepository warehouseRepository, SupplierRepository supplierRepository, CurrencyRepository currencyRepository) {
        this.inputRepository = inputRepository;
        this.warehouseRepository = warehouseRepository;
        this.supplierRepository = supplierRepository;
        this.currencyRepository = currencyRepository;
    }

    public Result add(InputDTO inputDTO) {

        Input input = new Input();
        input.setCode(inputDTO.getCode());
        input.setFactureNumber(inputDTO.getFactureNumber());
        input.setDate(inputDTO.getDate());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());

        if (optionalWarehouse.isPresent() && optionalSupplier.isPresent() && optionalCurrency.isPresent()) {

            Warehouse warehouse = optionalWarehouse.get();
            Supplier supplier = optionalSupplier.get();
            Currency currency = optionalCurrency.get();


            input.setWarehouse(warehouse);
            input.setSupplier(supplier);
            input.setCurrency(currency);

            inputRepository.save(input);
            return new Result("Input added", true);
        }
        return new Result("Such warehouse or supplier or currency not found", false);
    }

    public List<Input> get() {
        return inputRepository.findAll();
    }

    public Input getById(Integer id) {

        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElse(null);
    }

    public Result delete(Integer id) {

        boolean exists = inputRepository.existsById(id);
        if (exists) {
            inputRepository.deleteById(id);
            return new Result("Input deleted", true);
        }
        return new Result("Such inout not found", false);
    }

    public Result edit(Integer id, InputDTO inputDTO) {

        Optional<Input> optionalInput = inputRepository.findById(id);

        if (optionalInput.isPresent()) {
            Input input = optionalInput.get();

            input.setCode(inputDTO.getCode());
            input.setFactureNumber(inputDTO.getFactureNumber());
            input.setDate(inputDTO.getDate());

            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
            Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
            Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());

            if (optionalWarehouse.isPresent() && optionalSupplier.isPresent() && optionalCurrency.isPresent()) {

                Warehouse warehouse = optionalWarehouse.get();
                Supplier supplier = optionalSupplier.get();
                Currency currency = optionalCurrency.get();


                input.setWarehouse(warehouse);
                input.setSupplier(supplier);
                input.setCurrency(currency);

                inputRepository.save(input);
                return new Result("Input added", true);
            } else {
                return new Result("Such warehouse not found", false);
            }

        }
        return new Result("Such input not found", false);
    }
}
