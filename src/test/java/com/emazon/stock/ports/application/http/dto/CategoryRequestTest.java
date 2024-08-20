package com.emazon.stock.ports.application.http.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoryRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCategoryName_NotBlank() {
        CategoryRequest categoryRequest = new CategoryRequest("", "Valid description");
        Set<ConstraintViolation<CategoryRequest>> violations = validator.validate(categoryRequest);

        assertEquals(1, violations.size());
        ConstraintViolation<CategoryRequest> violation = violations.iterator().next();
        assertEquals("{category.name.required-message}", violation.getMessageTemplate());
    }

    @Test
    void testCategoryName_Size() {
        String longName = "a".repeat(51);
        CategoryRequest categoryRequest = new CategoryRequest(longName, "Valid description");
        Set<ConstraintViolation<CategoryRequest>> violations = validator.validate(categoryRequest);

        assertEquals(1, violations.size());
        ConstraintViolation<CategoryRequest> violation = violations.iterator().next();
        assertEquals("{category.name.length-message}", violation.getMessageTemplate());
    }

    @Test
    void testCategoryDescription_NotBlank() {
        CategoryRequest categoryRequest = new CategoryRequest("Valid name", "");
        Set<ConstraintViolation<CategoryRequest>> violations = validator.validate(categoryRequest);

        assertEquals(1, violations.size());
        ConstraintViolation<CategoryRequest> violation = violations.iterator().next();
        assertEquals("{category.description.required-message}", violation.getMessageTemplate());
    }

    @Test
    void testCategoryDescription_Size() {
        String longDescription = "a".repeat(91);
        CategoryRequest categoryRequest = new CategoryRequest("Valid name", longDescription);
        Set<ConstraintViolation<CategoryRequest>> violations = validator.validate(categoryRequest);

        assertEquals(1, violations.size());
        ConstraintViolation<CategoryRequest> violation = violations.iterator().next();
        assertEquals("{category.description.length-message}", violation.getMessageTemplate());
    }

    @Test
    void testValidCategoryRequest() {
        CategoryRequest categoryRequest = new CategoryRequest("Valid name", "Valid description");
        Set<ConstraintViolation<CategoryRequest>> violations = validator.validate(categoryRequest);

        assertTrue(violations.isEmpty());

    }
}