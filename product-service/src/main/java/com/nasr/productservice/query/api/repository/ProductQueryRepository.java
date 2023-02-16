package com.nasr.productservice.query.api.repository;

import com.nasr.productservice.query.api.data.Product;
import com.nasr.productservice.query.api.dto.ProductResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface ProductQueryRepository extends JpaRepository<Product,String> {

    @Query("select new com.nasr.productservice.query.api.dto.ProductResponseDto(p.id,p.name,p.price,p.quantity) from Product as p where p.id=:id")
    ProductResponseDto findByProductId(String id);

    @Query("select new com.nasr.productservice.query.api.dto.ProductResponseDto(p.id,p.name,p.price,p.quantity) from Product as p ")
    List<ProductResponseDto> findAllProducts();

    @Modifying
    @Query("update Product p set p.quantity=(p.quantity - :orderQuantity) , p.lastUpdateDate= :eventDate where p.id=:productId")
    void reduceQuantity(String productId, Integer orderQuantity, Instant eventDate);

    @Modifying
    @Query("update Product p set p.quantity=( p.quantity + :quantity) , p.lastUpdateDate= :eventDate where p.id=:id")
    void increaseQuantity(String id, Integer quantity, Instant eventDate);
}
