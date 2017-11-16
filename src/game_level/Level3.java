package game_level;

import timer.PlayerManaRegenTimer;
import collision_listener.*;
import environment.Ship;
import collectible.Portal;
import actor.enemy_assassin.EnemyAssassin;
import game.Game;
import org.jbox2d.common.Vec2;

/**
 * Level 3 of the game.
 * @author Lanaya
 */

public class Level3 extends GameLevel {
    
    private Portal portal;
    
    public Level3() {
        
    }
    
    @Override
    public void populate(Game game) {
        // spawn foreground static bodies
        Ship ship = new Ship(this);
        ship.setPosition(new Vec2(0, 0));
        ship.addCollisionListener(new LevelCollider(ship));

        // spawn the player, set mana regen
        super.populate(game);
        PlayerManaRegenTimer playerRegen = new PlayerManaRegenTimer(getPlayer());
        this.addStepListener(playerRegen);
        playerRegen.setRegenRate(500);
        
        //spawn enemies
        EnemyAssassin tank = new EnemyAssassin(game, this);
        tank.setPosition(new Vec2(10, -26));
        tank.addCollisionListener(new EnemyAssassinCollider(getPlayer(), tank));
        
        //spawn background static bodies
        ship.addSails();
        
        portal = new Portal(this);
        portal.makeGhostly();
        portal.setPosition(new Vec2(-35, -21));
        portal.addCollisionListener(new PickupCollider(game));
    }
    
    //implementing parent's abstract methods
    
    @Override
    public Vec2 getStartPosition() {
        return new Vec2(-35, -26);
    };
    
    @Override
    public boolean isCompleted(){
        return true;
    };
    
    @Override
    public String getAssetsFolder() {
        return "level3";
    }
    
    @Override
    public String getObjectives() {
        return "Kill Phantom Assassin.";
    }
    
    @Override
    public Portal getPortal() {
        return portal;
    }
}
