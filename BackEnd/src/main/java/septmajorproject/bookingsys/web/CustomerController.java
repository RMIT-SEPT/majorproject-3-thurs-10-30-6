package septmajorproject.bookingsys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import septmajorproject.bookingsys.model.Customer;
import septmajorproject.bookingsys.model.Employee;
import septmajorproject.bookingsys.service.CustomerService;
import septmajorproject.bookingsys.service.MapValidationErrorService;

import javax.validation.Valid;
@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private MapValidationErrorService mapValidation;

    @PostMapping("")
    public ResponseEntity<?> createNewCustomer(@Valid @RequestBody Customer customer, BindingResult result){
        ResponseEntity<?> errorMap = mapValidation.MapValidationService(result);

        if(errorMap != null){
            return errorMap;
        }

        Customer newEmployee = customerService.saveOrUpdateCustomer(customer);

        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    @GetMapping("{username}/{password}")
    public ResponseEntity<?> getPersonByUserNameAndPassword(@PathVariable String username, @PathVariable String password){
        Customer customer = customerService.findByUsernameAndPassword(username, password);

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

}

