package actor.player;

import actor.Creature;
import game_level.GameLevel;
import city.cs.engine.*;

/**
 * User-controller Player entity.
 */

public class Player extends Creature {
    private static final Shape playerBodyShape = new PolygonShape(0.82f,3.0f, 0.4f,2.96f, -0.54f,1.89f, -1.61f,-2.95f, 1.18f,-2.96f);
    //this appears as a fixture in front of the player during a melee attack
    private static final Shape playerMeleeHitboxShape = new PolygonShape(1.62f,2.98f, 1.49f,-2.94f, 3.71f,-2.94f, 4.74f,0.41f, 3.96f,2.98f);
    
    private static final BodyImage anim_idle = new BodyImage("data/animations/player/anim_idle.gif", 6.0f);
    private static final BodyImage anim_move = new BodyImage("data/animations/player/anim_run.gif", 6.0f);
    private static final BodyImage anim_jump = new BodyImage("data/animations/player/anim_jump.gif", 6.0f);
    private static final BodyImage anim_cast = new BodyImage("data/animations/player/anim_cast.gif", 6.0f);
    private static final BodyImage anim_melee = new BodyImage("data/animations/player/anim_melee.gif", 6.0f);
    
    //unlike other entities of the creature class the player can also jump and cast spells, which are tied to the amount of available mana
    private int jumpHeight;
    private int maxMana;
    private int currentMana;
    private final GameLevel world;

    public Player(GameLevel world, int jumpHeight, int maxMana) {
        super(world, 20, 100, 25);
        this.world = world;
        this.maxMana = maxMana;
        this.currentMana = maxMana;
        this.jumpHeight = jumpHeight;
        SolidFixture playerBody = new SolidFixture(this, playerBodyShape);
        playerBody.setFriction(100);
        addImage(anim_idle);
    }
    
    @Override
    public BodyImage getAnimation(String animation) {
        switch (animation) {
            case "idle":
                return anim_idle;
            case "move":
                return anim_move;
            case "jump":
                return anim_jump;
            case "cast":
                return anim_cast;
            case "melee":
                return anim_melee;
            default:
                break;
        }
        return null;
    }
    
    /**
     * 
     * @return polygon shape of the player body.
     */
    public Shape getPlayerShape() {
        return playerBodyShape;
    }
    
    /**
     * 
     * @return polygon shape of the player's melee hitbox when they are performing a melee attack.
     */
    public Shape getMeleeHitboxShape() {
        return playerMeleeHitboxShape;
    }

    /**
     * 
     * @return player's jump height.
     */
    public int getJumpHeight() {
        return jumpHeight;
    }
    
    /**
     * 
     * @return player's maximum mana.
     */
    public int getMaxMana() {
        return maxMana;
    }
    
    /**
     * 
     * @return player's current mana.
     */
    public int getCurrentMana() {
        return currentMana;
    }
    
    /** 
     * Sets player's jump height.
     * @param jumpHeight desired height.
     */
    public void setJumpHeight (int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }
    
    /**
     * Sets player's maximum mana pool.
     * @param mana desired mana amount.
     */
    public void setMaxMana(int mana) {
        maxMana = mana;
    }
    
    /**
     * Sets player's currently available mana pool.
     * @param mana desired mana amount.
     */
    public void setCurrentMana(int mana) {
        currentMana = mana;
    }
    
    /**
     * 
     * @return The GameLevel object to which the player belongs.
     */
    @Override
    public GameLevel getWorld() {
        return world;
    }
}
