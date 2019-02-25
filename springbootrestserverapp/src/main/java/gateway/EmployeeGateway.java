package gateway;

import core.Constants;
import core.ConverterRegistry;
import dto.EmployeeData;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import core.ServiceRegistry;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(Constants.Services.EMPLOYEE_ENDPOINT_NAME)
public class EmployeeGateway {

    @Autowired
    ServiceRegistry services;

    @Autowired
    ConverterRegistry converters;

    @GetMapping("/{from}/{to}")
    ResponseEntity findEmployeesByEmploymentDate(
            @PathVariable("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
            @PathVariable("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date to) {
        List<Employee> employees = services.employees.findByEmploymentDate(from, to);
        List<EmployeeData> employeeDataList = converters.employees.convertToList(employees);
        return new ResponseEntity(employeeDataList, HttpStatus.OK);
    }

    @PutMapping("/{id}/{jobTitle}")
    ResponseEntity updateJobTitle(
            @PathVariable(Constants.Repositories.ID_FIELD_NAME) long id,
            @PathVariable("jobTitle") String jobTitle){
        Optional<Employee> updatedEmployee = services.employees.updateJobTitle(id, jobTitle);
        if (!updatedEmployee.isPresent()) {
            return new ResponseEntity(EmployeeData.empty(), HttpStatus.NOT_FOUND);
        }
        EmployeeData employeeData = converters.employees.apply(updatedEmployee.get());
        return new ResponseEntity(employeeData, HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity getAllEmployees() {
        Iterable<Employee> employees = services.employees.getAll();
        List<Employee> employeeList = StreamSupport.
                stream(employees.spliterator(), false)
                .collect(Collectors.toList());
        List<EmployeeData> employeeDataList = converters.employees.convertToList(employeeList);
        return new ResponseEntity(employeeDataList, HttpStatus.OK);
    }

    @GetMapping(Constants.Services.ID_PATH_VARIABLE_NAME)
    ResponseEntity getEmployeeById(@PathVariable(Constants.Repositories.ID_FIELD_NAME) long id) {
        Optional<Employee> employeeById = services.employees.getById(id);
        if (!employeeById.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(employeeById, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addEmployee(@RequestBody Employee employee) throws Exception {
        Employee savedEmployee = services.employees.save(employee);
        if(savedEmployee != null) {
            EmployeeData employeeData = converters.employees.apply(savedEmployee);
            return new ResponseEntity(employeeData, HttpStatus.OK);
        }else {
            return new ResponseEntity(EmployeeData.empty(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    /** Updates employee from a json object
     *  All employee fields must exist in the json object,
     *  otherwise; the call will fail
     **/
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateEmployee(@RequestBody Employee employee) throws Exception {
        if (employee.getId() == Integer.MIN_VALUE) {
            return new ResponseEntity(EmployeeData.empty(), HttpStatus.NOT_ACCEPTABLE);
        }
        Optional<Employee> employeeById = services.employees.getById(employee.getId());
        /* If no employee with this id exists in the db,
            then the response will be http 404 Not Found */
        if (!employeeById.isPresent()) {
            return new ResponseEntity(EmployeeData.empty(), HttpStatus.NOT_FOUND);
        }

        /*
        addEmployee is called here for two reasons
        1- The repository.save() method automatically distinguishes between
          insert and update using the id to indicate that the employee already exists
        2- In order to benefit from its response handling rather than rewriting
        */
        return addEmployee(employee);
    }

    @DeleteMapping(Constants.Services.ID_PATH_VARIABLE_NAME)
    public ResponseEntity deleteEmployee(@PathVariable(Constants.Repositories.ID_FIELD_NAME) long id){
        Optional<Employee> employeeById = services.employees.getById(id);
        if (!employeeById.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        services.employees.delete(employeeById.get());
        return new ResponseEntity(HttpStatus.OK);
    }
}
