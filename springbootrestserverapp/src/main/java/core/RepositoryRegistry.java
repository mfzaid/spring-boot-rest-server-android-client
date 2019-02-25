package core;

import repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(Constants.Repositories.REPOSITORIES_COMPONENT_NAME)
public class RepositoryRegistry {
    @Autowired
    public EmployeeRepository employees;
}
