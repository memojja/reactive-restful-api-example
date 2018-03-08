package com.reative.reative.resource;

import com.reative.reative.model.Employee;
import com.reative.reative.model.EmployeeEvent;
import com.reative.reative.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import javax.print.attribute.standard.Media;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeResource {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeResource(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public Flux<Employee> getAll(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Employee> getById(@PathVariable("id") String id){
        return employeeRepository.findById(id);
    }

    @GetMapping(value = "/{id}/events" ,produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmployeeEvent> getEventsByUserId(@PathVariable("id")String id){
        return employeeRepository.findById(id)
                .flatMapMany(
                        employee -> {
                            Flux<Long> interval = Flux.interval(Duration.ofSeconds(4));
                            Flux<EmployeeEvent> employeeEventFlux = Flux.fromStream(
                                    Stream.generate(() -> new EmployeeEvent(employee,new Date()))
                            );
                            return Flux.zip(interval,employeeEventFlux).map(Tuple2::getT2);
                        }
                );
    }

}
