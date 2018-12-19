package com.calcard.cobranca.repository;

import com.calcard.cobranca.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
