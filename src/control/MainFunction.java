package control;

import entity.DatabaseManager;

/**
 * This class is the application's main class.
 */
public class MainFunction {
    /**
     * This method launches the application through this main function
     * 
     * @param args unused
     */
    public static void main(String[] args) {
        DatabaseManager.read();

        NavigateControl.load(new UserControl());

        DatabaseManager.write();
    }
}
