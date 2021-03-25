package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement) {

        Result result = measurementService.addMeasurementService(measurement);

        return result;
    }

    @GetMapping
    public List<Measurement> getMeasurements() {
        return measurementService.getAllMeasurements();
    }

    @GetMapping("/{id}")
    public Measurement getById(@PathVariable Integer id) {
        Measurement measurement = measurementService.getById(id);
        return measurement;
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        Result result = measurementService.deleteById(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result editById(@PathVariable Integer id, @RequestBody Measurement measurement) {
        Result result = measurementService.editById(id, measurement);
        return result;
    }
}
