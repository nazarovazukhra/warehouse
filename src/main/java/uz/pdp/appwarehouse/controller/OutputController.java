package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.payload.OutputDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    final OutputService outputService;

    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }

    @PostMapping
    public Result addInput(@RequestBody OutputDTO outputDTO) {

        return outputService.add(outputDTO);
    }

    @GetMapping
    public List<Output> getAll() {

        return outputService.get();
    }

    @GetMapping("/{id}")
    public Output getById(@PathVariable Integer id) {

        return outputService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {

        return outputService.delete(id);
    }

    @PutMapping("/{id}")
    public Result editById(@PathVariable Integer id, @RequestBody OutputDTO outputDTO) {

        return outputService.edit(id, outputDTO);
    }
}
