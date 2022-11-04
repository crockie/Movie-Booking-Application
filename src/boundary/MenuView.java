package boundary;

import java.util.List;
import entity.ItemName;

/**
 * This class handles the menu display and input
 */
public class MenuView {
	
	/**
	 * This method display the numbered list of options and prompts the user choose an option. The numbering starts from 1.
	 * @param title the title of the menu
	 * @param options the options
	 * @return the selected option
	 */
	public static int getMenuOption(String title, String... options) {
		IOController.displayMessage("");
		IOController.displayTitle(title);
		
		for (int i = 0; i < options.length; i++) {
			IOController.displayMessage((i + 1) + ": " + options[i]);
		}
		
		int option;
		
		while (true) {
			option = IOController.readInt("Option: ");
			
			if (option >= 1 && option <= options.length)
				break;
			else
				IOController.displayMessage("Invalid option selected!");
		}
		
		IOController.displayMessage("");
		
		return option;
	}
	
	/**
	 * This method display the numbered list of options and prompts the user choose an option. The numbering starts from 1.
	 * @param <T> a class that implements the {@code ItemName} interface
	 * @param title the title of the menu
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
	
	/**
	 * This method display the numbered list of options and prompts the user choose an option. The numbering starts from 1.
	 * @param <T> a class that implements the {@code ItemName} interface
	 * @param title the title of the menu
	 * @param itemNames the options of type {@code T}
	 * @return the selected {@code T} object
	 */
	public static <T extends ItemName> T getItemName(String title, List<T> itemNames) {
		int size = itemNames.size();
		String[] options = new String[size];
		for (int i = 0; i < size; i ++) {
			options[i] = itemNames.get(i+1).nameToString();
		}
	
		/*
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * int i = 0;
		for (T itemName: itemNames) {
			options[i] = itemName.nameToString();
			i++;
		}*/
		int option = getMenuOption(title, options);
		return itemNames.get(option - 1);
	}
}