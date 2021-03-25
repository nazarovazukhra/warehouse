package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.ProductDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    final ProductRepository productRepository;
    final AttachmentRepository attachmentRepository;
    final AttachmentContentRepository attachmentContentRepository;
    final CategoryRepository categoryRepository;
    final MeasurementRepository measurementRepository;

    public ProductService(ProductRepository productRepository, AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository, CategoryRepository categoryRepository, MeasurementRepository measurementRepository) {
        this.productRepository = productRepository;
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
        this.categoryRepository = categoryRepository;
        this.measurementRepository = measurementRepository;
    }

    public Result add(ProductDTO productDTO) {

        Product product = new Product();
        product.setName(product.getName());
        product.setCode(product.getCode());
        product.setActive(productDTO.getActive());

        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getCategoryId());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDTO.getMeasurementId());
        if (optionalCategory.isPresent() && optionalMeasurement.isPresent() && optionalAttachment.isPresent()) {
            Category category = optionalCategory.get();
            Measurement measurement = optionalMeasurement.get();
            Attachment attachment = optionalAttachment.get();

            product.setCategory(category);
            product.setMeasurement(measurement);
            product.setAttachment(attachment);

            productRepository.save(product);

            return new Result("Product added", true);
        }
        return new Result("Error in server", false);
    }

    public List<Product> get() {

        return productRepository.findAll();
    }

    public Product getById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public Result delete(Integer id) {

        boolean exists = productRepository.existsById(id);
        if (exists) {
            productRepository.deleteById(id);
            return new Result("Product deleted", true);
        }
        return new Result("Such product not found", false);
    }

    public Result edit(Integer id, ProductDTO productDTO) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDTO.getName());
            product.setCode(productDTO.getCode());
            product.setActive(productDTO.getActive());

            Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
            Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDTO.getAttachmentId());

            if (optionalCategory.isPresent() && optionalMeasurement.isPresent() && optionalAttachment.isPresent()) {
                Category category = optionalCategory.get();
                Measurement measurement = optionalMeasurement.get();
                Attachment attachment = optionalAttachment.get();

                product.setCategory(category);
                product.setMeasurement(measurement);
                product.setAttachment(attachment);

                productRepository.save(product);
                return new Result("Product edited", true);
            } else {
                return new Result("Such category not found", false);
            }
        }
        return new Result("Such product not found", false);
    }
}
