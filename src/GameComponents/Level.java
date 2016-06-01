package GameComponents;

/**
 * The class that represents a game level.
 */
public class Level
{
    //Variables
    public Object[][] blocks;
    public String bestTime;

    //Constructors
    public Level(Object[][] blocks, String bestTime){
        this.bestTime = bestTime;
        this.blocks = blocks;
    }
}