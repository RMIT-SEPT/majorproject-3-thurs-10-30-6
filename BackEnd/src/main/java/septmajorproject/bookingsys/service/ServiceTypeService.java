package septmajorproject.bookingsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import septmajorproject.bookingsys.model.ServiceType;
import septmajorproject.bookingsys.repository.ServiceTypeRepository;

@Service
public class ServiceTypeService {
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    public ServiceType saveOrUpdateService(ServiceType serviceType){
        return serviceTypeRepository.save(serviceType);
    }


}
