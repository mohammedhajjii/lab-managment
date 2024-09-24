package md.hajji.equipmentservice.enums;

public enum ReservationStatus {
    /**
     * if the equipment is restricted, then the user need the authorization from prof
     */
    WAITLISTED,
    /**
     * if the equipment is not restricted, accepted and pending, waiting to check out
     */
    ACCEPTED,
    /**
     * if the reservation is refused by prof
     */
    REFUSED,
    /**
     * equipment is in use, and user take possession of the equipment (reservation started)
     */
    CHECKED_OUT,
    /**
     *  the reservation ended, and user return the equipment
     */
    COMPLETED,
    /**
     * if the user or request for canceling a reservation
     */
    CANCEL_REQUESTED,
    /**
     * if the admin or prof cancel a reservation due to damage or operational issues
     */
    CANCELLED,
    /**
     * if the allocated time is out
     */
    EXPIRED
}
