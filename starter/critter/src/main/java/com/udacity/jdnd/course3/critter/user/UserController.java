package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getPhoneNumber(), customerDTO.getNotes(), new ArrayList<Pet>());
        CustomerDTO convertedCustomer;
        try {
            convertedCustomer = convertCustomerToCustomerDTO(customerService.saveCustomerWithoutPets(customer));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer could not be saved", exception);
        }
        return convertedCustomer;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream().map(this::convertCustomerToCustomerDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomerById(@PathVariable long id) {
        Customer customer;
        try {
            customer = customerService.getCustomerById(id);
            customer.getId(); // this will trigger the exception in case of a null value returned from service
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "owner with id: " + id + " not found", exception);
        }
        return convertCustomerToCustomerDTO(customer);
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer;
        try {
            customer = customerService.getCustomerByPetId(petId);
            customer.getId(); // this will trigger the exception in case of a null value returned from service
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Owner pet with id: " + petId + " not found", exception);
        }
        return convertCustomerToCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getSkills(), employeeDTO.getDaysAvailable());
        EmployeeDTO convertedEmployee;
        try {
            Employee managedEmployee = employeeService.saveEmployee(employee);
            convertedEmployee = convertEmployeeToEmployeeDTO(managedEmployee);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee could not be saved", exception);
        }
        return convertedEmployee;
    }

    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream().map(this::convertEmployeeToEmployeeDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable long employeeId) {
        Employee employee;
        try {
            employee = employeeService.getEmployeeById(employeeId);
            employee.getId(); // this will trigger the exception in case of a null value returned from service
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "employee with id: " + employeeId + " not found", exception);
        }
        return convertEmployeeToEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        try {
            employeeService.setEmployeeAvailability(daysAvailable, employeeId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee with id: " + employeeId + " not found", exception);
        }
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.getEmployeesByService(employeeDTO.getDate(), employeeDTO.getSkills());
        return employees.stream().map(this::convertEmployeeToEmployeeDTO).collect(Collectors.toList());
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        List<Long> petIds = customer.getPets().stream().map(Pet::getId).collect(Collectors.toList());
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getPhoneNumber(), customer.getNotes(), petIds);
        // BeanUtils.copyProperties(customer, customerDTO); will not transfer the pet objects to their corresponding pet id list
        return customerDTO;
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO(employee.getId(), employee.getName(), employee.getSkills(), employee.getDaysAvailable());
        // BeanUtils.copyProperties does not transfer the skills properly
        return dto;
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        return customer;
    }

    private Employee convertEmployeeDTOToEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        return employee;
    }



}
