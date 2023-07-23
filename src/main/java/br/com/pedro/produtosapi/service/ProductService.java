package br.com.pedro.produtosapi.service;

import br.com.pedro.produtosapi.dto.ActualizeProdDto;
import br.com.pedro.produtosapi.dto.ProductDto;
import br.com.pedro.produtosapi.model.Product;
import br.com.pedro.produtosapi.repository.ProductRepository;
import org.modelmapper.ModelMapper;
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

    public Product actualizeProd(Product product) {
//        Product productE = getById(product.getId()).toProduct();

        deleteById(product.getId());

        productRepository.save(product);

        return product;
    }
}
