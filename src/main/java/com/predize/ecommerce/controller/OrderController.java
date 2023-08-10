package com.predize.ecommerce.controller;

import com.predize.ecommerce.service.OrderService;
import com.predize.ecommerce.service.dto.request.OrderRequestDTO;
import com.predize.ecommerce.service.dto.response.OrderResponseDTO;
import com.predize.ecommerce.service.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {
    private final OrderService service;

    @PostMapping
    public Response<OrderResponseDTO> checkout(@RequestBody OrderRequestDTO data) {
        var response = new Response<OrderResponseDTO>();

        response.setData(service.checkout(data));
        response.setOk();

        return response;
    }
}
