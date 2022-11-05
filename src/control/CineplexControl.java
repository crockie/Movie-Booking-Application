package control;

import entity.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import boundary.*;

public class CineplexControl implements MainControl{
    @Override
    public void begin() {
        while (true) {
			int option = MenuView.getMenuOption(
					"Enter your choice: ",
					"1. Add Cineplex",
                    "2. Add Cinemas",
                    "3. View Cineplex",
                    "4. Add Movie Time",
                    "5. Exit");
            Scanner sc = new Scanner(System.in);
            List<Movie> movieList = DatabaseManager.getDataBase().getMovieList();
            List<Cineplex> cineplexList = DatabaseManager.getDataBase().getCineplexList();
			switch (option) {
				case 1:
					System.out.println("Add Cineplex");
                    System.out.println("Enter Cineplex Name: ");
                    String name = sc.nextLine();
                    Cineplex cineplex = new Cineplex(name);
                    DatabaseManager.getDataBase().getCineplexList().add(cineplex);
					break;

				case 2:
                    System.out.println("Select Cineplex:");
                    for (int i = 0; i < cineplexList.size(); i++) {
                        System.out.println((i+1) + ". " + cineplexList.get(i).getName());
                    }
                    int cineplexIndex = sc.nextInt();
                    sc.nextLine();
                    Cineplex cineplex2 = cineplexList.get(cineplexIndex-1);
					System.out.println("Add Cinema");
                    System.out.println("Enter Cinema Code: ");
                    String cinemaCode = sc.nextLine();
                    System.out.println("Enter Cinema Type: ");
                    CinemaClass cinemaClass = MenuView.getItemName("Cinema Code: ", CinemaClass.values());
                    boolean[][] seat = new boolean[10][10];
                    
                    for(int i = 0; i < 10; i++){
                        for(int j = 0; j < 10; j++){
                            seat[i][j] = false;
                        }
                    }
                    cineplex2.addCinema(cinemaCode, cinemaClass, seat);
					break;

				case 3:
                    System.out.println("View Cineplex");
                    for (int i = 0; i < cineplexList.size(); i++) {
                        System.out.println((i+1) + ". " + cineplexList.get(i).getName());
                    }
                    int cineplexIndex2 = sc.nextInt();
                    sc.nextLine();
                    Cineplex cineplex3 = cineplexList.get(cineplexIndex2-1);
                    ArrayList <Cinema> cinemaList = cineplex3.getCinemas();
                    for(int i = 0; i < cinemaList.size(); i++){
                        System.out.println((i+1) + ". " + cinemaList.get(i).getCinemaCode());
                        boolean[][] seatLayouts = cinemaList.get(i).getSeatLayout();
                        for(int j = 0; j < 10; j++){
                            for(int k = 0; k < 10; k++){
                                if(seatLayouts[j][k] == true){
                                    System.out.print("X ");
                                }
                                else{
                                    System.out.print("O ");
                                }
                            }
                            System.out.println();
                        }
                    }
					break;

				case 4:
					System.out.println("Add Movie Time");
                    
                    for (int i = 0; i < cineplexList.size(); i++) {
                        System.out.println((i+1) + ". " + cineplexList.get(i).getName());
                    }
                    int cineplexIndex3 = sc.nextInt();
                    sc.nextLine();
                    ArrayList<Cinema> cinemaList2 = cineplexList.get(cineplexIndex3-1).getCinemas();
                    int cinemaIndex = 0;
                    System.out.println("Select Cinema:");
                    for(int i = 0; i < cinemaList2.size(); i++){
                        System.out.println((i+1) + ". " + cinemaList2.get(i).getCinemaCode());
                    }
                    cinemaIndex = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.println("Enter Movie Title: ");
                    Movie movie = MenuView.getItemName("Choose a movie", movieList);
					MovieView.getMovieView(movie);

                    System.out.println("Enter Movie Date and Time as dd/MM/yyyy HH:mm: ");
                    //LocalDateTime movieDateTime = LocalDateTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

                    System.out.println("Enter movie start date and time (dd/MM/yyyy HH:mm): ");
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                    LocalDateTime movieTime;
                    while (true) {
                        try {
                            movieTime = LocalDateTime.parse(sc.nextLine(), format);
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date and time format. Please try again.");
                        }
                    }
                    Cinema cinema = cinemaList2.get(cinemaIndex-1);
                    
                    cinema.addMovieTime(movieTime, movie);
                    break;

				case 5:
					NavigateControl.popOne();
					return;
			}
		}
    }
    public static void main(String[] args) {
        DatabaseManager.read();
        
        CineplexControl controller = new CineplexControl();
        controller.begin();

        DatabaseManager.write();
    }
}
