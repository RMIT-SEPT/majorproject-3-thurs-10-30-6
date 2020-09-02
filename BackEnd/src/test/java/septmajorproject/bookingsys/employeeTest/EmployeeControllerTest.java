package septmajorproject.bookingsys.employeeTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import septmajorproject.bookingsys.model.Employee;
import septmajorproject.bookingsys.service.EmployeeService;
import septmajorproject.bookingsys.service.MapValidationErrorService;
import septmajorproject.bookingsys.web.EmployeeController;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.springframework.http.MediaType;
import java.util.Arrays;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private MapValidationErrorService mapValidationErrorService;

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception{
        Employee newEmployeeOne = new Employee("1234", "Alex", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");
        
        List<Employee> allEmployees = Arrays.asList(newEmployeeOne);

        given(employeeService.findAllEmployees()).willReturn(allEmployees);

        mvc.perform(get("/api/employee/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(newEmployeeOne.getFirstName())));
    }

    @Test
    public void givenEmployeesId_whenGetEmployeesById_thenReturnEmployee() throws Exception{
        Employee newEmployeeOne = new Employee("1234", "Alex", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        given(employeeService.findByEmployeeIdentifier("1234")).willReturn(newEmployeeOne);

        mvc.perform(get("/api/employee/{employeeId}", "1234")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(newEmployeeOne.getFirstName())));
    }

    @Test
    public void givenEmployeeId_whenDeleteEmployeeById_thenReturnDelete() throws Exception{
        Employee newEmployeeOne = new Employee("1234", "Alex", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        String deleteString = "Person with ID: " + newEmployeeOne.getEmployeeIdentifier() + " was deleted";

        mvc.perform(delete("/api/employee/{employeeId}", "1234")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(deleteString)));
    }

    @Test
    public void givenEmployee_whenAddingNewEmployee_thenSuccesfullPostRequest() throws Exception{
        Employee newEmployeeOne = new Employee("1234", "Alex", "Test", "s3661671@student.rmit.edu.au", 0424735215, "Something", "s3661671", "password");

        mvc.perform(post("/api/employee/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newEmployeeOne)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(newEmployeeOne.getFirstName())));
    }

}
