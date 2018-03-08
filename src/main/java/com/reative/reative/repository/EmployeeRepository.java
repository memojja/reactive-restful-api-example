package com.reative.reative.repository;

import com.reative.reative.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee,String> {
}
