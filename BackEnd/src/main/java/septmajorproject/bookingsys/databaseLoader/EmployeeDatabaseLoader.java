package septmajorproject.bookingsys.databaseLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import septmajorproject.bookingsys.model.Employee;
import septmajorproject.bookingsys.repository.CustomerRepository;
import septmajorproject.bookingsys.repository.EmployeeRepository;

@Component
public class EmployeeDatabaseLoader implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDatabaseLoader(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Employee alex = new Employee(
                "E1234",
                "Alex",
                "Test",
                "s3661671@student.rmit.edu.au",
                0424735215,
                "Something",
                "Alex",
                "alex");
        alex.setAdmin(false);

        Employee admin = new Employee(
                "E2341",
                "Admin",
                "Admin",
                "admin@student.rmit.edu.au",
                000,
                "admin",
                "Admin",
                "admin");
        admin.setAdmin(true);

        Employee sam = new Employee(
                "E3456",
                "Sam",
                "ten",
                "sam@student.rmit.edu.au",
                0544654634,
                "10 the parade",
                "Sam",
                "sam");
        sam.setAdmin(false);

        Employee lucas = new Employee(
                "E9876",
                "Lucas",
                "Angelo",
                "lucas@student.rmit.edu.au",
                463413476,
                "10 rover street",
                "Lucas",
                "lucas");
        lucas.setAdmin(false);

        Employee massimo = new Employee(
                "E7688",
                "Massimo",
                "Sette",
                "massimo@student.rmit.edu.au",
                658454556,
                "10 link parade",
                "Massimo",
                "massimo");
        massimo.setAdmin(false);

        employeeRepository.save(alex);
        employeeRepository.save(admin);
        employeeRepository.save(sam);
        employeeRepository.save(lucas);
        employeeRepository.save(massimo);
    }
}
