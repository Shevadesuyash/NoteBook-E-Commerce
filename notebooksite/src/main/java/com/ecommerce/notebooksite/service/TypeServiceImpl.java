package com.ecommerce.notebooksite.service;

import com.ecommerce.notebooksite.entity.Type;
import com.ecommerce.notebooksite.model.TypeResponse;
import com.ecommerce.notebooksite.repository.TypeRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TypeServiceImpl implements TypeService {

  private final TypeRepository typeRepository;

  public TypeServiceImpl(TypeRepository typeRepository) {
    this.typeRepository = typeRepository;
  }

  @Override
  public List<TypeResponse> getAllType() {
    log.info("Fetching all type");
    List<Type> typeList = typeRepository.findAll();

    List<TypeResponse> responses =
        typeList.stream().map(this::convertToTypeResponse).collect(Collectors.toList());

    return responses;
  }

  private TypeResponse convertToTypeResponse(Type type) {
    return TypeResponse.builder().id(type.getId()).name(type.getName()).build();
  }
}
