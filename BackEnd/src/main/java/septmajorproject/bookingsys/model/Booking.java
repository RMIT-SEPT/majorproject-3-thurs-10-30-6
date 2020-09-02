package septmajorproject.bookingsys.model;


import javax.persistence.*;

@Entity
@Table(name = "BOOKING")
/**
 * Booking entity, has a connection between customer entity and employee entity, both
 * many to one connections.
 */
public class Booking {

    @EmbeddedId
    private BookingPK bookingPK;

    //many to one connection initialization for Employee table
    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employee;

    //many to one connection initialization for Customer table
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Booking() {
    }

    /**
     * Constructor
     * @param bookingPK : Composite key for booking table
     * @param employee : Employee table entry
     * @param customer : customer table entry
     */
    public Booking(BookingPK bookingPK, Employee employee, Customer customer) {
        this.bookingPK = bookingPK;
        this.employee = employee;
        this.customer = customer;
    }

    /**
     * get employee
     * @return employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * get customer
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * set employee to the given object of employee
     * @param employee: employee object
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * set customer to the given object of customer
     * @param customer: customer object
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Booking table composite key set function
     * @param bookingPK: booking table composite key
     */
    public void setBookingPK(BookingPK bookingPK) {
        this.bookingPK = bookingPK;
    }

    /**
     * get the booking table compostie key
     * @return composite key
     */
    public BookingPK getBookingPK() {
        return bookingPK;
    }
}
