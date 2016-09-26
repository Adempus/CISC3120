/**
 *  Jeff Morin
 *  CISC3120-TR
 *  4/29/16
 *
 *  GuessingGameGUIController:
 *  Handles user interaction with the GUI, and coordinates
 *  messages between the model and view. The controller class is
 *  a singleton to prevent multiple occurrences.
 *
 * */

package edu.cuny.brooklyn.cisc3120.homework3.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class GuessingGameGUIController extends MouseAdapter
        implements ActionListener, ChangeListener
{
    private GuessingGameGUIModel model;
    private GuessingGameGUIView view;

    private GuessingGameGUIController() { }

    private static class SingletonHelper {
        private static final GuessingGameGUIController _guiController
                = new GuessingGameGUIController();
    }

    public static GuessingGameGUIController getInstance() {
        return SingletonHelper._guiController;
    }

    public void setModelView(GuessingGameGUIModel model,
                             GuessingGameGUIView view)
    {
        if(this.model != model && this.view != view) {
            this.model = model;
            this.view = view;
            this.view.addListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getActionCommand().equalsIgnoreCase("submit")) {
            model.submit();
            if (model.isRepeatedGuess()) {
                view.displayErrorMessage(model.getValue());
            } else {
                view.updateGuessLabel(model.getGuess());
                view.updateProgressLabel(model.getAttempts(),
                                         model.getGuessList());
            }
        }
        if (ae.getActionCommand().equalsIgnoreCase("replay")) {
            view.resetView();
            model.resetModel(view);
        }
        if (ae.getActionCommand().equalsIgnoreCase("toggle"))
            view.updateSoundOption();
    }

    /** Enables the controller to detect interactions on the spinner's
        buttons directly. **/
    @Override
    public void stateChanged(ChangeEvent ce) {
        model.setValue((Integer) view.getGuessSpinner()
                .getModel().getValue());
    }

    /** Highlight spinner text on mouse click. **/
    @Override
    public void mouseClicked(MouseEvent me) {
        ((JFormattedTextField) me.getSource()).selectAll();
    }
}