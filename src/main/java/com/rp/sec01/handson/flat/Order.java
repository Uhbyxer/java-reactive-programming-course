package com.rp.sec01.handson.flat;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Order {
    private String id;
    private String cusId;

    public static Flux<Order> getOrdersForCus(String cusId) {
        return Flux.fromIterable(orders.getOrDefault(cusId, List.of())).delayElements(Duration.ofSeconds(1));
    }

    private static final Map<String, List<Order>> orders = Map.of(
            "1", List.of(new Order("01", "1"), new Order("02", "1"), new Order("03", "1")),
            "2", List.of(new Order("04", "2"), new Order("05", "2")),
            "3", List.of(new Order("06", "3")),
            "4", List.of(new Order("07", "4"))
    );
}
