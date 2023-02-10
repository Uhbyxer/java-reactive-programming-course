package com.rp.sec01.handson.flat;

import lombok.Data;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Data
public class Customer {
    private String id;
    private String name;

    public Customer(String id) {
        this.id = id;
        this.name = "Cus " + id;
    }

    public static Flux<Customer> getCustomers() {
        return Flux.fromIterable(List.of(
           new Customer("1"),
           new Customer("2"),
           new Customer("3"),
           new Customer("4")
        ));
    }
}
