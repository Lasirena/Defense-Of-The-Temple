package ui_action;

import game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action that happens when the player clicks the Resume button in the in-game menu.
 * @author Lanaya
 */

public class ResumeGame implements ActionListener {
    private final Game game;
    
    public ResumeGame(Game game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        game.getInGameMenu().setVisible(false);
        game.getGameView().remove(game.getInGameMenu());
        game.getActiveWorld().start();
    }
}
