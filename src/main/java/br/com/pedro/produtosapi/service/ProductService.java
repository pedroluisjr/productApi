package br.com.pedro.produtosapi.service;

import br.com.pedro.produtosapi.dto.ProductDto;
import br.com.pedro.produtosapi.model.Product;
import br.com.pedro.produtosapi.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductDto> getProd(){
        return productRepository.findAll().stream().map(ProductDto::new).toList();
    }

    public List<Product> isActive() {
        return productRepository.isActive();
    }

    public List<Product> isInactive() {
        return productRepository.isInactive();
    }

    public Product addProd(ProductDto productDto) {
        return productRepository.save(productDto.toProduct());
    }

    public ProductDto getById(Long id) {
        return new ProductDto(productRepository.findById(id).orElse(new Product()));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product actualizeProd(Long id, ProductDto productDto) {
        Product product = productDto.toProduct();
        product.setId(id);
        productRepository.save(product);
        return product;
    }

    public Product partialUpdateProd(Long id, ProductDto productDto) {
        Product productSave = productRepository.findById(id).orElseThrow();
        if (productDto.getName() != null) productSave.setName(productDto.getName());
        if (productDto.getDescription() != null) productSave.setDescription(productDto.getDescription());
        if (productDto.getNcm() != -1) productSave.setNcm((long) productDto.getNcm());
        if (productDto.getValue() != -1) productSave.setValue(productDto.getValue());
        productSave.setActive(productDto.isActive());
        productRepository.save(productSave);
        return productSave;
    }
}
