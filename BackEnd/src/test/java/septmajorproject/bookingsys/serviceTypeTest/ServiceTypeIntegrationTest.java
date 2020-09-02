package septmajorproject.bookingsys.serviceTypeTest;

import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import septmajorproject.bookingsys.model.Customer;
import septmajorproject.bookingsys.model.Employee;
import septmajorproject.bookingsys.model.ServiceType;


import javax.validation.ConstraintViolation;
import javax.xml.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceTypeIntegrationTest {
    private Validator validator;
    private LocalValidatorFactoryBean localValidatorFactory;

    //generates test data and constructs testing objects
    @Before
    public void setUp(){
        localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();

    }

    //testing what happens if the service number is blank
    @Test
    public void whenInsertServiceTypeWithBlankServiceNo_thenReturnFalse(){
        ServiceType newServiceType = new ServiceType("","Test");

        Set<ConstraintViolation<ServiceType>> constraintViolations = localValidatorFactory.validate(newServiceType);
        assertFalse(constraintViolations.size() == 0, "service number must not be blank.");

    }

    //testing what happens if the service number is  not blank
    @Test
    public void whenInsertServiceTypeWithValidServiceNo_thenReturnTrue(){
        ServiceType newServiceType = new ServiceType("testNo","Test");

        Set<ConstraintViolation<ServiceType>> constraintViolations = localValidatorFactory.validate(newServiceType);
        assertTrue(" ",constraintViolations.size()==0);
    }

    //testing what happens if the service name is blank
    @Test
    public void whenInsertServiceTypeWithBlankServiceName_thenReturnFalse(){
        ServiceType newServiceType = new ServiceType("test","");

        Set<ConstraintViolation<ServiceType>> constraintViolations = localValidatorFactory.validate(newServiceType);
        assertFalse(constraintViolations.size() == 0, "service name must not be blank.");
    }

    //testing what happens if the service name is not blank
    public void whenInsertServiceTypeWithValidServiceName_thenReturnTrue(){
        ServiceType newServiceType = new ServiceType("test","Testame");

        Set<ConstraintViolation<ServiceType>> constraintViolations = localValidatorFactory.validate(newServiceType);
        assertTrue(" ",constraintViolations.size()==0);
    }


}
