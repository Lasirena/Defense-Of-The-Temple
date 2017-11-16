package game;

import input_controller.*;
import actor.player.*;
import game_level.*;
import view.*;
import ui_menu.*;
import city.cs.engine.DebugViewer;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The computer game.
 */
public class Game {

    /** The World in which the bodies move and interact. */
    private GameLevel world;
    
    private Path saveGamePath;
    
    private final GameView gameView;
    private final MainMenuView menuView;
    
    private final JFrame frame;
    private final Container mainMenu;
    private final Container optionsMenu;
    private final Container inGameMenu;
    private final Container saveMenu;
    private final LoadGameMenu loadMenu;
    private final TutorialMenu tutorialMenu;
    
    private MouseController mouseController;
    private KeyboardController keyboardController;

    private int levelIndex;
    private final ArrayList<GameLevel> levelList;

    /** Initialize a new Game. */
    public Game() {

        //attempt set the look and feel of the game's UI.
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                System.out.println("UIManager Exception: " + e);
        }
        
        //set a path for where saved games should be stored (relative to project folder by default).
        saveGamePath = Paths.get("savedgames/");
        
        //make an array list containing all the levels and start at the first level
        levelIndex = 0;
        levelList = new ArrayList<>();
        levelList.add(new Level1());
        levelList.add(new Level2());
        levelList.add(new Level3());
        world = levelList.get(levelIndex);
        
        //make two views, one for the main menu and one for the game, each with a specific resolution
        gameView = new GameView(world, this, 1336, 768);
        menuView = new MainMenuView(world, 600, 500);
        
        // display the view in a frame
        frame = new JFrame("Defense of the Temple");
        
        // fullscreen mode
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
        frame.setVisible(true);

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //add the menu view to the frame upon creation.
        frame.add(menuView);
        
        //define UI menus
        mainMenu = new MainMenu(this);
        optionsMenu = new OptionsMenu(this);
        inGameMenu = new InGameMenu(this);
        saveMenu = new SaveGameMenu(this);
        loadMenu = new LoadGameMenu(this);
        tutorialMenu = new TutorialMenu(this);
        menuView.add(mainMenu);
        
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // get keyboard focus
        frame.requestFocus();
        // give keyboard focus to the frame whenever the mouse enters the view
        gameView.addMouseListener(new KeyboardFocus(frame));
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
    
    /**
     * Creates a new game and its required objects when the player clicks on "New Game".
     */
    
    public void startGame() {
        //start the game at the first world in the levels list and set it active
        setActiveWorld(levelList.get(levelIndex));
        //paint the background for that level
        gameView.loadBackground();
        
        // add keyboard and mouse controllers and listeners
        world.addStepListener(new PlayerTracker(gameView, getPlayer()));
        mouseController = new MouseController(gameView, getPlayer());
        keyboardController = new KeyboardController(this);
        frame.addKeyListener(keyboardController);
        gameView.addMouseListener(mouseController);
        
        //start the physics simulation in the world
        world.start();

        // uncomment this to also make a debugging view
        //JFrame debugView = new DebugViewer(world, 1440, 900);

        // print description of game controls
        System.out.println("Game controls: \n"
                + "A/Left arrow - go left \n"
                + "D/Right arrow - go right \n"
                + "W/Up arrow/SPACE - jump \n"
                + "Left mouse - melee attack \n"
                + "Right mouse - ranged attack");
    }
    
    /**
     * 
     * @return Current level index.
     */
    public int getLevelIndex() {
        return levelIndex;
    }
    
    /**
     * 
     * @param levelIndex level index to be set to.
     */
    public void setLevelIndex(int levelIndex) {
        this.levelIndex = levelIndex;
    }
    
    /**
     * 
     * @return Path for the game's save files.
     */
    public Path getSaveGamePath() {
        return saveGamePath;
    }
    
    /**
     * Sets path for the game's save files.
     * @param newPath new path to be set.
     */
    public void setSaveGamePath(Path newPath) {
        saveGamePath = newPath;
    }
    
    /**
     * 
     * @return The game's player.
     */
    public Player getPlayer() {
        return world.getPlayer();
    }
    
    /**
     * 
     * @return The game's primary frame.
     */
    public JFrame getWindow() {
        return frame;
    }
    
    /**
     * 
     * @return The game's in-game view.
     */
    public GameView getGameView() {
        return gameView;
    }
    
    /**
     * 
     * @return The game's start menu view.
     */
    public MainMenuView getMenuView() {
        return menuView;
    }
    
    /**
     * Changes the resolution of the game view.
     * @param x horizontal resolution
     * @param y vertical resolution
     */
    public void setGameViewResolution(int x, int y) {
        gameView.setPreferredSize(new Dimension(x, y));
        frame.pack();
    }
    
    /**
     * 
     * @return The game's main menu.
     */
    public Component getMainMenu() {
        return mainMenu;
    }
    
    /**
     * 
     * @return The game's resolution options menu.
     */
    public Component getOptionsMenu() {
        return optionsMenu;
    }
    
    /**
     * 
     * @return The game's in-game menu.
     */
    public Component getInGameMenu() {
        return inGameMenu;
    }
    
    /**
     * 
     * @return The game's save game menu.
     */
    public Component getSaveMenu() {
        return saveMenu;
    }
    
    /**
     * 
     * @return The game's load game menu.
     */
    public LoadGameMenu getLoadMenu() {
        return loadMenu;
    }
    
    /**
     * 
     * @return The game's tutorial menu.
     */
    public TutorialMenu getTutorialMenu() {
        return tutorialMenu;
    }
    
    /**
     * 
     * @return The currently active game level.
     */
    public GameLevel getActiveWorld() {
        return world;
    }
    
    /**
     * Set as active and populate a new GameLevel.
     * @param world the GameLevel to populate
     */
    public void setActiveWorld(GameLevel world) {
        this.world = world;
        this.world.populate(this);
        gameView.setWorld(world);
    }
    
    /**
     * Moves the game onto the next level in the constructor-initialized array list.
     */
    public void advanceLevel() {
        levelIndex++;
        if (levelIndex >= levelList.size()) {
            System.exit(0);
        } else {
            world.stop();
            setActiveWorld(levelList.get(levelIndex));
            gameView.loadBackground();
            world.addStepListener(new PlayerTracker(gameView, getPlayer()));
            keyboardController.setPlayer(getPlayer());
            mouseController.setBody(getPlayer());
            world.start();
        }
    }
    
    /**
     * 
     * @return The game's keyboard controller.
     */
    public KeyboardController getKeyboardController() {
        return keyboardController;
    }
    
    /**
     * 
     * @return The game's mouse controller.
     */
    public MouseController getMouseController() {
        return mouseController;
    }
}
