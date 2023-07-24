package br.com.pedro.produtosapi.service;

import br.com.pedro.produtosapi.dto.ProductDto;
import br.com.pedro.produtosapi.model.Product;
import br.com.pedro.produtosapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
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

//    public ResponseEntity<Product> partialUpdateProd(Long id, ProductDto productDto) {
//        Optional<Product> product = productRepository.findById(id);
//        if (product.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        product.get().setId(id);
//        productRepository.save(productDto.toProduct());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
