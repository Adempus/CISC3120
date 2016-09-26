/**
 * Jeff Morin
 * CISC3120-TR
 *
 *  GuessingGameGUIView
 *  contains GUI components and manages their presentations.
 *
 * */

package edu.cuny.brooklyn.cisc3120.homework3.gui;

import edu.cuny.brooklyn.cisc3120.homework3.core.IClient;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;

public class GuessingGameGUIView implements IClient
{
    // GUI components
    private JButton submitButton;
    private JToggleButton soundToggle;
    private JSpinner guessSpinner;
    private ImageIcon soundOnIcon, soundOffIcon;
    private JLabel guessLabel, feedbackLabel,
                    progressLabel, statsLabel;

    // label colors
    final private Color
            HIGH_RED, LOW_BLUE, GLOOMY_GRAY, VICTORY_GREEN;

    // sound effects
    final private SoundEffects LOSE, WIN, WRONG;

    public GuessingGameGUIView() {
        HIGH_RED = new Color(214, 50, 50);
        LOW_BLUE = new Color(41, 106, 193);
        GLOOMY_GRAY = new Color(117, 119, 127);
        VICTORY_GREEN = new Color(34, 178, 34);
        WRONG = SoundEffects.BUZZER;
        WIN = SoundEffects.BELL;
        LOSE = SoundEffects.GAME_OVER;
        SoundEffects.init();
        createGUI();
    }

    /** addListener(): Adds event listeners to the components interacted with by the user.
     * @param controller -the listening object (GUI controller) that defines interaction responses
     *                    with methods implemented from EventListener derived interfaces.
     */
    protected void addListener(EventListener controller) {
        guessSpinner.addChangeListener((ChangeListener) controller);
        guessSpinner.getEditor().getComponent(0)
                .addMouseListener((MouseAdapter) controller);
        submitButton.addActionListener((ActionListener) controller);
        soundToggle.addActionListener((ActionListener) controller);
        soundToggle.setActionCommand("toggle");
        submitButton.setActionCommand("submit");
    }

    private void createGUI() {
        // Create all of the containers.
        JFrame window = GUIComponents.createFrame();
        JPanel mainPanel = GUIComponents.createMainPanel();
        JPanel topPanel = GUIComponents.createSecondaryPanel("top");
        JPanel leftPanel = GUIComponents.createSecondaryPanel("left");
        JPanel centerPanel = GUIComponents.createSecondaryPanel("center");
        JPanel bottomPanel = GUIComponents.createSecondaryPanel("bottom");

        // Create all of the components.
        JLabel mainLabel = GUIComponents.createMainLabel();
        JLabel secondaryLabel = GUIComponents.createSecondaryLabel();
        submitButton = GUIComponents.createSubmitButton();
        guessSpinner = GUIComponents.createGuessSpinner();
        guessLabel = GUIComponents.createGuessLabel(guessSpinner);
        feedbackLabel = GUIComponents.createFeedBackLabel();
        progressLabel = GUIComponents.createProgressLabel();
        statsLabel = GUIComponents.createStatsLabel();
        soundOffIcon = GUIComponents.createSoundOffIcon();
        soundOnIcon = GUIComponents.createSoundOnIcon();
        soundToggle = GUIComponents.createSoundToggle(soundOnIcon);

        // Add all of the components to their containers.
        topPanel.add(mainLabel);
        leftPanel.add(guessSpinner);
        leftPanel.add(submitButton);
        centerPanel.add(secondaryLabel);
        centerPanel.add(guessLabel);
        centerPanel.add(feedbackLabel);
        centerPanel.add(progressLabel);
        // bottomPanel.add(statsLabel);
        bottomPanel.add(soundToggle);

        // Add all of the containers to their positions in the main container.
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the main container to the window.
        window.add(mainPanel);
        // Show the window.
        window.setVisible(true);
    }

    @Override
    public void tooHigh(int guess) {
        feedbackLabel.setForeground(HIGH_RED);
        feedbackLabel.setText(guess + " is too high!");
        updateSoundFlag();
        playBuzzerSound();
    }
    @Override
    public void tooLow(int guess) {
        feedbackLabel.setForeground(LOW_BLUE);
        feedbackLabel.setText(guess + " is too low!");
        updateSoundFlag();
        playBuzzerSound();
    }
    @Override
    public void lose() {
        feedbackLabel.setForeground(GLOOMY_GRAY);
        feedbackLabel.setText("You Loose.");
        LOSE.playSound();
        updateSoundFlag();
        initReplay();
    }
    @Override
    public void win() {
        playWinEffect();
        feedbackLabel.setForeground(VICTORY_GREEN);
        feedbackLabel.setText("You Win!");
        WIN.playSound();
        initReplay();
    }

    protected void updateProgressLabel(int attempts,
                                       java.util.List<Integer> guessList)
    {
        String grammar = attempts != 1 ? " guesses left. " : " guess left. ";

        progressLabel.setText(attempts + grammar + "Past guesses: "
                + guessList.toString().replaceAll("[\\[\\]]", ""));
    }

    protected void updateGuessLabel(Integer value) {
        guessLabel.setText(value.toString());
    }

    protected void updateSoundOption() {
        if(soundToggle.isSelected()) {
            soundToggle.setIcon(soundOffIcon);
            SoundEffects.option = SoundEffects.SoundOption.SOUND_OFF;
        } else {
            soundToggle.setIcon(soundOnIcon);
            SoundEffects.option = SoundEffects.SoundOption.SOUND_ON;
        }
    }

    private int soundFlag = 4;

    private void playBuzzerSound() {
        if(soundFlag > 0) { WRONG.playSound(); }
    }

    private void updateSoundFlag() {
        soundFlag--;
    }

    private void playWinEffect() {
        ActionListener effect = new ActionListener() {
            int flashes = 0;
            public void actionPerformed(ActionEvent evt) {
                if (flashes < 8) {
                    flashes++;
                    guessLabel.setForeground(flashes % 2 == 0 ?
                            VICTORY_GREEN : Color.WHITE);
                }
            }
        };
        javax.swing.Timer timer = new javax.swing.Timer(145, effect);
        timer.start();
    }

    protected void displayErrorMessage(int value) {
        JOptionPane.showMessageDialog(
                null, "You've already guessed " + value + ".",
                "ID10T!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void initReplay() {
        submitButton.setText("Replay");
        submitButton.setActionCommand("replay");
    }

    public JSpinner getGuessSpinner() {
        return this.guessSpinner;
    }

    protected void resetView() {
        SwingWorker<Void, Void> replayWorker
                = new SwingWorker<Void, Void>()
        {
            @Override
            public Void doInBackground() {
                feedbackLabel.setForeground(Color.BLACK);
                feedbackLabel.setText("Ready");
                guessLabel.setForeground(Color.BLACK);
                guessLabel.setText("1");
                progressLabel.setText("4 guesses left.");
                submitButton.setText("Submit Guess");
                submitButton.setActionCommand("submit");
                guessSpinner.setValue(1);
                soundFlag = 4;
                return null;
            }
        };
        replayWorker.execute();
    }
}