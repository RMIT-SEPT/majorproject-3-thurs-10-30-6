package septmajorproject.bookingsys.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
    //Customer ID is the primary key, it is just simply a unique integer used to identify each customer
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Integer id;

    @NotBlank(message="Identification number cannot be blank")
    @Column(updatable = false, unique = true)
    private String identificationNumber;
    //Password will be stored as an encrypted string
    @NotBlank(message = "Please enter a password")
    private String password;
    //Email will be checked using a regular expression prior to being stored in the table
    @NotBlank(message = "Please enter an email address")
    private String email;
    //Firstname will be checked against a regular expression prior to being stored in the database,
    //the @Size tag is used to ensure the firstname field is atleast 1 character long
    @Size(min=1, max = Integer.MAX_VALUE, message = "Please enter a first name of at least 1 character length")
    @NotBlank(message = "First Name can not be Blank")
    private String firstName;
    //Similarly to the firstname, lastname will also being enforced as being atleast one character long
    @Size(min=1,max = Integer.MAX_VALUE,message = "Please enter a last name of at least 1 character length")
    @NotBlank(message = " Last name can not blank")
    private String lastName;
    //Address will be checked against a regular expression
    @NotBlank(message = "Please enter an address")
    private String address;
    //Phone number is of type Integer as the primative int type cannot be used.
    @NotBlank(message = "Phone Number can not be blank")
    private String phoneNumber;
    //Username will be forced into being between 3 and 20 characters, but will also be
    @Size(min=3, max = 20, message = "Please enter a username between 3-20 characters")
    @Column(unique = true, updatable = false)
    @NotBlank(message = "userName cannot be blank")
    private String username;
    //Created date will be forced into the corresponding Json format
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createdDate;
    //updatedDate will be also forced into the corresponding Json format, this attribute will be updated
    //whenever the relevant item in the database is updated.
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updatedDate;
    //The customer id will be used as a foreign key in the booking table, and has a one to many relationship
    //As many bookings can be made by the one Customer.
    @OneToMany(mappedBy = "customer")
    private List<Booking> customerList;
    //When the Customer is created the createdDate object is created and corresponds to the current date.
    @PrePersist
    protected void onCreated(){
        this.createdDate = new Date();
    }
    //Whenever the customer object is updated the updatedDate is then also updated.
    @PreUpdate
    protected void onUpdate(){
        this.updatedDate = new Date();
    }

    //default constructor
    public Customer()
    {
    }

    public Customer(String firstName, String lastName, String email, String address, String username,
                    String phoneNumber, String password, String id)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.identificationNumber = id;
    }


    //Below are just a series of basic setters/getters.
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstname(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setCustomerList(List<Booking> customerList) {
        this.customerList = customerList;
    }

    public List<Booking> getCustomerList() {
        return customerList;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
}
