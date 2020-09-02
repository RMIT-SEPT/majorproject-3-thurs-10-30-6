package septmajorproject.bookingsys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import septmajorproject.bookingsys.model.Employee;
import septmajorproject.bookingsys.service.EmployeeService;
import septmajorproject.bookingsys.service.MapValidationErrorService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MapValidationErrorService mapValidation;

    @PostMapping("")
    public ResponseEntity<?> createNewEmployee(@Valid @RequestBody Employee employee, BindingResult result){
        //cleans up the error message that is displayed
        //benefits when developing the front end
        ResponseEntity<?> errorMap = mapValidation.MapValidationService(result);

        if(errorMap != null){
            return errorMap;
        }

        Employee employeeOne = employeeService.saveOrUpdateEmployee(employee);

        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getPersonById(@PathVariable String employeeId){
        Employee employee = employeeService.findByEmployeeIdentifier(employeeId);

        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees(){
        return employeeService.findAllEmployees();
    }

    @GetMapping("/{username}/{password}")
    public ResponseEntity<?> getbyUsernameAndPassword(@PathVariable String username, @PathVariable String password){
        Employee employee = employeeService.findByUsernameAndPassword(username, password);

        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployeeByIdentifier(@PathVariable String employeeId){
        employeeService.deleteEmployeeByIdentifier(employeeId);

        return new ResponseEntity<String>("Person with ID: " + employeeId + " was deleted", HttpStatus.OK);
    }
}
