package ru.flamexander.reactive.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.flamexander.reactive.service.dtos.ProductDetailsDto;
import ru.flamexander.reactive.service.entities.Product;
import ru.flamexander.reactive.service.exceptions.ResourceNotFoundException;
import ru.flamexander.reactive.service.integrations.ProductDetailsServiceIntegration;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailsService {
    private final ProductDetailsServiceIntegration productDetailsServiceIntegration;
    private final ProductsService productsService;

    public Mono<ProductDetailsDto> getProductDetailsById(Long id) {
        return productsService.findById(id)
                .flatMap(this::callProductDetailService)
                .log();
    }

    public Flux<ProductDetailsDto> getProductDetailsById(List<Long> ids) {
        return Flux.fromStream(ids.stream()).flatMap(this::getProductDetailsById);
    }

    public Flux<ProductDetailsDto> findAllProductDetails() {
        return productsService.findAll()
                .flatMap(this::callProductDetailService)
                .log();
    }

    private Mono<ProductDetailsDto> callProductDetailService(Product product) {
        return productDetailsServiceIntegration.getProductDetailsById(product.getId())
                .doOnNext(it -> it.setName(product.getName()))
                .onErrorReturn(ResourceNotFoundException.class, new ProductDetailsDto(product.getId(), null, product.getName()));
    }
}
