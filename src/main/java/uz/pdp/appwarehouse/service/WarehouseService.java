package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Result add(Warehouse warehouse) {

        Warehouse newWarehouse = new Warehouse();

        newWarehouse.setName(warehouse.getName());
        newWarehouse.setActive(warehouse.isActive());

        warehouseRepository.save(newWarehouse);

        return new Result("Warehouse saved", true);
    }

    public List<Warehouse> get() {

        return warehouseRepository.findAll();
    }

    public Warehouse getById(Integer id) {

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.orElse(null);
    }

    public Result delete(Integer id) {

        boolean exists = warehouseRepository.existsById(id);
        if (exists) {
            warehouseRepository.deleteById(id);
            return new Result("Warehouse deleted", true);
        }
        return new Result("Such warehouse not found", false);
    }

    public Result edit(Integer id, Warehouse warehouse) {

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);

        if (optionalWarehouse.isPresent()) {
            Warehouse editingWarehouse = optionalWarehouse.get();

            editingWarehouse.setName(warehouse.getName());
            editingWarehouse.setActive(warehouse.isActive());

            warehouseRepository.save(editingWarehouse);

            return new Result("Warehouse edited", true);

        }

        return new Result("Such warehouse not found", false);

    }
}
