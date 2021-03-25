package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.SupplierDTO;
import uz.pdp.appwarehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public Result addSupplier(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.add(supplierDTO);
    }

    @GetMapping
    public List<Supplier> getSuppliers() {
        return supplierService.get();
    }

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable Integer id) {
        return supplierService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        return supplierService.delete(id);
    }

    @PutMapping("/{id}")
    public Result editById(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO) {
        return supplierService.edit(id, supplierDTO);
    }
}
