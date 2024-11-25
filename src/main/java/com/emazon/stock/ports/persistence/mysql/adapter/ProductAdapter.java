package com.emazon.stock.ports.persistence.mysql.adapter;

import com.emazon.stock.domain.exception.NotFoundException;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.spi.product.IProductPersistencePort;
import com.emazon.stock.domain.util.PaginationUtil;
import com.emazon.stock.domain.util.SortBy;
import com.emazon.stock.ports.persistence.mysql.entity.ProductEntity;
import com.emazon.stock.ports.persistence.mysql.mapper.IProductEntityMapper;
import com.emazon.stock.ports.persistence.mysql.repository.IProductRepository;

import com.emazon.stock.ports.persistence.mysql.util.ProductAdapterConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class ProductAdapter implements IProductPersistencePort {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Override
    public Pagination<Product> getAllProductsPaginated(PaginationUtil paginationUtil) {
        PageRequest pageRequest = PageRequest.of(paginationUtil.getPageNumber(), paginationUtil.getPageSize());
        Page<ProductEntity> productPage = null;

        if (SortBy.BRAND_NAME.getFieldName().equals(paginationUtil.getSortBy())) {
            productPage = paginationUtil.isAscending()
                    ? productRepository.findAllOrderByBrandNameAsc(pageRequest)
                    : productRepository.findAllOrderByBrandNameDesc(pageRequest);
        } else if (SortBy.NUMBER_OF_CATEGORIES.getFieldName().equals(paginationUtil.getSortBy())) {
            productPage = paginationUtil.isAscending()
                    ? productRepository.findAllOrderByNumberOfCategoriesAsc(pageRequest)
                    : productRepository.findAllOrderByNumberOfCategoriesDesc(pageRequest);
        } else if (SortBy.PRODUCT_NAME.getFieldName().equals(paginationUtil.getSortBy())) {
            productPage = paginationUtil.isAscending()
                    ? productRepository.findAllOrderByProductNameAsc(pageRequest)
                    : productRepository.findAllOrderByProductNameDesc(pageRequest);
        }

        assert productPage != null;
        List<Product> products = productEntityMapper.toProductList(productPage.getContent());

        return new Pagination<>(
                paginationUtil.isAscending(),
                paginationUtil.getPageNumber(),
                productPage.getTotalPages(),
                productPage.getTotalElements(),
                products
        );
    }

    @Transactional
    @Override
    public void updateProduct(Product product) {
        ProductEntity productEntity = productRepository.findById(product.getProductId())
                .orElseThrow(() -> new NotFoundException(ProductAdapterConstants.PRODUCT_NOT_FOUND_MESSAGE));
        productEntity.setProductQuantity(productEntity.getProductQuantity() + product.getProductQuantity());

        productRepository.save(productEntity);
    }


    @Override
    public void reduceProductQuantity(Product product) {
        ProductEntity productEntity = productRepository.findById(product.getProductId())
                .orElseThrow(() -> new NotFoundException(ProductAdapterConstants.PRODUCT_NOT_FOUND_MESSAGE));
        productEntity.setProductQuantity(productEntity.getProductQuantity() - product.getProductQuantity());

        productRepository.save(productEntity);
    }

    @Override
    public void getProductById(Long productId) {
        productEntityMapper.toProduct(productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(ProductAdapterConstants.PRODUCT_NOT_FOUND_MESSAGE)));
    }

    @Override
    public boolean isStockSufficient(Long productId, Integer quantity) {
        return productRepository.findStockQuantityProduct(productId) >= quantity;
    }

    @Override
    public Pagination<Product> getAllProductsPaginatedByIds(PaginationUtil paginationUtil, List<Long> productIds, String categoryName, String brandName) {
        PageRequest pageRequest = PageRequest.of(
                paginationUtil.getPageNumber(),
                paginationUtil.getPageSize(),
                paginationUtil.isAscending() ? Sort.by(ProductAdapterConstants.PRODUCT_NAME).ascending() : Sort.by(ProductAdapterConstants.PRODUCT_NAME).descending()
        );

        Page<ProductEntity> productPage = null;
        categoryName = categoryName == null ? null : ProductAdapterConstants.WILDCARD + categoryName + ProductAdapterConstants.WILDCARD ;
        brandName = brandName == null ? null : ProductAdapterConstants.WILDCARD + brandName +ProductAdapterConstants.WILDCARD ;

        if (categoryName != null && brandName != null) {
            productPage = productRepository.findByBrandNameAndCategoryNameAndIds(brandName, categoryName, productIds, pageRequest);
        }
        if (categoryName != null && productPage == null) {
            productPage = productRepository.findByCategoryAndIds(categoryName, productIds, pageRequest);
        }
        if (brandName != null && productPage == null) {
            productPage = productRepository.findByBrandNameAndIds(brandName, productIds, pageRequest);
        }
        if (productPage == null) {
            productPage = productRepository.findByIds(productIds, pageRequest);
        }

        List<Product> products = productEntityMapper.toProductList(productPage.getContent());
        return new Pagination<>(
                paginationUtil.isAscending(),
                paginationUtil.getPageNumber(),
                productPage.getTotalPages(),
                productPage.getTotalElements(),
                products
        );
    }

    @Override
    public double getProductPriceById(Long productId) {
        return productRepository.findById(productId)
                .map(ProductEntity::getProductPrice)
                .orElseThrow(() -> new NotFoundException(ProductAdapterConstants.PRODUCT_NOT_FOUND_MESSAGE));
    }

    @Override
    public List<Product> getAllProducts(List<Long> productIds) {
        return productEntityMapper.toProductList(productRepository.findAllById(productIds));
    }



}
