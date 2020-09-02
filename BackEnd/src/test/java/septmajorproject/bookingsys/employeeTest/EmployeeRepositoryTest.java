package septmajorproject.bookingsys.employeeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import septmajorproject.bookingsys.model.Employee;
import septmajorproject.bookingsys.repository.EmployeeRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager testManager;

    @Autowired
    private EmployeeRepository employeeRepository;



    //generates test data and constructs testing objects
    @Before
    public void setUp() {
        Employee newEmployeeOne = new Employee("1234", "Alex", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");
        Employee newEmployeeTwo = new Employee("2345", "Tom", "Anthony", "s36@student.rmit.edu.au", 0424735214, "Something", "s3661672", "password");
        Employee newEmployeeThree = new Employee("3456", "Sam", "Rizzo", "s3661@student.rmit.edu.au", 0424735213, "Something", "s3661673", "password");
        Employee newEmployeeFour = new Employee("4567", "Leigh", "Dean", "s36616@student.rmit.edu.au", 0424735211, "Something", "s3661674", "password");


        testManager.persist(newEmployeeOne);
        testManager.persist(newEmployeeTwo);
        testManager.persist(newEmployeeThree);
        testManager.persist(newEmployeeFour);

        testManager.flush();
    }

    //finds user by Id PASS

    //find employee by email PASS
    @Test
    public void findEmployeeWithEmail_ReturnEmployee(){
        Employee foundEmployee = employeeRepository.findByEmail("s3661671@student.rmit.edu.au");


        assertThat(foundEmployee.getFirstName().equals("Alex"));
    }

    //find employee by email FAIL
    @Test
    public void findEmployeeWithIncorrectEmail_thenReturnNull(){
        Employee foundEmployee = employeeRepository.findByEmail("NO_EMAIL");

        assertThat(foundEmployee == null);
    }

    //find employee by phone number PASS
    @Test
    public void findEmployeeWithPhoneNumber(){
        Employee foundEmployee = employeeRepository.findByPhoneNumber(0424735215);

        assertThat(foundEmployee.getFirstName().equals("Alex"));
    }

    //find employee by phone number FAIL
    @Test
    public void findEmployeeWithIncorrectPhoneNumber_thenReturnNull(){
        Employee foundEmployee = employeeRepository.findByPhoneNumber(7888);

        assertThat(foundEmployee == null);
    }

    //find employee by userName PASS
    @Test
    public void findEmployeeWithUserName_thenReturnEmployee(){
        Employee foundEmployee = employeeRepository.findByUserName("s3661671");

        assertThat(foundEmployee.getFirstName().equals("Alex"));
    }


    //find employee by username FAIL
    @Test
    public void findEmployeeWithIncorrectUserName_thenReturnNull(){
        Employee foundEmployee = employeeRepository.findByUserName("NO_USER");

        assertThat(foundEmployee == null);
    }

    //Return list of employees
    @Test
    public void getAllEmployees_thenReturnListOfEmployees(){
        List<Employee> employeeList = employeeRepository.findAll();

        assertThat(employeeList.size() == 4);
    }

    @Test
    public void getEmployeeByValidIdentifier_thenReturnEmployee(){
        Employee found = employeeRepository.findByEmployeeIdentifier("1234");

        assertThat(found.getFirstName().equals("Alex"));
    }

    @Test
    public void getEmployeeWithInCorrectIdentifier_theReturnNull(){
        Employee found = employeeRepository.findByEmployeeIdentifier(("1111"));

        assertThat(found == null);
    }


}