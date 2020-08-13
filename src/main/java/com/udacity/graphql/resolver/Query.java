package com.udacity.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udacity.graphql.exception.DogNotFoundException;
import com.udacity.graphql.mapper.DogMapper;
import com.udacity.graphql.model.Dog;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private DogMapper dogMapper;

    public Query(DogMapper dogMapper) {
        this.dogMapper = dogMapper;
    }

    public Iterable<Dog> findAllDogs() {
        return dogMapper.findAll();
    }

    public Dog findDogById (Long id) {
        Optional<Dog> dog = dogMapper.findById(id);
        if (dog.isPresent()){
            return dog.get();
        }
        throw new DogNotFoundException("Dog not found", id);
    }
}
