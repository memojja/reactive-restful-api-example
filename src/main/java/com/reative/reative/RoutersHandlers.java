package com.reative.reative;

import com.reative.reative.model.Employee;
import com.reative.reative.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RoutersHandlers {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public RoutersHandlers(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(employeeRepository.findAll(), Employee.class);
    }

    public Mono<ServerResponse> getId(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(employeeRepository.findById(serverRequest.pathVariable("id")),Employee.class);
    }

}
