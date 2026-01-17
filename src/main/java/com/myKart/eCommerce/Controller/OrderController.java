package com.myKart.eCommerce.Controller;

import com.myKart.eCommerce.Service.EventProducer;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Tag(name = "E-Commerce Events", description = "Trigger dummy order events")
public class OrderController {
    private final EventProducer eventProducer;

    public OrderController(EventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    @PostMapping("/{orderId}/placed")
    public String orderPlaced(@PathVariable String orderId) {
        eventProducer.publishEvent("ORDER_PLACED", orderId);
        return "Order placed event published.";
    }

    @PostMapping("/{orderId}/out-for-delivery")
    public String outForDelivery(@PathVariable String orderId) {
        eventProducer.publishEvent("OUT_FOR_DELIVERY", orderId);
        return "Out for delivery event published.";
    }

    @PostMapping("/{orderId}/delivered")
    public String delivered(@PathVariable String orderId) {
        eventProducer.publishEvent("DELIVERED", orderId);
        return "Delivered event published.";
    }
}

