package com.nasr.productservice.command.api.service.impl;

import com.nasr.productservice.command.api.data.ProductLookup;
import com.nasr.productservice.command.api.repository.ProductLookupRepository;
import com.nasr.productservice.command.api.service.ProductLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductLookupServiceImpl implements ProductLookupService {

    @Autowired
    private ProductLookupRepository repository;

    @Override
    public void checkExistsByName(String name) {
        boolean isExists = repository.existsByName(name);

        if (isExists)
            throw new IllegalStateException("this product name already taken !");
    }

    @Override
    @Transactional
    public void save(ProductLookup product) {
        repository.save(product);
    }

}
