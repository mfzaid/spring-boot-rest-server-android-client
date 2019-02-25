package repository;

import core.Constants;
import model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    /**
     * If you need to fetch employees by a single field,
     * then just define a signature like the following:
     * ---------------------------------------
     * List<Employee> findByName(String name);
     * ---------------------------------------
     * The repository will automatically lookup a declared field <name> in the entity
     * and automatically create a query to find employees using this field.
     **/
    List<Employee> findByName(String name);

    /**
     * Custom Query method
     * Method parameters must be referenced [either] by their index ?1, ?2
     * if the method parameters are not annotated by @Param tag
     * [or] by annotation their names :from, :to if the method parameters are annotated
     * by @Param("from") and @Param("to")
     * @param from lower limit
     * @param to upper limit
     * @return List of employees employed between from and to dates
     **/
    @Query(value = Constants.Repositories.Queries.Employees.EMPLOYMENT_DATE_QUERY, nativeQuery = true)
    List<Employee> findByEmploymentDate(@Param("from") Date from, @Param("to") Date to);
}
