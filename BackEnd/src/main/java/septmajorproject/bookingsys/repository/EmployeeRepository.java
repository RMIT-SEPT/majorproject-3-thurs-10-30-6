package septmajorproject.bookingsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import septmajorproject.bookingsys.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Override
    //returns all employees
    List<Employee> findAll();

    //returns employee by unique username
    Employee findByUserName(String userName);

    //get employee using username and password
    Employee findByUserNameAndPassword(String username, String password);

    //returns employee by there unique email
    Employee findByEmail(String email);

    //find employee by unique phone number
    Employee findByPhoneNumber(int phoneNumber);

    //Find by employeeIdentifier
    Employee findByEmployeeIdentifier(String id);
}
