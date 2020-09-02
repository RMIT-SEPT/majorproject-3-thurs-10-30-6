package septmajorproject.bookingsys.serviceTypeTest;

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
import septmajorproject.bookingsys.model.Employee;
import septmajorproject.bookingsys.model.ServiceType;
import septmajorproject.bookingsys.repository.EmployeeRepository;
import septmajorproject.bookingsys.repository.ServiceTypeRepository;

import javax.xml.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceTypeRepositoryIntegrationTest {
    @Autowired
     TestEntityManager testManager;

    @Autowired
     ServiceTypeRepository serviceTypeRepository;


    //generates test data and constructs testing objects
//    @Before
//    public void setup(){
//        ServiceType ServiceType1 = new ServiceType("RC01","Room Cleaning");
//        ServiceType ServiceType2 = new ServiceType("HC02","House Cleaning");
//        ServiceType ServiceType3 = new ServiceType("CW01","Car Wash");
//        ServiceType ServiceType4 = new ServiceType("BS01","babysitter");
//
//        testManager.persist(ServiceType1);
//        testManager.persist(ServiceType2);
//        testManager.persist(ServiceType3);
//        testManager.persist(ServiceType4);
//
//        testManager.flush();
//
//    }

//    //finds Service type  by Service number
//    @Test
//    public void whenFindServiceTypeByNo_thenReturnServiceName(){
//        ServiceType foundServiceType = serviceTypeRepository.findByServiceNo("RC01");
//
//        assertThat(foundServiceType.getServiceName().equals("Room Cleaning"));
//
//    }
//    //finds Service type  by Service name
//    @Test
//    public void whenFindServiceTypeByServiceName_thenReturnServiceName(){
//        ServiceType foundServiceType = serviceTypeRepository.findByServiceName("House Cleaning");
//
//        assertThat(foundServiceType.getServiceName().equals("House Cleaning"));
//
//    }


    //service type doesnt exist with provided service name
    @Test
    public void whenServiceTypeDoesntExistWithServiceName_thenReturnNull(){
        ServiceType ServiceType1 = new ServiceType("RC01","Room Cleaning");
        testManager.persist(ServiceType1);


        ServiceType foundServiceType = serviceTypeRepository.findByServiceName("R");

        assertThat(foundServiceType == null);
    }

}

