package com.udacity.graphql.mapper;


import com.udacity.graphql.model.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogMapper extends CrudRepository<Dog, Long> {
}
