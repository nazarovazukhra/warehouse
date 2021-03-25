package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.OutputProductDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    final OutputProductRepository outputProductRepository;
    final OutputRepository outputRepository;
    final ProductRepository productRepository;

    public OutputProductService(OutputProductRepository outputProductRepository, OutputRepository outputRepository, ProductRepository productRepository) {
        this.outputProductRepository = outputProductRepository;
        this.outputRepository = outputRepository;
        this.productRepository = productRepository;
    }

    public Result add(OutputProductDTO outputProductDTO) {

        OutputProduct outputProduct = new OutputProduct();

        outputProduct.setAmount(outputProductDTO.getAmount());
        outputProduct.setPrice(outputProductDTO.getPrice());

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutputId());
        Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
        if (optionalOutput.isPresent() && optionalProduct.isPresent()) {

            outputProduct.setOutput(optionalOutput.get());
            outputProduct.setProduct(optionalProduct.get());

            outputProductRepository.save(outputProduct);
            return new Result("OutputProduct added", true);
        }
        return new Result("Such output or product not found", false);
    }

    public List<OutputProduct> get() {

        return outputProductRepository.findAll();
    }

    public OutputProduct getById(Integer id) {

        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.orElse(null);
    }

    public Result delete(Integer id) {

        boolean exists = outputProductRepository.existsById(id);
        if (exists) {
            outputProductRepository.deleteById(id);
            return new Result("InputProduct deleted", true);
        }
        return new Result("Such inputProduct not found", false);
    }

    public Result edit(Integer id, OutputProductDTO outputProductDTO) {

        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()) {

            OutputProduct outputProduct = optionalOutputProduct.get();

            outputProduct.setAmount(outputProductDTO.getAmount());
            outputProduct.setPrice(outputProductDTO.getPrice());


            Optional<Output> outputOptional = outputRepository.findById(outputProductDTO.getOutputId());
            Optional<Product> productOptional = productRepository.findById(outputProductDTO.getProductId());

            if (outputOptional.isPresent() && productOptional.isPresent()) {

                outputProduct.setOutput(outputOptional.get());
                outputProduct.setProduct(productOptional.get());

                outputProductRepository.save(outputProduct);
                return new Result("OutputProduct edited", true);
            } else {
                return new Result("Such output or product not found", false);
            }
        }

        return new Result("Such outputProduct not found", false);
    }
}
