package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.payload.InputProductDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    final InputProductService inputProductService;

    public InputProductController(InputProductService inputProductService) {
        this.inputProductService = inputProductService;
    }

    @PostMapping
    public Result add(@RequestBody InputProductDTO inputProductDTO) {

        return inputProductService.add(inputProductDTO);
    }

    @GetMapping
    public List<InputProduct> get() {

        return inputProductService.get();
    }

    @GetMapping("/{id}")
    public InputProduct getById(@PathVariable Integer id) {

        return inputProductService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {

        return inputProductService.delete(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody InputProductDTO inputProductDTO) {

        return inputProductService.edit(id, inputProductDTO);
    }
}
