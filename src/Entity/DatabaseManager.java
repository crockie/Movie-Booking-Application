package entity;

/**
 * Manages data stored in database
 */
public class DatabaseManager {

    /**
     * To store data
     */
    private static DataBase database;

    /**
     * Relative path of the data file
     */
    private static String path = "";

    /**
     * Constructor of the class
     */
    public DatabaseManager() {
    }

    /**
     * Initialize a new DataBase object
     */
    public static void init() {
        database = new DataBase();
    }

    /**
     * Read the file
     * If the file is not existed or the file is not a DataBase object, call init()
     * Else, downcast the file to DataBase type
     */
    public static void read() {
        DataBase database = SerializeDB.readSerializedObject(path);

        if (database == null)
            init();
    }

    /**
     * Write to the file
     */
    public static void write() {
        SerializeDB.writeSerializedObject(path, database);
    }

    /**
     * Return the database
     * 
     * @return the database
     */
    public static DataBase getDataBase() {
        return database;
    }

}