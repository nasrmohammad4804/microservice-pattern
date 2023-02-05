package com.nasr.productservice.command.api.repository;

import com.nasr.productservice.command.api.data.ProductLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

//@Lookup
public interface ProductLookupRepository extends JpaRepository<ProductLookup,String> {

    boolean existsByName(String name);

}
