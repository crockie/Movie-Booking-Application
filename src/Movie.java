import java.util.ArrayList;

public class Movie {
    //should private be change to protected to allow access for same package?
    private String title, synopsis, director;
    private ArrayList<String> casts = new ArrayList<>();
    protected enum ShowingStatus { COMING_SOON, PREVIEW, NOW_SHOWING};
    private ShowingStatus status;
    private double overallRating;
    // to be implemented
    //private ArrayList<Review> reviews = new ArrayList<>();


    //constructors
    public Movie(String title){
        this.title = title;
    }

    public Movie(String title, String synopsis, String director, ArrayList<String> casts, ShowingStatus status, double overallRating){
        this.title = title;
        this.synopsis = synopsis;
        this.director = director;
        this.casts = casts;
        this.status = status;
        this.overallRating = overallRating;
    }

    // get and set methods
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getSynopsis(){
        return this.synopsis;
    }
    public void setSynopsis(String synopsis){
        this.synopsis = synopsis;
    }

    public String getDirector(){
        return this.director;
    }
    public void setDirector(String director){
        this.director = director;
    }

    public ArrayList<String> getCasts(){
        return this.casts;
    }
    public void setCasts(ArrayList<String> casts){
        this.casts = casts;
    }

    public ShowingStatus getStatus(){
        return this.status;
    }
    public void setStatus(ShowingStatus status){
        this.status = status;
    }

    public double getOverallRating(){
        return this.overallRating;
    }
    public void setOverallRating(double overallRating){
        this.overallRating = overallRating;
    }
}
