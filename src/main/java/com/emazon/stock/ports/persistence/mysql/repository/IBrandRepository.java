package com.emazon.stock.ports.persistence.mysql.repository;

import com.emazon.stock.ports.persistence.mysql.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
    Optional<BrandEntity> findByBrandName(String brandName);

    @Query("SELECT b FROM BrandEntity b")
    List<BrandEntity> getAllBrands();
}
