package com.enroll.enrollment.controller;

import com.enroll.enrollment.dto.DependentDTO;
import com.enroll.enrollment.dto.EnrolleeDTO;
import com.enroll.enrollment.model.Dependents;
import com.enroll.enrollment.model.Enrollees;
import com.enroll.enrollment.sevice.EnrolleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.MediaTypes;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/enrolle")
public class EnrolleeController {

    @Autowired
    EnrolleeService enrolleeService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Enrollees getEnrolle(@PathVariable("id") long id){
       return enrolleeService.getEnrolleById(id);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Enrollees> getAllEnrolles(){
        return enrolleeService.getEnrolles();
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Enrollees insertEnrolle(@Valid @RequestBody EnrolleeDTO enrolleeDto){
        return enrolleeService.addEnrolle(enrolleeDto);
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Enrollees updateEnrolle(@Valid @RequestBody EnrolleeDTO enrolleeDTO){
        return enrolleeService.updateEnrolle(enrolleeDTO);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteEnrolle(@PathVariable("id") long id){
        return enrolleeService.deleteEnrolle(id);
    }

    @PostMapping(value = "{id}/dependents/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Enrollees insertDependents(@RequestBody DependentDTO dependent){
        return enrolleeService.addDependent(dependent);
    }

    @PutMapping(value = "{id}/dependents/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Enrollees updateDependents(@RequestBody DependentDTO dependentDTO){
        return enrolleeService.updateDependent(dependentDTO);
    }

    @DeleteMapping(value = "{id}/dependents/{dependentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Enrollees removeDependents(@PathVariable("id") long id, @PathVariable("dependentId") long dependentId){
        return enrolleeService.removeDependents(id, dependentId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
