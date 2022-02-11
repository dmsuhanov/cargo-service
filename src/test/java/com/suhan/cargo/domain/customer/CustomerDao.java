package com.suhan.cargo.domain.customer;

import com.suhan.cargo.domain.customer.CustomerEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerDao extends ReactiveCrudRepository<CustomerEntity, UUID> {

}
