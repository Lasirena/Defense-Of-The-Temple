package view;

import city.cs.engine.*;
import game.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.ImageIcon;

/**
 * This is the class representing the Game View.
 * It handles drawing the level backgrounds and all in-game GUI.
 */
public class GameView extends UserView {
    private final Game game;
    private Image background;
    private final Image power;
    private Shape fullStat;
    private Shape health;
    private Shape mana;
    
    public GameView(World world, Game game, int width, int height) {
        super(world, width, height);
        this.game = game;
        this.power = new ImageIcon("data/ui/psiblade_icon.png").getImage();
    }
    
    /**
     * The loadBackground method has to be called every time there is a transition to a new level, including when the game is started for the first time.
     * It loads the background image from the levels' assets folder which depends on the selected level.
     */
    public void loadBackground() {
        this.background = new ImageIcon("data/"+game.getActiveWorld().getAssetsFolder()+"/bg.jpg").getImage();
    }
    
    @Override
    protected void paintBackground(Graphics2D g) {
        //the standard drawImage method, which also implements resolution scaling by getting the width and height of the backgrounds container.
        //there may be some image ratio distortion, since not all provided resolutions are of the same aspect ratio.
        //however, it avoids the problem of having multiple file copies of the same background, and makes resolution switching less unnecessarily complex code-wise.
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
    
    @Override
    protected void paintForeground(Graphics2D g) {
        //first draw the background fill for the health shape
        g.setColor(Color.black);
        fullStat = new Rectangle(10, 25, 200, 12);
        g.fill(fullStat);
        g.draw(fullStat);
        //label the health shape
        g.setColor(Color.white);
        g.drawString("Health: ", 10, 20);
        //fill the shape depending on player's current health
        g.setColor(Color.red);
        for (int i = 0; i < game.getPlayer().getCurrentHealth()*2; i+=2) {
            health = new Rectangle(10+i, 25, 2, 12);
            g.draw(health);
            g.fill(health);
        }
        //label the shape with the exact health numbers
        g.setColor(Color.white);
        g.drawString(game.getPlayer().getCurrentHealth()+"/100", 75, 35);
        
        //background fill for the mana shape
        g.setColor(Color.black);
        fullStat = new Rectangle(10, 55, 200, 12);
        g.fill(fullStat);
        g.draw(fullStat);
        //text label for the mana shape
        g.setColor(Color.white);
        g.drawString("Mana: ", 10, 50);
        //draw the mana
        g.setColor(Color.blue);
        for (int i = 0; i < game.getPlayer().getCurrentMana()*2; i+=2) {
            mana = new Rectangle(10+i, 55, 2, 12);
            g.draw(mana);
            g.fill(mana);
        }
        //number label the mana
        g.setColor(Color.white);
        g.drawString(game.getPlayer().getCurrentMana()+"/100", 75, 65);
        
        //draw an icon indicating player's damage
        g.drawImage(power, 10, 75, this);
        //label the damage and its value
        g.drawString("Power: " + game.getPlayer().getDamage(), 50, 95);
        
        //describe the player's objectives within the current level
        g.drawString("Objectives:", 10, 130);
        g.drawString(game.getActiveWorld().getObjectives(), 10, 150);
    }
}
