package Entity;

import java.util.EnumMap;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class TicketPrice {
    private double normalPrice;
    private ArrayList<LocalDate> holiday = new ArrayList<LocalDate>();
    private EnumMap<CinemaClass, Double> cinemaClassPrice = new EnumMap<>(CinemaClass.class);
    private EnumMap<AgeGroup, Double> agePrice = new EnumMap<>(AgeGroup.class);
    private EnumMap<MovieType, Double> movieTypePrice = new EnumMap<>(MovieType.class);
    private EnumMap<DateGroup, Double> dateGroupPrice = new EnumMap<>(DateGroup.class);

    public double getTicketPrice(LocalDate date, CinemaClass cinemaClass, AgeGroup age, MovieType movieType) {
        // Check null
        double totalPrice = getCinemaClassPrice(cinemaClass) + getAgePrice(age) + getMovieTypePrice(movieType)
                + getDatePrice(date);
        return totalPrice;
    }

    public ArrayList<LocalDate> getHoliday() {
        return holiday;
    }

    public boolean isHoliday(LocalDate date) {
        if (holiday.contains(date))
            return true;
        return false;
    }

    public double getNormalPrice() {
        return normalPrice;
    }

    // For all setter, need to add a condition to check whether the price for the
    // particular object is existed
    public void setNormalPrice(double price) {
        normalPrice = price;
    }

    public double getCinemaClassPrice(CinemaClass cinemaClass) {
        return cinemaClassPrice.get(cinemaClass);
    }

    public void setCinemaClassPrice(CinemaClass cinemaClass, double price) {
        cinemaClassPrice.replace(cinemaClass, price);
    }

    public double getAgePrice(AgeGroup age) {
        return agePrice.get(age);
    }

    public void setAgePrice(AgeGroup age, double price) {
        agePrice.replace(age, price);
    }

    public double getMovieTypePrice(MovieType movieType) {
        return movieTypePrice.get(movieType);
    }

    public void setMovieTypePrice(MovieType movieType, double price) {
        movieTypePrice.replace(movieType, price);
    }

    private DateGroup getDateGroup(LocalDate date) {
        if (isHoliday(date))
            return DateGroup.HOLIDAY;
        else if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)
            return DateGroup.WEEKEND;
        else
            return DateGroup.WEEKDAY;
    }

    public double getDatePrice(LocalDate date) {
        return dateGroupPrice.get(getDateGroup(date));
    }

    public double getDatePrice(DateGroup dateGroup) {
        return dateGroupPrice.get(dateGroup);
    }

    public void setDatePrice(DateGroup dateGroup, double price) {
        dateGroupPrice.replace(dateGroup, price);
    }
}
