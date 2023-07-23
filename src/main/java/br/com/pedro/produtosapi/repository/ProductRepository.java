package br.com.pedro.produtosapi.repository;

import br.com.pedro.produtosapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT prod FROM Product prod WHERE prod.active = true")
    List<Product> isActive();

    @Query("SELECT prod FROM Product prod WHERE prod.active = false")
    List<Product> isInactive();

}
