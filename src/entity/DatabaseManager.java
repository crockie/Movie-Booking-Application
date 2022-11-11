package entity;

/**
 * This class manages the data stored in database
 */
public class DatabaseManager {

    /**
     * To store data
     */
    private static DataBase database;

    /**
     * Relative path of the data file
     */
    private static String path = "src/database/data/moblima.dat";

    /**
     * The constructor of the class
     */
    public DatabaseManager() {
    }

    /**
     * This method initialises a new DataBase object
     */
    public static void init() {
        database = new DataBase();
    }

    /**
     * This methood reads the file
     * If the file is not existed or the file is not a DataBase object, call init()
     * Else, downcast the file to DataBase type
     */
    public static void read() {
        Object object = SerializeDB.readSerializedObject(path);

        if (object == null || !(object instanceof DataBase))
            init();
        else
            database = (DataBase) object;

    }

    /**
     * This method writes to the file
     */
    public static void write() {
        SerializeDB.writeSerializedObject(path, database);
    }

    /**
     * This method gets the Database
     *
     * @return the Database
     */
    public static DataBase getDataBase() {
        return database;
    }

}
