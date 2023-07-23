package br.com.pedro.produtosapi.dto;

import br.com.pedro.produtosapi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizeProdDto {
    private Long id;
    private String name;
    private String description;
    private int ncm;
    private int quantity;
    private double value;
    private int active;

    public Product toProduct() {
        return new ModelMapper().map(this, Product.class);
    }
}
