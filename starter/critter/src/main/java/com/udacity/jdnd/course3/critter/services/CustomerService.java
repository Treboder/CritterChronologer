package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PetRepository petRepository;

    public Customer saveCustomerWithoutPets(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer saveCustomerWithPets(Customer customer, List<Long> petIds) {
        List<Pet> customerPets = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            customerPets = petIds.stream().map((petId) -> petRepository.getOne(petId)).collect(Collectors.toList());
        }
        customer.setPets(customerPets);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent())
            return customer.get();
        else
            return null; // ToDo: throw exception here?
    }

    public Customer getCustomerByPetId(Long petId) { // returns the owner of pet with petId given
        Optional<Pet> pet = petRepository.findById(petId);
        if(pet.isPresent())
            return pet.get().getOwner();
        else
            return null; // ToDo: throw exception here?
    }


}
