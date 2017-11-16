package ui_action;

import game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action that happens when player clicks the Options button.
 */

public class OptionsOpen implements ActionListener {
    private final Game game;
    
    public OptionsOpen(Game game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        game.getMainMenu().setVisible(false);
        game.getMenuView().remove(game.getMainMenu());
        game.getMenuView().add(game.getOptionsMenu());
        game.getWindow().pack();
        game.getOptionsMenu().setVisible(true);
    }
}
