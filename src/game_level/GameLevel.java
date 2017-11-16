package game_level;

import city.cs.engine.*;
import collectible.Portal;
import game.Game;
import actor.player.Player;
import org.jbox2d.common.Vec2;

/**
 * Abstract class for all in-game levels.
*/

public abstract class GameLevel extends World {
    private Player player;
    
    public GameLevel() {
        //changed target framerate to 60
        super(60);
    }
    
    public Player getPlayer() {
        return player;
    }

    public void populate(Game game) {
        player = new Player(this, 10, 100);
        player.setPosition(getStartPosition());
    };
    
    /**
     * 
     * @return level's assets folder path as a string
     */
    public abstract String getAssetsFolder();
    
    /**
     * 
     * @return starting position of the player
     */
    public abstract Vec2 getStartPosition();
    
    /**
     * 
     * @return whether the level has been completed
     */
    public abstract boolean isCompleted();
    
    /**
     * 
     * @return level objectives as a string.
     */
    public abstract String getObjectives();
    
    /**
     * 
     * @return portal object serving as the level's exit and/or entrance
     */
    public abstract Portal getPortal();
}
