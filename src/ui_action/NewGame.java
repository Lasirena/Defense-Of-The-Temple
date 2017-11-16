package ui_action;

import game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action that happens when player clicks the New Game button.
 * @author Lanaya
 */

public class NewGame implements ActionListener {
    private final Game game;
    
    public NewGame(Game game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        game.getWindow().remove(game.getMenuView());
        game.getWindow().add(game.getGameView());
        game.getWindow().pack();
        
        game.startGame();
    }
}
