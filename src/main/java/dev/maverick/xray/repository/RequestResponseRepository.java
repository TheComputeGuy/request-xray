package dev.maverick.xray.repository;

import org.springframework.data.repository.CrudRepository;

import dev.maverick.xray.model.RequestResponse;

/**
 * 
 * JPA CRUD repository to talk to DB and store request-response data
 * 
 * @author Shubham Pharande
 *
 */
public interface RequestResponseRepository extends CrudRepository<RequestResponse, Long> {

}
