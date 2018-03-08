package com.reative.reative.repository;

import com.reative.reative.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeRepository extends ReactiveMongoRepository<Employee,String> {
}
