package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }


    public Result addMeasurementService(Measurement measurement) {

        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName) {

            Result result = new Result();
            result.setMessage("Such measurement already exists");
            result.setSuccess(false);
            return result;

            //          return new Result("Bunday o'lchov birligi mavjud", false);

        }

        measurementRepository.save(measurement);
        return new Result("Measurement added", true);
    }


    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }


    public Measurement getById(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.orElse(null);
    }


    public Result deleteById(Integer id) {
        boolean exists = measurementRepository.existsById(id);
        if (!exists)
            return new Result("Measurement not found", false);
        measurementRepository.deleteById(id);
        return new Result("Measurement deleted", true);
    }


    public Result editById(Integer id, Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()) {
            Measurement editing = optionalMeasurement.get();
            editing.setName(measurement.getName());
            editing.setActive(measurement.isActive());
            measurementRepository.save(editing);
            return new Result("Edited into", true, editing.getName());
        }
        return new Result("Such measurement not found", false);
    }
}
