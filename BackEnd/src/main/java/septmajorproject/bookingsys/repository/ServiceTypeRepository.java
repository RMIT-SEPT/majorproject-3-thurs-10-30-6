package septmajorproject.bookingsys.repository;

import org.springframework.data.repository.CrudRepository;
import septmajorproject.bookingsys.model.ServiceType;

public interface ServiceTypeRepository extends CrudRepository<ServiceType, Long> {
    @Override
    Iterable<ServiceType> findAllById(Iterable<Long> iterable);

    ServiceType findByServiceName(String serviceName);
}
