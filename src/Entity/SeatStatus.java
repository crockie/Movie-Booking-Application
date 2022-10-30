package Entity;

/**
* Seat statuses for a show time seat
*/
public enum SeatStatus {
    /**
    * Seat is taken
    */
    TAKEN,
   
    /**
    * Seat is empty
    */
    EMPTY,
    
    /**
    * There is no such physical seat
    */
    NO_SEAT;  
}
