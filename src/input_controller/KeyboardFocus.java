package input_controller;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A mouse listener that gives the keyboard focus to a specified component.
 */
public class KeyboardFocus extends MouseAdapter {
    private final Component target;
    
    /**
     * Initialize the listener.
     * @param target the component to be given the focus on mouseEntered().
     */
    public KeyboardFocus(Component target) {
        this.target = target;
    }
    
    /**
     * Called when the mouse enters a component.
     * @param e description of the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        target.requestFocus();
    }
}
