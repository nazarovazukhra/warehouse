package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.ProductDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Result addProduct(@RequestBody ProductDTO productDTO) {

        return productService.add(productDTO);
    }

    @GetMapping
    public List<Product> getAll() {

        return productService.get();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer id) {

        return productService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {

        return productService.delete(id);
    }

    @PutMapping("/{id}")
    public Result editById(@PathVariable Integer id ,@RequestBody ProductDTO productDTO){

       return productService.edit(id,productDTO);
    }
}
