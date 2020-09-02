package septmajorproject.bookingsys.employeeTest;

import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import septmajorproject.bookingsys.model.Employee;


import javax.validation.ConstraintViolation;
import javax.xml.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeTest {

    private Validator validator;
    private LocalValidatorFactoryBean localValidatorFactory;


    //generates test data and constructs testing objects
    @Before
    public void setUp(){

        localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();

    }

    //testing employee firstName with lest then three character
    @Test
    public void whenInsertEmployeeFirstNameWithWrongSize_thenReturnFalse(){
        Employee newEmployee = new Employee("2345", "A", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);

        assertFalse(constraintViolations.size() == 0, "Enter a Valid first name that is greater than three characters.");
    }

    //testing what happens if the firstname is greater than three charcters
    @Test
    public void whenInsertFisrtNameGreaterThanThreeCharacter_thenReturnTrue(){
        Employee newEmployee = new Employee("3456","AAA", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);
        assertTrue( " ", constraintViolations.size() == 0);
    }

    //testing what happens iof the lastname is les than three charcters
    @Test
    public void whenInsertLastnameLessThanThreeCharcters_thenReturnFalse(){
        Employee newEmployee = new Employee("4567", "A", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);
        assertFalse(constraintViolations.size() == 0, "Enter a Valid first name that is greater than two characters.");
    }

    //testing what happens if the last name is greater than three charcters
    @Test
    public void whenInsertLastnameGreaterThanThreeCharcters_thenReturnTrue(){
        Employee newEmployee = new Employee("5678", "AAA", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);
        assertTrue( " ", constraintViolations.size() == 0);
    }

    //testing what happens if the firstname is blank
    @Test
    public void insertFirstNameThatIsBlank_thenReturnFalse(){
        Employee newEmployee = new Employee("6789", "", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);
        assertFalse(constraintViolations.size() == 0, "firstName must not be blank.");
    }

    //testing what happens if the lastname is blank
    @Test
    public void insertLastNameThatIsBlank_theReturnTrue(){
        Employee newEmployee = new Employee("7890", "AAA", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);
        assertTrue( " ", constraintViolations.size() == 0);
    }

    //testing what happens when email is blank
    @Test
    public void insertEmailThatIsBlank_ThenReturnFalse(){
        Employee newEmployee = new Employee("8901","AAAA", "Test", "", 0424735215, "Something", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);
        assertFalse(constraintViolations.size() == 0, "Email must not be blank.");
    }

    //testing what happens when the email is not blank
    @Test
    public void insertEmailThatIsNotBlank_thenReturnTrue(){
        Employee newEmployee = new Employee("7685", "AAA", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);
        assertTrue( " ", constraintViolations.size() == 0);
    }

    //testing what happens if the address is blank
    @Test
    public void insertAddressThatIsBlank_thenReturnFalse(){
        Employee newEmployee = new Employee("3484", "AAAA", "Test", "s3661671@student.rmit.edu.au", 0424735215, "", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);
        assertFalse(constraintViolations.size() == 0, "Address must not be blank.");
    }

    //testing that the address is not blank
    @Test
    public void insertAddressThatIsNotBlank_thenReturnTrue(){
        Employee newEmployee = new Employee("1010", "AAA", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);
        assertTrue( " ", constraintViolations.size() == 0);
    }

    //testing to see if the username is blank
    @Test
    public void insertUserNameThatIsBlank_thenReturnFalse(){
        Employee newEmployee = new Employee("1111", "AAAA", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);
        assertFalse(constraintViolations.size() == 0, "Username must not be blank");
    }

    //testing to see if the username is blank
    @Test
    public void insertUserNameThatIsNotBlank_thenReturnTrue(){
        Employee newEmployee = new Employee("2222", "AAA", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);
        assertTrue( " ", constraintViolations.size() == 0);
    }

    //testing what happens if the password is empty
    @Test
    public void insertPasswordThatIsBlank_thenReturnFalse(){
        Employee newEmployee = new Employee("3333", "AAAA", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);
        assertFalse(constraintViolations.size() == 0, "Password must not be blank");
    }

    //testing the password is not empty
    @Test
    public void insertPasswordThatIsNotBlank_thenReturnTrue(){
        Employee newEmployee = new Employee("4444", "AAA", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);
        assertTrue( " ", constraintViolations.size() == 0);
    }

    @Test
    public void insertIdentifierThatIsBlank_thenReturnCanNotBeBlank(){
        Employee newEmployee = new Employee("", "AAA", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);

        assertFalse(constraintViolations.size() == 0, "");
    }

    @Test
    public void insertCorrectIdentifier_returnTrue(){
        Employee newEmployee = new Employee("6754", "AAA", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        Set<ConstraintViolation<Employee>> constraintViolations = localValidatorFactory.validate(newEmployee);

        assertTrue(" ", constraintViolations.size() == 0);
    }
}
