package septmajorproject.bookingsys.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import septmajorproject.bookingsys.repository.RosterRepository;

@Service
public class RosterService {

    @Autowired
    private RosterRepository rosterRepository;

    //Add in addition/modification/retrieval logic

}
