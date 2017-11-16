package ui_action;

import game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.jbox2d.common.Vec2;

/**
 * Action that happens when player clicks the load game button as well as anything in the loading interface.
 * @author Lanaya
 */

public class LoadGame implements ActionListener {

    private final Game game;
    
    public LoadGame(Game game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //first bring up the loading menu
        game.getInGameMenu().setVisible(false);
        game.getGameView().remove(game.getInGameMenu());
        int result = game.getLoadMenu().getSaveGameChooser().showOpenDialog(game.getLoadMenu());
        game.getWindow().pack();
        game.getLoadMenu().setVisible(true);
        
        //then once the player has done something within the window and pressed one of the buttons, handle their actions as follows:
        switch (result) {
            //if the player pressed the Load Game button
            case JFileChooser.APPROVE_OPTION:
                //create the file object and file readers to parse data from it
                File saveFile;
                FileReader fileReader = null;
                BufferedReader saveReader = null;
                //wrap everything in try-catch block
                try {
                    //get the save file chosen by the player
                    saveFile = game.getLoadMenu().getSaveGameChooser().getSelectedFile();
                    fileReader = new FileReader(saveFile);
                    saveReader = new BufferedReader(fileReader);
                    
                    //read the first (and only) line in the save file
                    String line = saveReader.readLine();

                    //handle the line as space-separated values
                    String[] tokens = line.split(" ");
                    //treat the first value as the level index
                    game.setLevelIndex(Integer.parseInt(tokens[0]));
                    //start the game on that level
                    game.getWindow().remove(game.getMenuView());
                    game.getWindow().add(game.getGameView());
                    game.getWindow().pack();
                    game.startGame();
                    //treat the second value as the player's health at the moment of the save
                    game.getPlayer().setCurrentHealth(Integer.parseInt(tokens[1]));
                    //3rd - mana at the moment of the save
                    game.getPlayer().setCurrentMana(Integer.parseInt(tokens[2]));
                    //4th - damage at the moment of the save
                    game.getPlayer().setDamage(Integer.parseInt(tokens[3]));
                    //5th and 6th form the coordinate position at which the player was during the save
                    game.getActiveWorld().getPlayer().setPosition(new Vec2(Float.parseFloat(tokens[4]), Float.parseFloat(tokens[5])));
                    
                    //close the readers
                    fileReader.close();
                    saveReader.close();
                } catch (IOException|NumberFormatException|NullPointerException|IndexOutOfBoundsException e) {
                    System.out.println("File with that name does not exist or is corrupted.");
                    System.out.println(e);
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException ex) {
                            Logger.getLogger(LoadGame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (saveReader != null) {
                        try {
                            saveReader.close();
                        } catch (IOException ex) {
                            Logger.getLogger(LoadGame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                break;
            //if the player clicks Cancel, they are brought back to the in-game menu
            case JFileChooser.CANCEL_OPTION:
                game.getGameView().add(game.getInGameMenu());
                game.getInGameMenu().setVisible(true);
                game.getWindow().pack();
                break;
            //if an error happens, the player is notified and brought back to the in-game menu
            case JFileChooser.ERROR_OPTION:
                System.out.println("Error loading save.");
                game.getGameView().add(game.getInGameMenu());
                game.getInGameMenu().setVisible(true);
                game.getWindow().pack();
                break;
            default:
                break;
        }
    }
    
}