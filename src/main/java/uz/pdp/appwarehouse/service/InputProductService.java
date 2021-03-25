package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.InputProductDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.InputProductRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    final InputProductRepository inputProductRepository;
    final InputRepository inputRepository;
    final ProductRepository productRepository;

    public InputProductService(InputProductRepository inputProductRepository, InputRepository inputRepository, ProductRepository productRepository) {
        this.inputProductRepository = inputProductRepository;
        this.inputRepository = inputRepository;
        this.productRepository = productRepository;
    }

    public Result add(InputProductDTO inputProductDTO) {

        InputProduct inputProduct = new InputProduct();

        inputProduct.setAmount(inputProductDTO.getAmount());
        inputProduct.setPrice(inputProductDTO.getPrice());
        inputProduct.setExpireDate(inputProductDTO.getExpireDate());

        Optional<Input> optionalInput = inputRepository.findById(inputProductDTO.getInputId());
        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
        if (optionalInput.isPresent() && optionalProduct.isPresent()) {

            inputProduct.setInput(optionalInput.get());
            inputProduct.setProduct(optionalProduct.get());

            inputProductRepository.save(inputProduct);
            return new Result("InputProduct added", true);
        }
        return new Result("Such input or product not found", false);
    }

    public List<InputProduct> get() {

        return inputProductRepository.findAll();
    }

    public InputProduct getById(Integer id) {

        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElse(null);
    }

    public Result delete(Integer id) {

        boolean exists = inputProductRepository.existsById(id);
        if (exists) {
            inputProductRepository.deleteById(id);
            return new Result("InputProduct deleted", true);
        }
        return new Result("Such inputProduct not found", false);
    }

    public Result edit(Integer id, InputProductDTO inputProductDTO) {

        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()) {

            InputProduct inputProduct = optionalInputProduct.get();

            inputProduct.setAmount(inputProductDTO.getAmount());
            inputProduct.setPrice(inputProductDTO.getPrice());
            inputProduct.setExpireDate(inputProductDTO.getExpireDate());


            Optional<Input> inputOptional = inputRepository.findById(inputProductDTO.getInputId());
            Optional<Product> productOptional = productRepository.findById(inputProductDTO.getProductId());

            if (inputOptional.isPresent() && productOptional.isPresent()) {

                inputProduct.setInput(inputOptional.get());
                inputProduct.setProduct(productOptional.get());

                inputProductRepository.save(inputProduct);
                return new Result("InputProduct edited", true);
            } else {
                return new Result("Such input or product not found", false);
            }
        }

        return new Result("Such inputProduct not found", false);
    }
}
