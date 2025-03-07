package septmajorproject.bookingsys.bookingTest;


import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import septmajorproject.bookingsys.model.Booking;
import septmajorproject.bookingsys.model.Customer;
import septmajorproject.bookingsys.model.Employee;
import septmajorproject.bookingsys.model.ServiceType;

import javax.validation.ConstraintViolation;
import javax.xml.validation.Validator;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookingIntegrationTest {

    private Validator validator;
    private LocalValidatorFactoryBean localValidatorFactory;


    Time time = new Time(12,30,0);
    Date date = new Date(2020,8,27);

    Employee emp = new Employee("1234","Bob", "Smith", "bob@smith.com", "39593925", "123 street", "anotherOne", "test");
    Customer cust = new Customer("test", "test@email.com", "Julz", "riz", "123 street", "04373847545", "testSomething");


    @Before
    public void setUp() {
        localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();
    }

    // Testing a bookingPK with no time specified
    @Test
    public void whenInsertBookingWithNoTime_thenReturnFalse() {
        Booking nullTime = new Booking();
        nullTime.setRosterDate(date);
        nullTime.setEmployee(emp);
        nullTime.setCustomer(cust);

        Set<ConstraintViolation<Booking>> constraintViolations = localValidatorFactory.validate(nullTime);

        assertFalse(constraintViolations.size() == 0, "Booking must have a time attached");
    }

    // Testing a bookingPK with no date specified
    @Test
    public void whenInsertBookingWithNoDate_thenReturnFalse() {
        Booking nullDate = new Booking();
        nullDate.setRosterTime(time);
        nullDate.setEmployee(emp);
        nullDate.setCustomer(cust);

        Set<ConstraintViolation<Booking>> constraintViolations = localValidatorFactory.validate(nullDate);

        assertFalse(constraintViolations.size() == 0, "Booking must have a date attached");
    }

    //Testing a booking with no customer attached
    @Test
    public void whenInsertBookingWithNoCustomer_thenReturnFalse() {
        Booking booking = new Booking();
        booking.setRosterDate(date);
        booking.setRosterTime(time);
        booking.setEmployee(emp);

        Set<ConstraintViolation<Booking>> constraintViolations = localValidatorFactory.validate(booking);

        assertFalse(constraintViolations.size() == 0, "Booking must have a customer attached");
    }

    //Testing a booking with no employee attached
    @Test
    public void whenInsertBookingWithNoEmployee_thenReturnFalse() {
        Booking booking = new Booking();
        booking.setRosterDate(date);
        booking.setRosterTime(time);
        booking.setCustomer(cust);

        Set<ConstraintViolation<Booking>> constraintViolations = localValidatorFactory.validate(booking);

        assertFalse(constraintViolations.size() == 0, "Booking must have an employee attached");
    }

    // Testing a booking with valid customer, employee and booking data
    @Test
    public void whenInsertBookingWithValidValues_thenReturnTrue() {
        ServiceType serviceType = new ServiceType("1", "haircut","08:00","20:00");
        Booking booking = new Booking(date, time, emp, cust, serviceType);

        Set<ConstraintViolation<Booking>> constraintViolations = localValidatorFactory.validate(booking);

        assertTrue(constraintViolations.size() == 0);
    }

}
