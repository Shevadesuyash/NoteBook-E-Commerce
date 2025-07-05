package com.ecommerce.notebooksite.repository;

import com.ecommerce.notebooksite.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyWord%")
    List<Product> findByName(@Param("keyWord") String keyWord);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyWord% AND p.brand.id = :brandId AND p.type.id = :typeId")
    List<Product> findByBrandTypeAndName(@Param("brandId")Integer brandId,@Param("typeId") Integer typeId,@Param("keyWord") String keyword);

    @Query("SELECT p FROM Product p WHERE p.brand.id = :brandId AND p.type.id = :typeId")
    List<Product> findByBrandAndType(@Param("brandId")Integer brandId,@Param("typeId") Integer typeId);

    @Query("SELECT p FROM Product p WHERE p.brand.id = :brandId ")
    List<Product> findByBrand(@Param("brandId")Integer brandId);

    @Query("SELECT p FROM Product p WHERE p.type.id = :typeId ")
    List<Product> findByType(@Param("typeId")Integer typeId);
}
