package com.nasr.productservice.command.api.service;

import com.nasr.core.model.OrderDetailData;
import com.nasr.productservice.command.api.data.ProductLookup;

public interface ProductLookupService {

    void checkExistsByName(String name);

    void save(ProductLookup product);

}
