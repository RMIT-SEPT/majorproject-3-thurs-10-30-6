package septmajorproject.bookingsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import septmajorproject.bookingsys.exception.CustomerException;
import septmajorproject.bookingsys.model.Customer;
import septmajorproject.bookingsys.repository.CustomerRepository;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    //saves or updates the given customer in the backend
    public Customer saveOrUpdateCustomer(Customer customer) {
        try {
            customer.setUsername(customer.getUsername());
        } catch (Exception e) {

        }

        try {
            customer.setIdentificationNumber(customer.getIdentificationNumber().toUpperCase());
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new CustomerException("Username: " + customer.getUsername() + " already exists");
        }
    }
    //Used in conjunction with put requests, takes the request body and updates the customer
    public Customer updateExistingCustomer(String id, Map<String, String> userDataMap) {

        Customer existing = customerRepository.findByIdentificationNumber(id);

        if (existing != null) {
            if (userDataMap.containsKey("password")) {
                existing.setPassword(userDataMap.get("password"));
            } else {
                existing.setFirstName(userDataMap.get("firstName"));
                existing.setLastName(userDataMap.get("lastName"));
                existing.setAddress(userDataMap.get("address"));
                existing.setPhoneNumber(userDataMap.get("phoneNumber"));
                existing.setEmail(userDataMap.get("email"));
            }

            customerRepository.save(existing);
            return existing;
        } else {
            return null;
        }
    }


    //Returns the customer by the id
    public Customer findCustomerByIdentificatioNumber(String id) {
        Customer found = customerRepository.findByIdentificationNumber(id);
        System.out.println(id);
        if (found == null) {
            throw new CustomerException("Customer: " + id + " does not exist");
        }

        return found;
    }
    //Returns the given customer by the given email address
    public Customer findCustomerByEmail(String email) {
        Customer found = customerRepository.findByEmail(email);

        if (found == null) {
            throw new CustomerException("Customer email: " + email + " does not exist");
        }

        return found;
    }
    //Finds the customer by a given username
    public Customer findCustomerByUserName(String username) {
        Customer found = customerRepository.findByUsername(username);

        if (found == null) {
            throw new CustomerException("Customer username: " + username + " does not exist");
        }

        return found;
    }
    //Finds the customer by the username and password, useful for logging in
    public Customer findByUsernameAndPassword(String username, String password) {
        Customer found = customerRepository.findByUsernameAndPassword(username, password);

        if (found == null) {
            throw new CustomerException("Customer does not exist");
        }

        return found;
    }
    //Finds all customers in the backend
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
    //used to delete a customer from the backend
    public void deleteCustomerByIdentifier(String customerId) {
        Customer found = customerRepository.findByIdentificationNumber(customerId);
        if (found == null) {
            throw new CustomerException("Customer Identifier " + customerId.toUpperCase() + "This Employee does not exist");
        }
        customerRepository.delete(found);
    }
}
