package ui_action;

import game.Game;
import ui_menu.SaveGameMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Handles what happens when the player clicks accept from the save game menu.
 * @author Lanaya
 */

public class SaveGameAccept implements ActionListener {
    private final Game game;
    private final SaveGameMenu menu;
    private String fileName;
    
    public SaveGameAccept (Game game, SaveGameMenu menu) {
        this.game = game;
        this.menu = menu;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //first retrieve the text the player has entered as the save file's desired name
        fileName = menu.getTextFromField();
        //check whether the path to the save games exists and if not, create it
        if (Files.notExists(game.getSaveGamePath())) {
            new File(game.getSaveGamePath().toString()).mkdirs();
        }
        //try with resources to create a new file or override an existing one with space-separated values
        try (FileWriter saveWriter = new FileWriter(game.getSaveGamePath().toString()+"/"+fileName+".dott", false)) {
            saveWriter.write(game.getLevelIndex()+" "
                            +game.getPlayer().getCurrentHealth()+" "
                            +game.getPlayer().getCurrentMana()+" "
                            +game.getPlayer().getDamage()+" "
                            +game.getPlayer().getPosition().x+" "
                            +game.getPlayer().getPosition().y);
            saveWriter.close();
        } catch (IOException e) {
            System.out.println("Saved games folder does not exist.");
        }
        game.getSaveMenu().setVisible(false);
        game.getGameView().remove(game.getSaveMenu());
        game.getInGameMenu().setVisible(true);
        game.getGameView().add(game.getInGameMenu());
        game.getWindow().pack();
    }
    
}
