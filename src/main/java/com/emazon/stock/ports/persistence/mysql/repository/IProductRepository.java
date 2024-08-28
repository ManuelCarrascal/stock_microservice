package com.emazon.stock.ports.persistence.mysql.repository;

import com.emazon.stock.ports.persistence.mysql.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductRepository extends JpaRepository<ProductEntity,Long> {
}
