package ui_action;

import game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Brings up the Save Game menu.
 * @author Lanaya
 */

public class SaveGameOpen implements ActionListener {

    private final Game game;
    
    public SaveGameOpen(Game game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        game.getInGameMenu().setVisible(false);
        game.getGameView().remove(game.getInGameMenu());
        game.getGameView().add(game.getSaveMenu());
        game.getWindow().pack();
        game.getSaveMenu().setVisible(true);
    }
    
}
