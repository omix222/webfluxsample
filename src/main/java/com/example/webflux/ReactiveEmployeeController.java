package com.example.webflux;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.time.Duration;
import java.util.stream.Stream;

public class ReactiveEmployeeController {
    private String id;
    private String name;

    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route(RequestPredicates.GET("/employee"), this::employee).
                andRoute(RequestPredicates.POST("/employee"), this::postEmployee);
    }

    public Mono<ServerResponse> postEmployee(ServerRequest req){
        return  req.bodyToMono(Employee.class)
                .flatMap(employee1 -> {
                    this.id = employee1.getId();
                    this.name = employee1.getName();
                    return ServerResponse.ok().body(Mono.just(employee1),Employee.class);
                }).switchIfEmpty(ServerResponse.badRequest().build());
    }


    public Mono<ServerResponse> employee(ServerRequest req) {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
        Flux<Employee> mapFlux = Flux.fromStream(stream).zipWith(Flux.interval(Duration.ofSeconds(1)))
                .map(i -> {
                	Employee employee = new Employee();
                	employee.setId(id);
                	employee.setName(name);
                    return employee;
                });

        return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(mapFlux,
        		Employee.class);
}
}
