package septmajorproject.bookingsys.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity

public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long id;

    @Size(min=1, max= Integer.MAX_VALUE , message="Enter a Valid service number that is greater than one character.")
    @NotBlank(message="Please Enter a service number.")
    private String serviceNo;

    @Size(min=1, max= Integer.MAX_VALUE , message="Enter a Valid service name that is greater than one character.")
    @NotBlank(message="Please Enter a service name.")
    private String serviceName;


    //created and modified date for records following format yyyy-mm-dd
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date createdDate;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date updatedDate;


    //default constructor
    public ServiceType()
    {
    }

    //simple constructor for ServiceType
    public ServiceType( String serviceNo, String serviceName) {
        this.serviceNo = serviceNo;
        this.serviceName = serviceName;

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
    public String getServiceNo() {
        return serviceNo;
    }
    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}

