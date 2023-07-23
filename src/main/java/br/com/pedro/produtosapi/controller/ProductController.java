package br.com.pedro.produtosapi.controller;

import br.com.pedro.produtosapi.dto.ActualizeProdDto;
import br.com.pedro.produtosapi.dto.ProductDto;
import br.com.pedro.produtosapi.model.Product;
import br.com.pedro.produtosapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/todos")
    public List<ProductDto> getProd(){
        return productService.getProd();
    }

    @GetMapping
    public List<ProductDto> getProd(@RequestParam("active") boolean active){
        if (active){
            return productService.isActive().stream().map(ProductDto::new).toList();
        }
            return productService.isInactive().stream().map(ProductDto::new).toList();
    }

    @PostMapping
    public Product addProd(@RequestBody ProductDto productDto) {
        return productService.addProd(productDto);
    }

    @GetMapping("/{id}")
    public Optional<ProductDto> getById(@PathVariable("id") Long id) {
        return Optional.of(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Product actualizeProd(@PathVariable("id") Long id, @RequestBody ActualizeProdDto actualizeProdDto) {
        return productService.actualizeProd(id, actualizeProdDto);
    }

}
