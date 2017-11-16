package ui_action;

import game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Removes the Tutorial Menu from view and brings back the main menu.
 * @author Lanaya
 */

public class TutorialClose implements ActionListener {
    private final Game game;
    
    public TutorialClose(Game game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        game.getTutorialMenu().setVisible(false);
        game.getMenuView().remove(game.getTutorialMenu());
        game.getMenuView().add(game.getMainMenu());
        game.getMainMenu().setVisible(true);
        game.getWindow().pack();
    }
}
