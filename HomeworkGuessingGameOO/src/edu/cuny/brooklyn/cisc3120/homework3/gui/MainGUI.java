package edu.cuny.brooklyn.cisc3120.homework3.gui;

import javax.swing.*;
import edu.cuny.brooklyn.cisc3120.homework3.core.Configuration;
import edu.cuny.brooklyn.cisc3120.homework3.core.Game;
import edu.cuny.brooklyn.cisc3120.homework3.core.IChooser;
import edu.cuny.brooklyn.cisc3120.homework3.core.RandomChooser;

public class MainGUI
{
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    "javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            System.out.println(ex.getMessage()
                    + "\n Nimbus not available");
        }
        GuessingGameGUIModel model = new GuessingGameGUIModel();
        GuessingGameGUIView view = new GuessingGameGUIView();
        GuessingGameGUIController controller = GuessingGameGUIController.getInstance();
        controller.setModelView(model, view);

        startGame(model, view);
    }

    public static void startGame(GuessingGameGUIModel model,
                                 GuessingGameGUIView view) {
        try {
            Configuration config = new Configuration(16, 4);
            IChooser randomChooser = new RandomChooser(config);
            Game game = new Game(randomChooser, model, view, config);
            model.setConfig(config);
            game.play();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}