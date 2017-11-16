package ui_action;

import game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action that happens when player clicks Cancel in the Options menu.
 * @author Lanaya
 */

public class OptionsCancel implements ActionListener {
    private final Game game;
    
    public OptionsCancel(Game game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        game.getOptionsMenu().setVisible(false);
        game.getMenuView().remove(game.getOptionsMenu());
        game.getMenuView().add(game.getMainMenu());
        game.getMainMenu().setVisible(true);
        game.getWindow().pack();
    }
}
