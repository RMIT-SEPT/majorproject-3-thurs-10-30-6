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
import septmajorproject.bookingsys.repository.CustomerRepository;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryIntegrationTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CustomerRepository customerRepository;

    private LocalValidatorFactoryBean localValidatorFactory;

    @Before
    public void setup()
    {
        Customer Customer1 = new Customer("John","Test","s3788167@student.rmit.edu.au",
                "123 this street","johntest","0400000000","password", "62353guh");
        Customer1.setIdentificationNumber("1E");
        Customer Customer2 = new Customer("Jake","Smith","s3788168@student.rmit.edu.au",
                "123 this street","jaketest","0400000000","password", "74368");
        Customer2.setIdentificationNumber("2E");
        Customer Customer3 = new Customer("Jess","Smith","s3788169@student.rmit.edu.au",
                "123 this street","jesstest","0400000000","password", "473686");
        Customer3.setIdentificationNumber("3E");
        Customer Customer4 = new Customer("Jill","Smith","s3788168@student.rmit.edu.au",
                "123 this street","jilltest","0400000000","password", "643864");
        Customer4.setIdentificationNumber("4E");


        entityManager.persist(Customer1);
        entityManager.persist(Customer2);
        entityManager.persist(Customer3);
        entityManager.persist(Customer4);

        entityManager.flush();

        localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();
    }

    @Test
    public void whenCustomerExistsWithUsername_thenReturnCustomer()
    {
        Customer test = customerRepository.findByUsername("johntest");
        assertThat(test.getFirstName().equals("John"));
    }

    @Test
    public void whenCustomerDoesNotExistWithUsername_thenReturnNull()
    {
        Customer test = customerRepository.findByUsername("This isn't a username");
        assertThat(test == null);
    }

    @Test
    public void whenCustomerExistsWithFirstname_thenReturnCustomer()
    {
        Customer test = customerRepository.findByFirstName("Jess");
        assertThat(test.getFirstName().equals("Jess"));
    }



}

