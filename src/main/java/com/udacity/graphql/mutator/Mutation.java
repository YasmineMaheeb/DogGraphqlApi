package com.udacity.graphql.mutator;


import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import com.udacity.graphql.exception.DogNotFoundException;
import com.udacity.graphql.mapper.DogMapper;
import com.udacity.graphql.model.Dog;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private DogMapper dogMapper;

    public Mutation(DogMapper dogMapper) {
        this.dogMapper = dogMapper;
    }

    public boolean deleteDogBreed(String breed) {
        ArrayList<Dog> dogs = (ArrayList<Dog>) dogMapper.findAll();
        ArrayList<Dog> breedDogs = new ArrayList<>();
        for (Dog d: dogs)
            if (d.getBreed().equals(breed))
                breedDogs.add(d);
        dogMapper.deleteAll(breedDogs);
        return true;
    }

    public Dog updateDogName(String newName, Long id) {
        Optional<Dog> optionalDog =
                dogMapper.findById(id);

        if(optionalDog.isPresent()) {
            Dog dog = optionalDog.get();
            dog.setName(newName);
            dogMapper.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}
