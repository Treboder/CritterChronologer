package com.udacity.jdnd.course3.critter.services;


import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Pet> pets = petRepository.findAllById(petIds);
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        schedule.setPets(pets);
        schedule.setEmployee(employees);
        return scheduleRepository.save(schedule);
        // ToDo: Check for availablity first?
    }

    public List<Schedule> getAllSchedules() {
        List<Schedule> allSchedules = scheduleRepository.findAll();
        return allSchedules;
    }

    public List<Schedule> getEmployeeSchedule(Long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        List<Schedule> schedules = scheduleRepository.findByEmployee(employee);
        return schedules;
    }

    public List<Schedule> getPetSchedule(Long petId) {
        Pet pet = petRepository.getOne(petId);
        List<Schedule> schedules = scheduleRepository.findByPets(pet);
        return schedules;
    }

    public List<Schedule> getCustomerSchedule(Long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        List<Schedule> schedules = scheduleRepository.findByPetsIn(customer.getPets());
        return schedules;
    }

}
