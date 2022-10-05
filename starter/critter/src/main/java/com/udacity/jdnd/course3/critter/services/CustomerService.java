package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;

    public Customer saveCustomerWithoutPets(Customer customer) {
        return customerRepository.save(customer);
    }

//    public Customer saveCustomerWithPets(Customer customer, List<Long> petIds) {
//        List<Pet> customerPets = new ArrayList<>();
//        if (petIds != null && !petIds.isEmpty()) {
//            customerPets = petIds.stream().map((petId) -> petRepository.getOne(petId)).collect(Collectors.toList()); // ToDo: Replace deprecated method
//        }
//        customer.setPets(customerPets);
//        return customerRepository.save(customer);
//    }
//
//    public Customer getCustomerByPetId(Long petId) {
//        Customer customer = petRepository.getOne(petId).getOwner();
//        return customer;
//    }
//
//    public List<Customer> getAllCustomers() {
//        List<Customer> customers = customerRepository.findAll();
//        return customers;
//    }
}
