/**
 *  Jeff Morin
 *  CISC3120-TR
 *  4/29/16
 *
 *  GuessingGameGUIModel
 *  contains the game's core logic and data
 *
 * */

package edu.cuny.brooklyn.cisc3120.homework3.gui;

import java.util.*;
import javax.swing.*;
import java.util.concurrent.*;
import edu.cuny.brooklyn.cisc3120.homework3.core.*;

public class GuessingGameGUIModel implements IGuesser
{
    private int attempts;
    private int value;
    private int guess;
    private List<Integer> guessList;
    private boolean isRepeated;
    private Semaphore lock;

    public GuessingGameGUIModel() {
        value = 1;
        lock = new Semaphore(0);
        guessList = Collections.synchronizedList(new ArrayList<>());
        isRepeated = false;
    }

    @Override
    public int nextGuess() {
        try {
            lock.acquire();
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
        return guess;
    }

    protected void submit() {
        isRepeated = guessList.contains(value);
        if (isRepeated) {
            return;
        } else {
            guess = value;
            attempts--;
            guessList.add(guess);
        }
        lock.release();
    }

    public boolean isRepeatedGuess() {
        return isRepeated;
    }

    protected void setValue (int value) {
        this.value = value;
    }

    public void setConfig (Configuration config) {
        this.attempts = config.getAllowedGuesses();
    }

    // gets the input value.
    public int getValue() {
        return value;
    }

    // gets the submitted value (the guess).
    public int getGuess() {
        return guess;
    }

    public int getAttempts() {
        return attempts;
    }

    public List<Integer> getGuessList() {
        return guessList;
    }

    protected void resetModel(GuessingGameGUIView view)
    {
        GuessingGameGUIModel model = this;
        SwingWorker<Void, Void> replayWorker
                = new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() {
                guessList.clear();
                MainGUI.startGame(model, view);
                return null;
            }
        };
        replayWorker.execute();
    }
}