package ui_action;

import game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles what happens when the player clicks Cancel from the save game menu.
 * @author Lanaya
 */

public class SaveGameCancel implements ActionListener {

    private final Game game;
    
    public SaveGameCancel (Game game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        game.getSaveMenu().setVisible(false);
        game.getGameView().remove(game.getSaveMenu());
        game.getInGameMenu().setVisible(true);
        game.getGameView().add(game.getInGameMenu());
        game.getWindow().pack();
    }
    
}
