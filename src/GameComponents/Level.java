package GameComponents;

public class Level
{
    public Object[][] blocks;
    public String bestTime;

    public Level(Object[][] blocks, String bestTime){
        this.bestTime = bestTime;
        this.blocks = blocks;
    }
}