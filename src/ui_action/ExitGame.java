package ui_action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action that happens when player clicks the exit button.
 * @author Lanaya
 */

public class ExitGame implements ActionListener {
    
    public ExitGame() {
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
