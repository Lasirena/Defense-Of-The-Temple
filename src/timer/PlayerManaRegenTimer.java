package timer;

import city.cs.engine.*;
import actor.player.Player;

/**
* A StepListener that regenerates mana of the player, as long as it's not at maximum.
*/

public class PlayerManaRegenTimer implements StepListener {
    
    private final Player player;
    private long regenTimer;
    private long regenRate;
    
    public PlayerManaRegenTimer(Player player) {
        this.player = player;
    }
    
    /**
     * Set mana regeneration interval.
     * @param ratems desired interval in milliseconds.
     */
    public void setRegenRate(long ratems) {
        regenTimer = System.currentTimeMillis() + ratems;
        regenRate = ratems;
    }
    
    @Override
    public void preStep(StepEvent e) {
        if (player.getCurrentMana() < player.getMaxMana() && System.currentTimeMillis() > regenTimer) {
            player.setCurrentMana(player.getCurrentMana() + 1);
            this.setRegenRate(regenRate);
        }
    }
    
    @Override
    public void postStep(StepEvent e) {
        
    }
}