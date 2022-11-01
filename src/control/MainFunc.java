package control;

import entity.DatabaseManager;

/**
 * The application's main class.
 */
public class MainFunc {
    /**
     * Application is launched through this main function
     * @param args unused
     */
    public static void main(String[] args) {
        DatabaseManager.read();

        NavigateControl.load(new UserControl());

        DatabaseManager.write();
    }
}