package septmajorproject.bookingsys.model;

import javax.persistence.*;

@Entity
@Table(name="ROSTER")
/**
 * Roster entity in the table
 */
public class Roster {
    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    // many to one connection with the employee table
    private Employee employee;

    @EmbeddedId
    private RosterPK rosterPK;

    /**
     * Default constructor
     */
    public Roster(){

    }

    /**
     * constructor
     * @param employee : employee
     * @param rosterPK : roster composite key
     */
    public Roster(Employee employee, RosterPK rosterPK) {
        this.employee = employee;
        this.rosterPK = rosterPK;
    }

    /**
     * set employee
     * @param employee : employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * get employee
     * @return employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * set roster composite key
     * @param rosterPK : roster composite key
     */
    public void setRosterPK(RosterPK rosterPK) {
        this.rosterPK = rosterPK;
    }

    /**
     * get roster composite key
     * @return roster primary key
     */
    public RosterPK getRosterPK() {
        return rosterPK;
    }
}
