package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.SupplierDTO;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    public Result add(SupplierDTO supplierDTO) {

        Supplier supplier = new Supplier();
        supplier.setName(supplierDTO.getName());
        supplier.setActive(supplierDTO.getActive());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());

        supplierRepository.save(supplier);
        return new Result("Supplier added", true);
    }

    public List<Supplier> get() {
        return supplierRepository.findAll();
    }

    public Supplier getById(@PathVariable Integer id) {

        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.orElse(null);
    }

    public Result delete(Integer id) {

        boolean exists = supplierRepository.existsById(id);
        if (!exists)
            return new Result("Such supplier not found", false);
        supplierRepository.deleteById(id);
        return new Result("Supplier deleted", true);

    }

    public Result edit(Integer id, SupplierDTO supplierDTO) {

        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if(optionalSupplier.isPresent()){
            Supplier supplier = optionalSupplier.get();
            supplier.setName(supplierDTO.getName());
            supplier.setActive(supplierDTO.getActive());
            supplier.setPhoneNumber(supplierDTO.getPhoneNumber());

            supplierRepository.save(supplier);
            return new Result("Supplier edited",true);
        }
        return new Result("Such supplier not found",false);
    }
}
