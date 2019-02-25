package dto.transformation;

import core.Constants;
import core.base.DataConverter;
import dto.EmployeeData;
import model.Employee;
import org.springframework.stereotype.Component;

@Component(Constants.Converters.EMPLOYEE_CONVERTER_NAME)
public class EmployeeConverter implements DataConverter<Employee, EmployeeData> {
    @Override
    public EmployeeData apply(final Employee source) {
        final EmployeeData employeeData = new EmployeeData();
        employeeData.setId(source.getId());
        employeeData.setName(source.getName());
        employeeData.setDepartment(source.getDepartment());
        employeeData.setJobTitle(source.getJobTitle());
        employeeData.setEmploymentDate(source.getEmploymentDate().toString());
        return employeeData;
    }
}
