package septmajorproject.bookingsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import septmajorproject.bookingsys.model.Roster;
import septmajorproject.bookingsys.model.RosterPK;

import java.util.Date;

@Repository
public interface RosterRepository extends JpaRepository<Roster, RosterPK> {
    //Further table functionality will be entered as needed
}
