package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse) {

        return warehouseService.add(warehouse);
    }

    @GetMapping
    public List<Warehouse> getAll() {

        return warehouseService.get();
    }

    @GetMapping("/{id}")
    public Warehouse getById(@PathVariable Integer id) {

        return warehouseService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result deletedById(@PathVariable Integer id) {

        return warehouseService.delete(id);
    }

    @PutMapping("/{id}")
    public Result editById(@PathVariable Integer id, @RequestBody Warehouse warehouse) {

        return warehouseService.edit(id, warehouse);
    }
}
