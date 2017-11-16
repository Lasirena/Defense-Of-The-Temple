package view;

import city.cs.engine.*;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * This is a class representing the main menu view that the player is presented with upon opening the application.
 * It draws the menu background and exists to make sure that our game and menu GUI are separate.
 * @author Lanaya
 */
public class MainMenuView extends UserView {
    private final Image background;
    
    public MainMenuView(World world, int width, int height) {
        super(world, width, height);
        this.background = new ImageIcon("data/ui/menubg.png").getImage();
    }
    
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }
}
