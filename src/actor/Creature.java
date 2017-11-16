package actor;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Abstract Creature class for all living entities/actors (enemies, player, etc.) in the game.
 */
public abstract class Creature extends Walker {
    
    private int moveSpeed;
    private int maxHealth;
    private int currentHealth;
    private int damage;
    
    public Creature(World world, int moveSpeed, int maxHealth, int damage) {
        super(world);
        this.moveSpeed = moveSpeed;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.damage = damage;
    }
    
    /** Returns the BodyImage representing one of the creature's animations depending on string keyword input. 
    * This method should be implemented within sub-classes individually depending on which animations they have.
    * @param animation the conventional name of the animation the image for which will be returned.
    * @return  image of the requested animation.*/
    public abstract BodyImage getAnimation(String animation);
    
    /** @return the animation the creature currently has attached.
    */
    public BodyImage getCurrentAnimation() {
        try {
            return this.getImages().get(0).getBodyImage();
        } catch (NullPointerException|IndexOutOfBoundsException e) {
            System.out.println("Warning: No images attached to body " + this);
            System.out.println(e);
            return null;
        }
    }
    
    /** @return  whether the creature is in the air.*/
    public boolean isJumping() {
        Vec2 v = this.getLinearVelocity();
        return Math.abs(v.y) > 0.01f;
    }
    
    /**
     * @return whether creature is facing negative X axis, i.e. if its active BodyImage is flipped.
     * (Will only work correctly if creature has one and only one image attached).
     */
    public boolean isFlipped() {
        try {
            return this.getImages().get(0).isFlippedHorizontal();
        } catch (NullPointerException|IndexOutOfBoundsException e) {
            System.out.println("Warning: No images attached to body " + this);
            System.out.println(e);
            return false;
        }
    }
    
    /**
     * 
     * @return creature's movement speed.
     */
    public int getMoveSpeed() {
        return moveSpeed;
    }
    
    /**
     * 
     * @return creature's maximum health.
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    
    /**
     * 
     * @return creature's current health.
     */
    public int getCurrentHealth() {
        return currentHealth;
    }
    
    /**
     * 
     * @return creature's damage.
     */
    public int getDamage() {
        return damage;
    }
    
    
    /**
     * Assigns new animation to the creature based on the direction it's currently facing; 
     * Removes all previous attached animations/images.
     * @param anim animation to be assigned.
     */
    // 
    public void resetToAnimation(String anim) {
        if (this.isFlipped()) {
            this.removeAllImages();
            this.addImage(this.getAnimation(anim)).flipHorizontal();
        } else {
            this.removeAllImages();
            this.addImage(this.getAnimation(anim));
        }
    }
    
    /**
     * Sets creature's movement speed.
     * @param ms new movement speed.
     */
    public void setMoveSpeed(int ms) {
        moveSpeed = ms;
    }
    
    /**
     * Sets creature's maximum health.
     * @param mh new maximum health.
     */
    public void setMaxHealth(int mh) {
        maxHealth = mh;
    }
    
    /**
     * Sets creature's current health.
     * @param ch new current health.
     */
    public void setCurrentHealth(int ch) {
        currentHealth = ch;
    }
    
    /**
     * Sets creature's damage.
     * @param dmg new damage value.
     */
    public void setDamage(int dmg) {
        damage = dmg;
    }
}
