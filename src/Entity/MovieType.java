package Entity;

/**
* The movie types
*/
public enum MovieType implements ItemName {
	
    /**
    * 3D movie
    */
    _3D("3D"),
	
    /**
    * Blockbuster movie
    */
    BLOCKBUSTER("Blockbuster"),
	
    /**
    * Regular movie
    */
    REGULAR("Regular");
    
    /**
    * The name of the movie type
    */
    private final String name;
    
    /**
    * Creates a {@code MovieType} object with the given name
    * @param name
    */
    private MovieType(String name) {
        this.name = name;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public String nameToString() {
        return name;
    }
}
