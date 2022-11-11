package boundary;

import java.util.List;
import java.util.Scanner;

import entity.ItemName;

/**
 * This class handles the menu display and input
 */
@SuppressWarnings("resource")
public class MenuView {

	/**
	 * This method displays the numbered list of options and prompts the user choose
	 * an option. The numbering starts from 1.
	 * 
	 * @param title   the title of the menu
	 * @param options the options
	 * @return the selected option
	 */
	public static int getMenuOption(String title, String... options) {
		System.out.println("");

		System.out.println(title);

		String line = "";

		for (int i = 0; i < title.length(); i++)
			line += "-";

		System.out.println(line);

		for (int i = 0; i < options.length; i++) {
			System.out.println((i + 1) + ": " + options[i]);
		}

		int option;
		Scanner sc = new Scanner(System.in);

		while (true) {
			try {
				System.out.print("Enter option: ");
				option = sc.nextInt();
				if (option > 0 && option <= options.length)
					break;
				else
					System.out.println("Please enter a valid option");
			} catch (Exception e) {
				System.out.println("Please enter a valid option");
				sc.nextLine();
			}
		}

		System.out.println("");
		return option;
	}

	/**
	 * This method shows the list of options and gets the user input.
	 * 
	 * @param <T>       a class that implements the {@code ItemName} interface
	 * @param title     menu title
	 * @param itemNames the options of type {@code T}
	 * @return the selected {@code T} object
	 */
	public static <T extends ItemName> T getItemName(String title, List<T> itemNames) {
		int size = itemNames.size();
		String[] options = new String[size];
		for (int i = 0; i < size; i++) {
			options[i] = itemNames.get(i).nameToString();
		}

		int option = getMenuOption(title, options);
		return itemNames.get(option - 1);
	}

	/**
	 * This method shows the list of options and gets the user input.
	 * 
	 * @param <T>       a class that implements the {@code ItemName} interface
	 * @param title     menu title
	 * @param itemNames the options of type {@code T}
	 * @return the selected {@code T} object
	 */
	public static <T extends ItemName> T getItemName(String title, T[] itemNames) {
		String[] options = new String[itemNames.length];
		for (int i = 0; i < itemNames.length; i++)
			options[i] = itemNames[i].nameToString();

		int option = getMenuOption(title, options);

		return itemNames[option - 1];
	}

}
