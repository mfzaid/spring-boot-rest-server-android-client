package core;

import java.text.SimpleDateFormat;

public interface Constants {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public interface Repositories {
        public static final String ID_FIELD_NAME = "id";
        public static final String REPOSITORIES_COMPONENT_NAME = "repositories";

        public interface Queries {
            public interface Employees {
                public static final String EMPLOYMENT_DATE_QUERY =
                        "select * from employee where employment_date between :from and :to";
            }
        }
    }
    public interface Services {
        public static final String SERVICES_COMPONENT_NAME = "services";

        public static final String BASE_SERVICE_NAME = "baseService";
        public static final String ID_PATH_VARIABLE_NAME = "/{" + Constants.Repositories.ID_FIELD_NAME + "}";

        public static final String EMPLOYEE_SERVICE_NAME = "employeeService";
        public static final String EMPLOYEE_ENDPOINT_NAME = "/employees";
    }
    public interface Converters {
        public static final String CONVERTERS_COMPONENT_NAME = "converters";
        public static final String EMPLOYEE_CONVERTER_NAME = "employeeConverter";

    }
}
