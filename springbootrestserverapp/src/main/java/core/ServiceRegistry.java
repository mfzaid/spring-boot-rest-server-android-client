package core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.EmployeeService;

@Component(Constants.Services.SERVICES_COMPONENT_NAME)
public class ServiceRegistry {
    @Autowired
    public EmployeeService employees;
}
