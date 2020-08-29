package septmajorproject.bookingsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import septmajorproject.bookingsys.model.Booking;
import septmajorproject.bookingsys.model.BookingPK;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, BookingPK> {
    //Further table functionality will be entered as needed

    @Override
    List<Booking> findAllById(Iterable<BookingPK> id);

    List<Booking> findByEmployee(String employee);

    List<Booking> findByCustomer(String customer);
}
