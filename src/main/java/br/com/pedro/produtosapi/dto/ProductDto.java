package br.com.pedro.produtosapi.dto;

import br.com.pedro.produtosapi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private String description;
    private int ncm = -1;
    private int quantity = -1;
    private double value = -1;
    private boolean active;

    public ProductDto(Product product) {
        new ModelMapper().map(product, this);
    }

    public Product toProduct(){
        return new ModelMapper().map(this, Product.class);
    }

}
