package septmajorproject.bookingsys.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.transform.Source;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
    //values that make up a person object and the sql table
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="employee_id")
    //provides a unique id for each employee that is generated
    private long employeeId;
    @NotBlank(message = "Employee Identifier required")
    @Column(updatable = false, unique = true)
    private String employeeIdentifier;
    private boolean isAdmin;
    @Size(min=3, max= Integer.MAX_VALUE , message="Enter a Valid first name that is greater than two character.")
    @NotBlank(message="Please Enter a First Name.")
    private String firstName;
    @Size(min=3, max=Integer.MAX_VALUE, message="Enter a Valid last name that is greater than 1 character")
    @NotBlank(message="Please Enter a Last Name.")
    private String lastName;
    @NotBlank(message="Please Enter an Address.")
    private String address;
    @NotBlank(message="Please Enter an Address.")
    private String email;
    private int phoneNumber;
    @NotBlank(message="Password cannot be blank")
    private String password;
    @NotBlank(message = "UserName cannot be blank")
    private String userName;

    //created and modified date for records following format yyyy-mm-dd
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date createdDate;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date updatedDate;

    //foreign keys to other databses
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Roster> rosterList;

    @OneToMany(mappedBy = "employee")
    private List<Roster> bookingList;

    //default constructor
    public Employee(){

    }

    //simple constructor for employees
    public Employee(String employeeIdentifier, String firstName, String lastName, String email, int phoneNumber, String address, String userName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber =  phoneNumber;
        this.address = address;
        this.userName = userName;
        this.password = password;
        this.employeeIdentifier = employeeIdentifier;
    }

    //updates the date variables automatically with annotations
    @PrePersist
    protected void onCreate(){
        this.createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedDate = new Date();
    }

    //getters and setters


    public String getEmployeeIdentifier() {
        return employeeIdentifier;
    }

    public void setEmployeeIdentifier(String employeeIdentifier) {
        this.employeeIdentifier = employeeIdentifier;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRosterList(List<Roster> rosterArray) {
        this.rosterList = rosterArray;
    }

    public void setBookingList(List<Roster> bookingArray) {
        this.bookingList = bookingArray;
    }

    public List<Roster> getRosterList() {
        return rosterList;
    }

    public List<Roster> getBookingList() {
        return bookingList;
    }
}
