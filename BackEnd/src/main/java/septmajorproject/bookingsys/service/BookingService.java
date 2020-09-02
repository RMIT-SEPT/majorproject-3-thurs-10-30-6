package septmajorproject.bookingsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import septmajorproject.bookingsys.repository.BookingRepository;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    //Add in addition/modification/retrieval logic
}
