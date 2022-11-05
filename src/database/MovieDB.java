import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import entity.Movie;
import entity.MovieRating;
import entity.MovieType;
import entity.ShowStatus;

public class MovieDB extends Database{
    
    @Override
    public ArrayList read(String fileName) throws IOException {
        List<String[]> data = readAllData(fileName);
        ArrayList<Movie> movies = new ArrayList<Movie>();


        /*movie.getTitle(), movie.getSynopsis(),  movie.getDirector(), 
        movie.getCast().toString(), movie.getShowStatus().toString(), 
        movie.getMovieRating().toString(), movie.getMovieType().toString(), 
        movie.getDuration().toString()};*/
        for(String[] row: data){
            String title = row[0];
            String synopsis = row[1];
            String director = row[2];
            String cast = row[3];
            String showStatus = row[4];
            String movieRating = row[5];
            String movieType = row[6];
            String duration = row[7];

            Movie movie = new Movie(
                title, synopsis, director, cast.split(" "), 
                ShowStatus.valueOf(showStatus), MovieRating.valueOf(movieRating), 
                MovieType.valueOf(movieType), Duration.ofMinutes(Integer.parseInt(duration)));
            
            movies.add(movie);
        }

        return movies;

    }

    @Override
    public void save(String fileName, List data) throws IOException {
        List<String[]> toWrite = new ArrayList<String[]> ();
        
        for (int i = 0; i < data.size(); i++) {
            Movie movie = (Movie) data.get(i);
            String[] movieData = {
                movie.getTitle(), movie.getSynopsis(),  movie.getDirector(), 
                movie.getCast().toString(), movie.getShowStatus().toString(), 
                movie.getMovieRating().toString(), movie.getMovieType().toString(), 
                movie.getDuration().toString()};

            toWrite.add(movieData);
        }

        super.writeAllData(fileName, toWrite);
    }

    public static void main(String[] args){
        try{
            MovieDB movieDB = new MovieDB();
            List movies = new ArrayList<Movie>();
            movies = movieDB.read("movies.csv");
            
            for(int i = 0; i < movies.size(); i++){
                for(int j = 0; j < 8; j++){
                    System.out.println(movies.get(i));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
