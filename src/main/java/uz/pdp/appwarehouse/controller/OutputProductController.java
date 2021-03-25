package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.payload.OutputProductDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    final OutputProductService outputProductService;

    public OutputProductController(OutputProductService outputProductService) {
        this.outputProductService = outputProductService;
    }

    @PostMapping
    public Result add(@RequestBody OutputProductDTO outputProductDTO) {

        return outputProductService.add(outputProductDTO);
    }

    @GetMapping
    public List<OutputProduct> get() {

        return outputProductService.get();
    }

    @GetMapping("/{id}")
    public OutputProduct getById(@PathVariable Integer id) {

        return outputProductService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {

        return outputProductService.delete(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputProductDTO outputProductDTO) {

        return outputProductService.edit(id, outputProductDTO);
    }
}
