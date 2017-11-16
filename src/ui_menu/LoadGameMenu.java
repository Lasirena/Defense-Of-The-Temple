/*
 * (c) Lanaya
 */
package ui_menu;

import game.*;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Lanaya
 */
public class LoadGameMenu extends javax.swing.JPanel {

    private final Game game;
    
    public LoadGameMenu(Game game) {
        this.game = game;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        saveGameChooser = new javax.swing.JFileChooser();

        saveGameChooser.setAcceptAllFileFilterUsed(false);
        saveGameChooser.setApproveButtonText("Load Game");
        saveGameChooser.setCurrentDirectory(new File("savedgames/"));
        saveGameChooser.addChoosableFileFilter(new FileNameExtensionFilter("DotT Savegame File (.dott)", "dott"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saveGameChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(saveGameChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser saveGameChooser;
    // End of variables declaration//GEN-END:variables
    
    public JFileChooser getSaveGameChooser() {
        return saveGameChooser;
    }

}