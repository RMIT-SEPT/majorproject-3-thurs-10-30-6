package septmajorproject.bookingsys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import septmajorproject.bookingsys.model.ServiceType;
import septmajorproject.bookingsys.service.ServiceTypeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/serviceType")
public class ServiceTypeController {
    @Autowired
    private ServiceTypeService serviceTypeService;

    @PostMapping("")
    public ResponseEntity<?> createNewServiceType(@Valid @RequestBody ServiceType serviceType, BindingResult result){
        //cleans up the error message that is displayed
        //benefits when developing the front end
        if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error : result.getFieldErrors()){
                return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
            }
        }

        ServiceType serviceType1 = serviceTypeService.saveOrUpdateService(serviceType);

        return new ResponseEntity<ServiceType>(serviceType, HttpStatus.CREATED);
    }
}
