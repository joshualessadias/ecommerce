package com.predize.ecommerce.service;

import com.predize.ecommerce.enums.MessageEnum;
import com.predize.ecommerce.model.Order;
import com.predize.ecommerce.model.ProductOrder;
import com.predize.ecommerce.repository.OrderRepository;
import com.predize.ecommerce.service.dto.request.OrderRequestDTO;
import com.predize.ecommerce.service.dto.request.ProductOrderRequestDTO;
import com.predize.ecommerce.service.dto.response.OrderResponseDTO;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final ProductService productService;

    @Transactional
    public OrderResponseDTO checkout(OrderRequestDTO request) {
        var order = new Order();

        request.getProductOrderRequestList().forEach(productOrderRequest -> {
            var productOrder = buildProductOrder(productOrderRequest);

            if (order.getPrice() == null)
                order.setPrice(productOrderRequest.getPrice());
            else {
                var newValue = order.getPrice().add(productOrderRequest.getPrice());
                order.setPrice(newValue);
            }
            order.addProductOrder(productOrder);
        });

        return new OrderResponseDTO(repository.save(order));
    }

    @Transactional
    ProductOrder buildProductOrder(ProductOrderRequestDTO productOrderRequest) {
        var productOrder = new ProductOrder();
        var product = productService.findById(productOrderRequest.getProductId());

        if (productOrderRequest.getQuantity() > product.getStock())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    MessageEnum.PRODUCT_ORDER_QUANTITY_INVALID.getDescription(product.getName())
            );

        productOrder.setPrice(productOrderRequest.getPrice());
        productOrder.setProduct(product);
        productOrder.setQuantity(productOrderRequest.getQuantity());
        productService.updateStock(product, productOrderRequest.getQuantity());

        return productOrder;
    }
}
