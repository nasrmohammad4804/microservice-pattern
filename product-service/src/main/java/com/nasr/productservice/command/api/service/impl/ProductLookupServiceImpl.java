package com.nasr.productservice.command.api.service.impl;

import com.nasr.productservice.command.api.data.ProductLookup;
import com.nasr.productservice.command.api.repository.ProductLookupRepository;
import com.nasr.productservice.command.api.service.ProductLookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
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

    @Override
    @Transactional
    public void deleteAll() {
        log.info("all productLookUp data deleted . because we want to replay event at beginning of event store for specific requirement !");
        repository.deleteAll();

    }

}
