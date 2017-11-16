package ui_action;

import game.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Brings up the tutorial menu.
 * @author Lanaya
 */

public class TutorialOpen implements ActionListener {
    
    private final Game game;
    
    public TutorialOpen(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        game.getMainMenu().setVisible(false);
        game.getMenuView().remove(game.getMainMenu());
        game.getMenuView().add(game.getTutorialMenu());
        game.getTutorialMenu().setVisible(true);
        game.getWindow().pack();
    }
}
