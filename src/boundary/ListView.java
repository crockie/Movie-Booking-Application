package boundary;

import java.util.ArrayList;

import entity.ItemName;

/**
 * Displays a list of object
 */
public class ListView {
    /**
     * Display a list of strings
     * 
     * @param title      The name of the list
     * @param stringList The list of strings
     * @param nullString The string to show if the stringList is empty
     */
    public static void showList(String name, ArrayList<String> stringList, String nullString) {
        System.out.println(name);

        if (stringList.size() == 0)
            System.out.println(nullString);
        else {
            for (String string : stringList) {
                System.out.println(string);
            }
        }
    }

    /**
     * Displays a list of {@code ItemName} objects
     * 
     * @param <T>          The class that implements {@code ItemName} interface
     * @param title        The name of the list
     * @param itemNameList The list of items
     * @param nullString   The string to show if the stringList is empty
     */
    public static <T extends ItemName> void showItemNameList(String name, ArrayList<T> itemNameList,
            String nullString) {
        ArrayList<String> stringList = new ArrayList<String>();

        for (ItemName itemName : itemNameList) {
            stringList.add(itemName.nameToString());
        }

        showList(name, stringList, nullString);
    }

}
