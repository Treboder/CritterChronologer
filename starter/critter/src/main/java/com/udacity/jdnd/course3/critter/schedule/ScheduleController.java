package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule(scheduleDTO.getDate(), scheduleDTO.getActivities());;
        ScheduleDTO convertedSchedule;
        try {
            schedule = scheduleService.saveSchedule(schedule, scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds());
            schedule.getId(); // will throw the exception in case of a null value returned, because employee(s) or pet(s) do not exist
            convertedSchedule = convertScheduleToScheduleDTO(schedule);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule could not be saved", exception);
        }
        return convertedSchedule;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return schedules.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules;
        try {
            schedules = scheduleService.getPetSchedule(petId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet schedule with id: " + petId + " not found", exception);
        }
        return schedules.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules;
        try {
            schedules = scheduleService.getEmployeeSchedule(employeeId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee schedule with employee id: " + employeeId + " not found", exception);
        }
        return schedules.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules;
        try {
            schedules = scheduleService.getCustomerSchedule(customerId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule with owner id " + customerId + " not found", exception);
        }
        return schedules.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        List<Long> employeeIds = schedule.getEmployee().stream().map(Employee::getId).collect(Collectors.toList());
        List<Long> petIds = schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList());

        return new ScheduleDTO(schedule.getId(), employeeIds, petIds, schedule.getDate(), schedule.getActivities());
    }


}
