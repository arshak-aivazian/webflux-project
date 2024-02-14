package ru.flamexander.product.details.service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.flamexander.product.details.service.exception.ResourceNotFoundException;
import ru.flamexander.product.details.service.dtos.ProductDetailsDto;

@RestController
@RequestMapping("/api/v1/details")
public class ProductDetailsController {
    @GetMapping("/{id}")
    public ProductDetailsDto getProductDetailsById(@PathVariable Long id) throws InterruptedException {
        if (id == 1) {
            throw new ResourceNotFoundException();
        }
        Thread.sleep(2500 + (int)(Math.random() * 2500));
        return new ProductDetailsDto(id, "Product description..");
    }
}
