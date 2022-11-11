package entity;

import java.util.EnumMap;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Ticket price determined by other factors
 */
public class TicketPrice implements Serializable {
    /**
     * The serialisation version number
     */
    private static final long serialVersionUID = 6521558511554354L;

    /**
     * Normal Price (Price without any factor)
     */
    private double normalPrice;

    /**
     * A list of holiday date
     */
    private ArrayList<LocalDate> holiday = new ArrayList<LocalDate>();

    /**
     * EnumMap that maps Cinema Class with additional price
     */
    private EnumMap<CinemaClass, Double> cinemaClassPrice = new EnumMap<>(CinemaClass.class);

    /**
     * EnumMap that maps Age of Movie Goer with additional price
     */
    private EnumMap<AgeGroup, Double> agePrice = new EnumMap<>(AgeGroup.class);

    /**
     * EnumMap that maps Movie Type with additional price
     */
    private EnumMap<MovieType, Double> movieTypePrice = new EnumMap<>(MovieType.class);

    /**
     * EnumMap that maps Date Group with additional price
     */
    private EnumMap<DateGroup, Double> dateGroupPrice = new EnumMap<>(DateGroup.class);

    /**
     * The additional price of couple seats
     */
    private double coupleSeatPrice;

    /**
     * Get the final ticket price with all factors considered
     * 
     * @param date        Date of the movie
     * @param cinemaClass Class of the cinema
     * @param age         Age of the movie goer
     * @param movieType   Type of the movie
     * @return the total ticket price
     */
    public double getTicketPrice(LocalDate date, CinemaClass cinemaClass, AgeGroup age, MovieType movieType) {
        double totalPrice = getNormalPrice() + getCinemaClassPrice(cinemaClass) + getAgePrice(age)
                + getMovieTypePrice(movieType)
                + getDatePrice(date);
        return totalPrice;
    }

    /**
     * Get the list of holiday dates
     * 
     * @return holiday dates
     */
    public ArrayList<LocalDate> getHoliday() {
        return holiday;
    }

    /**
     * Set the list of holiday dates
     * 
     * @param holiday holiday dates
     */

    public void setHoliday(ArrayList<LocalDate> holiday) {
        this.holiday = holiday;
    }

    /**
     * Check whether the given date is a holiday
     * 
     * @param date
     * @return
     */
    public boolean isHoliday(LocalDate date) {
        if (holiday.contains(date))
            return true;
        return false;
    }

    /**
     * Get the normal price (price without any factor)
     * 
     * @return normal price
     */
    public double getNormalPrice() {
        return normalPrice;
    }

    // For all setter, need to add a condition to check whether the price for the
    // particular object is existed
    /**
     * Update/Set the normal price of the ticket
     * 
     * @param price The price to be updated
     */
    public void setNormalPrice(double price) {
        normalPrice = price;
    }

    /**
     * Get the additional price according to the class of cinema
     * 
     * @param cinemaClass The class of cinema
     * @return the additional price
     */
    public double getCinemaClassPrice(CinemaClass cinemaClass) {
        return cinemaClassPrice.get(cinemaClass);
    }

    /**
     * Update the additional price according to the class of cinema
     * 
     * @param cinemaClass The class of cinema
     * @param price       The price to be updated
     */
    public void setCinemaClassPrice(CinemaClass cinemaClass, double price) {
        cinemaClassPrice.put(cinemaClass, price);
    }

    /**
     * Get the additional price according to the age of movie goer
     * 
     * @param age The age of the movie goer
     * @return the additional price
     */
    public double getAgePrice(AgeGroup age) {
        return agePrice.get(age);
    }

    /**
     * Update the additional price according to the age of movie goer
     * 
     * @param age   The age of the movie goer
     * @param price The additional price to be updated
     */
    public void setAgePrice(AgeGroup age, double price) {
        agePrice.put(age, price);
    }

    /**
     * Get the additional price according to the type of movie
     * 
     * @param movieType The type of movie
     * @return the additional price
     */
    public double getMovieTypePrice(MovieType movieType) {
        return movieTypePrice.get(movieType);
    }

    /**
     * Update the addtional price according to the type of movie
     * 
     * @param movieType The type of movie
     * @param price     The additional price to be updated
     */
    public void setMovieTypePrice(MovieType movieType, double price) {
        movieTypePrice.put(movieType, price);
    }

    /**
     * Get the date group of which the given date belongs to
     * 
     * @param date The date of the movie
     * @return the date group
     */
    private DateGroup getDateGroup(LocalDate date) {
        if (isHoliday(date))
            return DateGroup.HOLIDAY;
        else if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)
            return DateGroup.WEEKEND;
        else
            return DateGroup.WEEKDAY;
    }

    /**
     * Get the additional price according to the date of the movie
     * 
     * @param date The date of the movie
     * @return the additional price
     */
    public double getDatePrice(LocalDate date) {
        return dateGroupPrice.get(getDateGroup(date));
    }

    /**
     * Get the additional price according to the date group
     * 
     * @param dateGroup The date group of the date of movie
     * @return the additional price
     */
    public double getDatePrice(DateGroup dateGroup) {
        return dateGroupPrice.get(dateGroup);
    }

    /**
     * Update the additional price according to the date group
     * 
     * @param dateGroup The date group of the date of the movie
     * @param price     The additional price
     */
    public void setDatePrice(DateGroup dateGroup, double price) {
        dateGroupPrice.put(dateGroup, price);
    }

    /**
     * Get the additional price for couple seats
     * 
     * @return
     */
    public double getCoupleSeatPrice() {
        return coupleSeatPrice;
    }

    /**
     * Update the additional price for couple seats
     * 
     * @param price The updated price of couple seats
     */
    public void setCoupleSeatPrice(double price) {
        coupleSeatPrice = price;
    }
}
