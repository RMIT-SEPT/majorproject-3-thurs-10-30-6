package septmajorproject.bookingsys.employeeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import septmajorproject.bookingsys.exception.EmployeeException;
import septmajorproject.bookingsys.model.Employee;
import septmajorproject.bookingsys.repository.EmployeeRepository;
import septmajorproject.bookingsys.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @TestConfiguration
    static class EmployeeServiceContextConfig {
        @Bean
        public EmployeeService employeeService() {
            return new EmployeeService();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp(){
        Employee newEmployeeOne = new Employee("1234", "Alex", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        Employee newEmployeeTwo = new Employee("2345", "Tom", "Anthony", "s36@student.rmit.edu.au", 0424735214, "Something", "s3661672", "password");
        Employee newEmployeeThree = new Employee("3456", "Sam", "Rizzo", "s3661@student.rmit.edu.au", 0424735213, "Something", "s3661673", "password");
        Employee newEmployeeFour = new Employee("4567", "Leigh", "Dean", "s36616@student.rmit.edu.au", 0424735211, "Something", "s3661674", "password");

        List<Employee> testList = new ArrayList<Employee>();
        testList.add(newEmployeeTwo);
        testList.add(newEmployeeThree);
        testList.add(newEmployeeFour);

        Mockito.when(employeeRepository.findByEmployeeIdentifier(newEmployeeOne.getEmployeeIdentifier())).thenReturn(newEmployeeOne);
        Mockito.when(employeeRepository.findByEmail(newEmployeeOne.getEmail())).thenReturn(newEmployeeOne);
        Mockito.when(employeeRepository.findByPhoneNumber(newEmployeeOne.getPhoneNumber())).thenReturn(newEmployeeOne);
        Mockito.when(employeeRepository.findByUserName(newEmployeeOne.getUserName())).thenReturn(newEmployeeOne);
        Mockito.when(employeeRepository.findAll()).thenReturn(testList);
    }

    @Test
    public void whenValidIdentifier_ThenEmployeeShouldBeFound(){
        String id = "1234";

        Employee found = employeeService.findByEmployeeIdentifier(id);

        assertThat(found.getEmployeeIdentifier().equals(id));
    }

    @Test(expected = EmployeeException.class)
    public void whenInValidIdentifier_ThrowEmployeeException(){
        String id = "1111";

        Employee found = employeeService.findByEmployeeIdentifier(id);
    }

    @Test
    public void whenValidUserName_ThenEmployeeShouldBeFound(){
        String userName = "s3661671";

        Employee found = employeeService.findByEmployeeUserName(userName);

        assertThat(found.getUserName().equals(userName));
    }

    @Test(expected = EmployeeException.class)
    public void whenInValidUserName_ThrowEmployeeException(){
        String userName = "s";

        Employee found = employeeService.findByEmployeeUserName(userName);
    }

    @Test
    public void whenValidEmail_ThenEmployeeShouldBeFound(){
        String email = "s3661671@student.rmit.edu.au";

        Employee found = employeeService.findByEmployeeEmail(email);

        assertThat(found.getEmail().equals(email));
    }

    @Test(expected = EmployeeException.class)
    public void whenInValidEmail_ThenThrowEmployeeException(){
        String email = "s";
        Employee found = employeeService.findByEmployeeEmail(email);
    }

    @Test
    public void whenValidPhoneNumber_ThenEmployeeShouldBeFound(){
        int phoneNumber = 0424735215;

        Employee found = employeeService.findEmployeeByPhoneNumber(phoneNumber);

        assertThat(found.getPhoneNumber() == phoneNumber);
    }

    @Test(expected = EmployeeException.class)
    public void whenInValidPhoneNumber_ThenThrowEmployeeException(){
        int phoneNumber = 0;

        Employee found = employeeService.findEmployeeByPhoneNumber(phoneNumber);
    }

    @Test
    public void getAllEmployees_returnListOFEmployees(){
        assertThat(employeeService.findAllEmployees().size() == 3);
    }



}
