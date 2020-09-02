package septmajorproject.bookingsys.databaseLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import septmajorproject.bookingsys.model.Customer;
import septmajorproject.bookingsys.repository.CustomerRepository;

@Component
public class CustomerDataBaseLoader implements CommandLineRunner {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDataBaseLoader(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    //loads customers into the database
    @Override
    public void run(String... strings) throws Exception {
        this.customerRepository.save(new Customer(
                "Theo",
                "Politis",
                "s3661671@student.rmit.edu.au",
                "Something",
                "Theo",
                "4545574654",
                "theo",
                "1TP"));

        this.customerRepository.save(new Customer(
                "Angus",
                "Newman",
                "something@gmail.com",
                "9 alloy way",
                "Angus",
                "555444555",
                "angus",
                "1AE"));

        this.customerRepository.save(new Customer(
                "Sam",
                "Timothy",
                "Timothy@gmail.com",
                "5 point avenue",
                "Sam",
                "8744544545",
                "sam",
                "1ST"));

        this.customerRepository.save(new Customer(
                "Margaret",
                "Wiley",
                "Margeret@gmail.com",
                "3 wiley street",
                "Margaret",
                "78354653",
                "margaret",
                "1MW"));

        this.customerRepository.save(new Customer(
                "Rebecca",
                "Luscombe",
                "rebecca@gmail.com",
                "10 philips road",
                "Rebecca",
                "8744544545",
                "rebecca",
                "1RL"));

        this.customerRepository.save(new Customer(
                "Tom",
                "Yamaha",
                "tom@student.rmit.edu.au",
                "15 Maccas road",
                "Tom",
                "87455544545",
                "tom",
                "1TY"));
    }


}
