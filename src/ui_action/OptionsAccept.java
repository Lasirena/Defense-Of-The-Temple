package ui_action;

import game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 * Action that happens when player clicks Accept in the Options menu.
 * @author Lanaya
 */

public class OptionsAccept implements ActionListener {
    private final Game game;
    private final JComboBox<String> selector;
    private int selectorIndex;
    
    public OptionsAccept(Game game, JComboBox selector) {
        this.game = game;
        this.selector = selector;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        selectorIndex = selector.getSelectedIndex();
        switch (selectorIndex) {
            case 0:
                game.setGameViewResolution(1366, 768);
                break;
            case 1:
                game.setGameViewResolution(1440, 900);
                break;
            case 2:
                game.setGameViewResolution(1920, 1080);
                break;
            case 3:
                game.setGameViewResolution(2560, 1440);
                break;
            default:
                break;
        }
        game.getOptionsMenu().setVisible(false);
        game.getMenuView().remove(game.getOptionsMenu());
        game.getMenuView().add(game.getMainMenu());
        game.getWindow().pack();
        game.getMainMenu().setVisible(true);
    }
}
