package septmajorproject.bookingsys.customerTest;

import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import septmajorproject.bookingsys.model.Customer;


import javax.validation.ConstraintViolation;
import javax.xml.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerIntegrationTest {
    private LocalValidatorFactoryBean localValidatorFactory;

    @Before
    public void setup()
    {
        localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();
    }

    @Test
    public void whenInsertCustomerWithBlankUsername_thenReturnFalse()
    {
        Customer newCustomer = new Customer("","tester","s3788167@student.rmit.edu.au",
                "123 abc street","username","0400000000","password", "1E");
        Set<ConstraintViolation<Customer>> constraintViolations = localValidatorFactory.validate(newCustomer);
        assertFalse(constraintViolations.size()==0,"Please enter a last name of at least 1 character length");
    }

    @Test
    public void whenInsertCustomerWithValidFirstname_thenReturnTrue()
    {
        Customer newCustomer = new Customer("Testname","tester","s3788167@student.rmit.edu.au",
                "123 abc street","username","0400000000","password", "1F");
        newCustomer.setIdentificationNumber("1A");
        Set<ConstraintViolation<Customer>> constraintViolations = localValidatorFactory.validate(newCustomer);
        assertTrue(" ",constraintViolations.size()==0);
    }

    @Test
    public void whenInsertCustomerWithBlankLastname_thenReturnFalse()
    {
        Customer newCustomer = new Customer("tester","","s3788167@student.rmit.edu.au",
                "123 abc street","username","0400000000","password", "1C");
        Set<ConstraintViolation<Customer>> constraintViolations = localValidatorFactory.validate(newCustomer);
        assertFalse(constraintViolations.size()==0,"Please enter a last name of at least 1 character length");
    }

    @Test
    public void whenInsertCustomerWithValidLastname_thenReturnTrue()
    {
        Customer newCustomer = new Customer("tester","lastname","s3788167@student.rmit.edu.au",
                "123 abc street","username","0400000000","password", "17");
        newCustomer.setIdentificationNumber("1B");
        Set<ConstraintViolation<Customer>> constraintViolations = localValidatorFactory.validate(newCustomer);
        assertTrue(" ",constraintViolations.size()==0);
    }

    @Test
    public void whenInsertUsernameWithLessThan3Characters_thenReturnFalse()
    {
        Customer newCustomer = new Customer("tester","lastname","s3788167@student.rmit.edu.au",
                "123 abc street","a","0400000000","password", "522");
        Set<ConstraintViolation<Customer>> constraintViolations = localValidatorFactory.validate(newCustomer);
        assertFalse(constraintViolations.size()==0,"Please enter a username between 3-20 characters");
    }

    @Test
    public void whenInsertUsernameWithGreaterThan20Characters_thenReturnFalse()
    {
        Customer newCustomer = new Customer("tester","lastname","s3788167@student.rmit.edu.au",
                "123 abc street","ThisisOverTwentyCharacters","0400000000","password", "h6");
        Set<ConstraintViolation<Customer>> constraintViolations = localValidatorFactory.validate(newCustomer);
        assertFalse(constraintViolations.size()==0,"Please enter a username between 3-20 characters");
    }

    @Test
    public void whenInsertUsernameBetween3and20Characters_thenReturnTrue()
    {
        Customer newCustomer = new Customer("tester","lastname","s3788167@student.rmit.edu.au",
                "123 abc street","betweenNumbers","0400000000","password", "rg87");
        newCustomer.setIdentificationNumber("1D");
        Set<ConstraintViolation<Customer>> constraintViolations = localValidatorFactory.validate(newCustomer);
        assertTrue(" ",constraintViolations.size()==0);
    }

    @Test
    public void whenInsertBlankEmail_thenReturnFalse()
    {
        Customer newCustomer = new Customer("tester","lastname","",
                "123 abc street","username","0400000000","password", "7686");
        Set<ConstraintViolation<Customer>> constraintViolations = localValidatorFactory.validate(newCustomer);
        assertFalse(constraintViolations.size()==0,"Please enter an email address");
    }

    @Test
    public void whenInsertBlankAddress_thenReturnFalse()
    {
        Customer newCustomer = new Customer("tester","lastname","s3788167@student.rmit.edu.au",
                "","username","0400000000","password", "634764");
        Set<ConstraintViolation<Customer>> constraintViolations = localValidatorFactory.validate(newCustomer);
        assertFalse(constraintViolations.size()==0,"Please enter an address");
    }

    @Test
    public void whenInsertBlankPassword_thenReturnFalse()
    {
        Customer newCustomer = new Customer("tester","lastname","s3788167@student.rmit.edu.au",
                "123 abc street","username","0400000000","", "76833");
        Set<ConstraintViolation<Customer>> constraintViolations = localValidatorFactory.validate(newCustomer);
        assertFalse(constraintViolations.size()==0,"Please enter a password");
    }

}
