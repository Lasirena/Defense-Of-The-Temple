package actor.enemy_assassin;

import actor.*;
import actor.player.Player;
import game_level.GameLevel;
import city.cs.engine.*;
import fsm.FSM;
import game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/*
* A type of enemy in the game that posesses several unique abilities.
*/

public class EnemyAssassin extends Creature implements StepListener, ActionListener {
    
    private static final Shape ENEMY_ASSASSIN_SHAPE = new PolygonShape(-0.97f,-3.94f, 1.5f,-3.94f, 0.93f,1.83f, -0.8f,1.83f);
    
    private static final BodyImage anim_idle = new BodyImage("data/animations/assassin/pa_idle.gif", 8.0f);
    private static final BodyImage anim_melee = new BodyImage("data/animations/assassin/pa_melee.gif", 8.0f);
    private static final BodyImage anim_move = new BodyImage("data/animations/assassin/pa_move.gif", 8.0f);
    private static final BodyImage anim_dash = new BodyImage("data/animations/assassin/pa_dash.gif", 8.0f);
    
    public static final float VISION_RANGE = 30;
    public static final float DASH_RANGE = 10;
    public static final float MELEE_RANGE = 6;
    
    private final Timer dashCooldown;
    private boolean hasDash;
    
    private final Game game;
    private final GameLevel world;
    private final FSM<EnemyAssassin> fsm;
    
    public EnemyAssassin(Game game, GameLevel world) {
        super(world, 12, 150, 40);
        this.game = game;
        this.world = world;
        addImage(anim_idle);
        SolidFixture enemyBody = new SolidFixture(this, ENEMY_ASSASSIN_SHAPE, 10);
        enemyBody.setFriction(100);
        fsm = new FSM<>(this, new StateStandStill());
        getWorld().addStepListener(this);
        hasDash = true;
        dashCooldown = new Timer(5000, this);
        dashCooldown.start();
    }
    
    /**
     * Checks whether the player is within a specific range from the enemy.
     * @param range the distance to be checked.
     * @return whether the player is within the specified range from the enemy.
     */
    public int playerInRange(float range) {
        Player player = game.getPlayer();
        float gap = getPosition().x - player.getPosition().x;
        // if player is to the left
        if (gap < range && gap > 0) {
            return -1;
        //if player is to the right
        } else if (gap > -range && gap < 0) {
            return 1;
        //if player out of range
        } else {
            return 0;
        }
    }
    
    /**
     * 
     * @return whether the enemy is able to use dash right now.
     */
    public boolean hasDash() {
        return hasDash;
    }
    
    /**
     * Disables enemy's ability to dash.
     */
    public void resetDash() {
        hasDash = false;
    }
    
    /**
     * 
     * @return the defined collision shape for the enemy.
     */
    public Shape getShape() {
        return ENEMY_ASSASSIN_SHAPE;
    }

    @Override
    public BodyImage getAnimation(String animation) {
        switch (animation) {
            case "idle":
                return anim_idle;
            case "move":
                return anim_move;
            case "melee":
                return anim_melee;
            case "dash":
                return anim_dash;
            default:
                break;
        }
        return null;
    }
    
    @Override
    public GameLevel getWorld() {
        return world;
    }
    
    @Override
    public void preStep(StepEvent e) {
        fsm.update();
    }
    
    @Override
    public void postStep(StepEvent e) {}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //enables enemy to dash every rotation of the timer (default every 5 seconds).
        hasDash = true;
    }
}
