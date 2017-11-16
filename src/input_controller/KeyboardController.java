package input_controller;

import timer.PlayerDashTimer;
import city.cs.engine.*;
import game.Game;
import actor.player.Player;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The KeyboardController implements player-controllable actions using keyboard input.
 */
public class KeyboardController extends KeyAdapter {
    
    private Player player;
    private final WorldView view;
    private final Game game;
    
    public KeyboardController(Game game) {
        this.game = game;
        this.player = game.getPlayer();
        this.view = game.getGameView();
    }
    
    /**
     * Handle key press events for walking and jumping.
     * @param e description of the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE && view.getWorld().isRunning()) { // ESC = menu
            view.getWorld().stop();
            game.getInGameMenu().setVisible(true);
            view.add(game.getInGameMenu());
            game.getWindow().pack();
        } 
        else if (code == KeyEvent.VK_W || code == KeyEvent.VK_SPACE || code == KeyEvent.VK_UP) { // W or SPACE or UP arrow = jump
            // only jump if body is not already jumping
            if (!player.isJumping()) {
                player.jump(player.getJumpHeight());
                player.resetToAnimation("jump");
            }
        } 
        else if ((code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) && player.getLinearVelocity().x > -player.getMoveSpeed()) { // A or left arrow = walk left
            player.startWalking(-player.getMoveSpeed()); 
            player.removeAllImages();
            if (player.isJumping()) {
                player.addImage(player.getAnimation("jump")).flipHorizontal();
            } else {
                player.addImage(player.getAnimation("move")).flipHorizontal();
            }
        } 
        else if ((code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) && player.getLinearVelocity().x < player.getMoveSpeed()) { // D or right arrow = walk right
            player.startWalking(player.getMoveSpeed()); 
            player.removeAllImages();
            if (player.isJumping()) {
                player.addImage(player.getAnimation("jump"));
            } else {
                player.addImage(player.getAnimation("move"));
            }
        }
        else if (code == KeyEvent.VK_SHIFT && player.getCurrentMana() >= 20) { //SHIFT = directional dodge
            player.stopWalking();
            player.setCurrentMana(player.getCurrentMana() - 20);
            player.getFixtureList().get(0).destroy();
            GhostlyFixture playerDodge = new GhostlyFixture(player, player.getPlayerShape());
            player.setGravityScale(0);
            PlayerDashTimer dc = new PlayerDashTimer(player, player.getWorld());
            dc.setDodgeDuration(400);
            player.getWorld().addStepListener(dc);
            if (player.isFlipped()) {
                player.setLinearVelocity(new Vec2(-40, 0));
            } else {
                player.setLinearVelocity(new Vec2(40, 0));
            }
        }
    }
    
    /**
     * Handle key release events (stop movement when A or D are released).
     * @param e description of the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            player.stopWalking();
            if (!player.isJumping()) {
                player.removeAllImages();
                player.addImage(player.getAnimation("idle")).flipHorizontal();
            }
        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            player.stopWalking();
            if (!player.isJumping()) {
                player.removeAllImages();
                player.addImage(player.getAnimation("idle"));
            }
        }
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
}
