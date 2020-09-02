package septmajorproject.bookingsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import septmajorproject.bookingsys.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Override
    //returns a list of all customers
    List<Customer> findAll();

    //finds customer by unique username
    Customer findByUsername(String username);

    //finds customer by unique email
    Customer findByEmail(String email);

    //finds customer by the identification number
    Customer findByIdentificationNumber(String id);

    //Further table functionality will be entered as needed
    Customer findByFirstName(String firstName);

    Customer findByUsernameAndPassword(String username, String password);

}
