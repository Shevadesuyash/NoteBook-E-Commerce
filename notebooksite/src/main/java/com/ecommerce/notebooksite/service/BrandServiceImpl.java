package com.ecommerce.notebooksite.service;

import com.ecommerce.notebooksite.entity.Brand;
import com.ecommerce.notebooksite.model.BrandResponse;
import com.ecommerce.notebooksite.repository.BrandRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BrandServiceImpl implements BrandService {

  private final BrandRepository brandRepository;

  public BrandServiceImpl(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  @Override
  public List<BrandResponse> getAllBrands() {
    log.info("Fetching all Brands !!!");
    List<Brand> brandList = brandRepository.findAll();

    List<BrandResponse> response =
        brandList.stream().map(this::convertToBrandResponse).collect(Collectors.toList());

    return response;
  }

  private BrandResponse convertToBrandResponse(Brand brand) {
    return BrandResponse.builder().id(brand.getId()).name(brand.getName()).build();
  }
}
