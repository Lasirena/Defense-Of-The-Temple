package game_level;

import timer.PlayerManaRegenTimer;
import collision_listener.PickupCollider;
import environment.*;
import collectible.*;
import game.Game;
import org.jbox2d.common.Vec2;

/**
 * Level 2 of the game.
 * @author Lanaya
 */

public class Level2 extends GameLevel{
    private Portal portal;
    
    public Level2() {
        
    }
    
    @Override
    public void populate(Game game) {
        // spawn foreground static bodies

        // spawn the player, set mana regen
        super.populate(game);
        PlayerManaRegenTimer playerRegen = new PlayerManaRegenTimer(getPlayer());
        this.addStepListener(playerRegen);
        playerRegen.setRegenRate(500);
        
        //spawn collectibles
        Scroll scroll = new Scroll(this);
        scroll.setPosition(new Vec2(90, 15));
        scroll.addCollisionListener(new PickupCollider(game));
        
        portal = new Portal(this);
        portal.makeGhostly();
        portal.setPosition(new Vec2(0, 5));
        
        //spawn background static bodies
        Statue statue = new Statue(this);
        statue.setPosition(new Vec2(20, -1));
        
        Shroom shroom = new Shroom(this);
        shroom.setPosition(new Vec2(90, -10));
    }
    
    //implementing parent's abstract methods
    
    @Override
    public Vec2 getStartPosition() {
        return new Vec2(0, 0);
    };
    
    @Override
    public boolean isCompleted(){
        return getPlayer().getDamage() > 30;
    };
    
    @Override
    public String getAssetsFolder() {
        return "level2";
    }
    
    @Override
    public String getObjectives() {
        return "Find the scroll with ancient psi-blade schematics and escape through the portal.";
    }
    
    @Override
    public Portal getPortal() {
        return portal;
    }
}
