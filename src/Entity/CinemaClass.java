package Entity;
public enum CinemaClass {
    STANDARD("Standard"),
    PLAT_MOVIE_SUITES("Platinum Movie Suite");

    private String cinemaClass;

    private CinemaClass(String cinemaClass){
        this.cinemaClass = cinemaClass;
    }

    private String getCinemaClass(){
        return this.cinemaClass;
    }
    
}
