package core;

import dto.transformation.EmployeeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(Constants.Converters.CONVERTERS_COMPONENT_NAME)
public class ConverterRegistry {
    @Autowired
    public EmployeeConverter employees;
}
