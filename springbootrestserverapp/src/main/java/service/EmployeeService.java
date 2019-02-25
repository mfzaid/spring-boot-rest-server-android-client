package service;

import core.Constants;
import core.base.BaseService;
import model.Employee;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service(Constants.Services.EMPLOYEE_SERVICE_NAME)
public class EmployeeService extends BaseService<Employee> {

    @Override
    public Iterable<Employee> getAll(){
        Iterable<Employee> employees = repositories.employees.findAll();
        return employees;
    }

    @Override
    public Optional<Employee> getById(long id){
        Optional<Employee> employeeById = repositories.employees.findById(id);
        return employeeById;
    }

    @Override
    public Employee save(Employee employee){
        employee.setLastAudit(new Date());
        Employee savedEmployee = repositories.employees.save(employee);
        return savedEmployee;
    }

    @Override
    public void delete(Employee employee){
        repositories.employees.delete(employee);
    }

    public List<Employee> findByEmploymentDate(Date from, Date to){
        List<Employee> byEmploymentDate = repositories.employees.findByEmploymentDate(from, to);
        return byEmploymentDate;
    }

    public Optional<Employee> updateJobTitle(long id, String jobTitle){
        boolean employeeExists = repositories.employees.existsById(id);
        if(employeeExists) {
            Employee employeeById = repositories.employees.findById(id).get();
            employeeById.setJobTitle(jobTitle);
            employeeById.setLastAudit(new Date());
            return Optional.of(repositories.employees.save(employeeById));
        }else {
            return Optional.empty();
        }
    }
}
