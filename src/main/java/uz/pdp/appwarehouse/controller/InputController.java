package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.payload.InputDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    final InputService inputService;

    public InputController(InputService inputService) {
        this.inputService = inputService;
    }

    @PostMapping
    public Result addInput(@RequestBody InputDTO inputDTO) {

        return inputService.add(inputDTO);
    }

    @GetMapping
    public List<Input> getAll() {

        return inputService.get();
    }

    @GetMapping("/{id}")
    public Input getById(@PathVariable Integer id) {

        return inputService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {

        return inputService.delete(id);
    }

    @PutMapping("/{id}")
    public Result editById(@PathVariable Integer id, @RequestBody InputDTO inputDTO) {

        return inputService.edit(id, inputDTO);
    }
}
