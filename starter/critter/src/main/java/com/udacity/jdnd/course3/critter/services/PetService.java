package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PetRepository petRepository;

    public Pet savePet(Pet pet, Long customerId) {
        // update the pet with its customer/owner fetched by id
        Customer customer = customerRepository.getOne(customerId); // ToDo: replace deprecated method
        pet.setOwner(customer);
        pet = petRepository.save(pet);
        // update the owners/customers pet list
        List<Pet> pets = new ArrayList<>();
        pets.add(pet);
        customer.setPets(pets);
        customerRepository.save(customer);
        // return the managed pet
        return pet;
    }

//    public List<Pet> getPetsByCustomerId(long customerId) {
//        List<Pet> pets = petRepository.findPetByCustomerId(customerId);
//        return pets;
//    }

    public List<Pet> getAllPets() {
        List<Pet> pets = petRepository.findAll();
        return pets;
    }

    public Pet getPetById(Long petId) {
        Pet pet = petRepository.getOne(petId);
        return pet;
    }

}