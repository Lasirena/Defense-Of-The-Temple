
package input_controller;

import timer.PlayerAnimationTimer;
import city.cs.engine.*;
import timer.PlayerHitboxTimer;
import actor.player.Player;
import projectile.PlayerSpellProjectile;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.jbox2d.common.Vec2;

/**
 * This MouseController implements the player-controllable actions that use mouse input.
 */
public class MouseController extends MouseAdapter {

    private final WorldView view;
    private Player player;

    /**
     * Construct a listener.
     * @param view the view the mouse will be clicked on
     * @param body the player that will be controlled by the input
     */
    public MouseController(WorldView view, Player body) {
        this.view = view;
        this.player = body;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton();
        if (button == MouseEvent.BUTTON1) {
            player.resetToAnimation("melee");
            SolidFixture meleeHitbox = new SolidFixture(player, player.getMeleeHitboxShape());
            
            player.getWorld().addStepListener(new PlayerHitboxTimer(view, player));
            player.getWorld().addStepListener(new PlayerAnimationTimer(player, player.getCurrentAnimation(), 27));
        } else if (button == MouseEvent.BUTTON3 && player.getCurrentMana() >= 10) {
            player.setCurrentMana(player.getCurrentMana() - 10);
            
            player.resetToAnimation("cast");
            
            player.getWorld().addStepListener(new PlayerAnimationTimer(player, player.getCurrentAnimation(), 21));
            
            // create the spell projectile
            DynamicBody spell = new PlayerSpellProjectile(view.getWorld());
            spell.setPosition(new Vec2(player.getPosition().x+2.0f, player.getPosition().y));
            if (player.isFlipped()) {
                spell.setLinearVelocity(new Vec2(-30,0));
                spell.setPosition(new Vec2(player.getPosition().x-2.0f, player.getPosition().y));
            } else {
                spell.setPosition(new Vec2(player.getPosition().x+2.0f, player.getPosition().y));
                spell.setLinearVelocity(new Vec2(30,0));
            }
        }
    }
    
    public void setBody(Player player) {
        this.player = player;
    }
}
