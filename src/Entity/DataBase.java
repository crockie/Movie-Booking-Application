import java.util.ArrayList;
import java.util.HashMap;

public class DataBase{
	/**
	 * The cinema staff list with the username of the cinema staff as the key
	 */
	private HashMap<String, CinemaStaff> cinemaStaffList = new HashMap<String, CinemaStaff>();
	/**
	 * The movie goer list with the username of the movie goer as the key
	 */
	private HashMap<String, User> userList = new HashMap<String, User>();
	
	// Might change name
	private PricingScheme pricingScheme = new PricingScheme();
	
	private ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
	
	private ArrayList<Movie> movieList = new ArrayList<Movie>();
	
	// Check if Staff username exists
	public boolean checkStaffUsername(String username) {
		return cinemaStaffList.containsKey(username);
	}
	
	// Check if Staff password is correct
	public CinemaStaff getCinemaStaff(String username, String password) {
		CinemaStaff cinemaStaff = cinemaStaffList.get(username);
		
		if (cinemaStaff != null && cinemaStaff.login(password))
			return cinemaStaff;
		else
			return null;
	}
	
	// Add new CinemaStaff to DataBase
	public boolean addCinemaStaff(CinemaStaff cinemaStaff) {
		String username = cinemaStaff.getUsername();
		
		if (cinemaStaffList.containsKey(username)) {
			return false;
			
		} else {
			cinemaStaffList.put(username, cinemaStaff);
			return true;
		}
	}
	
	// Check if User username exists
	public boolean checkUserUsername(String username) {
		return userList.containsKey(username);
	}

	
	// Returns the User object if the password is correct
	public User getUser(String username, String password) {
		User user = userList.get(username);
		
		if (user != null && user.login(password))
			return user;
		else
			return null;
	}

	// Add new User to DataBase
	public boolean addUser(User user) {
		String username = user.getUsername();
		
		if (userList.containsKey(username)) {
			return false;
			
		} else {
			userList.put(username, user);
			return true;
		}
	}
	
	// Might change name
	public PricingScheme getPricingScheme() {
		return pricingScheme;
	}
	
	// Might change name
	public ArrayList<Cineplex> getCineplexList() {
		return cineplexList;
	}
	
	// Might change name
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}
}