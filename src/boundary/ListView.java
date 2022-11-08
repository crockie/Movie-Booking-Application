package boundary;

import java.util.ArrayList;
import java.util.List;

import entity.ItemName;

/**
 * Displays a list of object
 */
public class ListView {
    /**
     * Display a list of strings
     * 
     * @param name The name of the list
     * @param stringList The list of strings
     * @param nullString The string to show if the stringList is empty
     */
    public static void showStringList(String name, List<String> stringList, String nullString) {
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
     * @param <T> The class that implements {@code ItemName} interface
     * @param name The name of the list
     * @param itemList The list of items
     * @param nullString The string to show if the stringList is empty
     */
    public static <T extends ItemName> void showItemList(String name, ArrayList<T> itemList,
            String nullString) {
        List<String> stringList = new ArrayList<String>();

        for (ItemName itemName : itemList) {
            stringList.add(itemName.nameToString());
        }

        showStringList(name, stringList, nullString);
    }

}
