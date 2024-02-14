package ru.flamexander.reactive.service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.flamexander.reactive.service.dtos.ProductDetailsDto;
import ru.flamexander.reactive.service.services.ProductDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detailed")
@RequiredArgsConstructor
public class ProductsDetailsController {
    private final ProductDetailsService productDetailsService;

    @GetMapping("/{id}")
    public Mono<ProductDetailsDto> getDetailedProduct(@PathVariable Long id) {
        return productDetailsService.getProductDetailsById(id);
    }

    @GetMapping
    public Flux<ProductDetailsDto> getDetailedProducts(@RequestParam List<Long> ids) {
        return productDetailsService.getProductDetailsById(ids);
    }

    @GetMapping("/all")
    public Flux<ProductDetailsDto> findAllProducts() {
        return productDetailsService.findAllProductDetails();
    }
}
