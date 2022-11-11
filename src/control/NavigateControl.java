package control;

import java.util.Stack;

/**
 * This class controls navigation between classes in the control package
 */
public class NavigateControl {
    /**
     * A stack containing all the control class objects.
     * Stack: First In Last Out (FILO)
     * The first control class objects are the last to leave the stack.
     * The top of the stack contains the currently active control class object.
     */
    private static Stack<MainControl> stack = new Stack<MainControl>();

    /**
     * This method pushes the control class to the stack and call its begin method
     * @param mainController the maincontrol class to be loaded
     */
    public static void load(MainControl mainController) {
        stack.push(mainController);
        mainController.begin();
    }

    /**
     * This method goes back by one level.
     */
    public static void popOne() {
        pop(1);
    }

    /**
     * This method goes back by the given number of levels
     * @param noOfLevels the number of levels to go back
     */
    public static void pop(int noOfLevels) {
        MainControl mainControl = null;

        // Pops off the current controller
        noOfLevels++;

        if (noOfLevels <= stack.size()) {
            for (int k = 0; k < noOfLevels; k++)
                mainControl = stack.pop();

            load(mainControl);
        }
    }
}
