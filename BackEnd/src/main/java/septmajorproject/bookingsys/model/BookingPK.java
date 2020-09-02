package septmajorproject.bookingsys.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Embeddable
/**
 * booking table composite key table
 */
public class BookingPK implements Serializable {

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date rosterDate;

    @JsonFormat(pattern = "HH:mm")
    private Time rosterTime;

    /**
     * default constructor
     */
    public BookingPK() {
    }

    /**
     * constructor
     * @param rosterDate : roster date
     * @param rosterTime : roster time
     */
    public BookingPK(Date rosterDate, Time rosterTime) {
        this.rosterDate = rosterDate;
        this.rosterTime = rosterTime;
    }

    /**
     * setter for date
     * @param rosterDate date
     */
    public void setRosterDate(Date rosterDate) {
        this.rosterDate = rosterDate;
    }

    /**
     * setter for time
     * @param rosterTime : time
     */
    public void setRosterTime(Time rosterTime) {
        this.rosterTime = rosterTime;
    }

    /**
     * get date
     * @return date
     */
    public Date getRosterDate() {
        return rosterDate;
    }

    /**
     * get time
     * @return Time
     */
    public Time getRosterTime() {
        return rosterTime;
    }
}
