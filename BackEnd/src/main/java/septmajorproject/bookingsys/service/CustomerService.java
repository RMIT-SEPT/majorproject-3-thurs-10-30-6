package septmajorproject.bookingsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import septmajorproject.bookingsys.exception.CustomerException;
import septmajorproject.bookingsys.model.Customer;
import septmajorproject.bookingsys.model.Employee;
import septmajorproject.bookingsys.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveOrUpdateCustomer(Customer customer) {
        try{
            customer.setUsername(customer.getUsername());
        }catch(Exception e){

        }

        try {
            customer.setIdentificationNumber(customer.getIdentificationNumber().toUpperCase());
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new CustomerException("Username: " + customer.getUsername() + " already exists");
        }
    }

    public Customer findCustomerByIdentificatioNumber(String id){
        Customer found = customerRepository.findByIdentificationNumber(id);

        if(found == null){
            throw new CustomerException("Customer: " + id + " does not exist");
        }

        return found;
    }

    public Customer findCustomerByEmail(String email){
        Customer found = customerRepository.findByEmail(email);

        if(found == null){
            throw new CustomerException("Customer email: " + email + " does not exist");
        }

        return found;
    }

    public Customer findCustomerByUserName(String username){
        Customer found = customerRepository.findByUsername(username);

        if(found == null){
            throw new CustomerException("Customer username: " + username + " does not exist");
        }

        return found;
    }

    public Customer findByUsernameAndPassword(String username, String password){
        Customer found = customerRepository.findByUsernameAndPassword(username, password);

        if(found == null){
            throw new CustomerException("Customer does not exist");
        }

        return found;
    }
}
