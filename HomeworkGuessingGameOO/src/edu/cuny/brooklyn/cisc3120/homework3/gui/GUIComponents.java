/**
 * Jeff Morin
 * CISC3120-TR
 * 4/29/16
 *
 *  GUIComponents:
 *  A separate class for constructing all of the GUI's components
 *  and containers.
 * */

package edu.cuny.brooklyn.cisc3120.homework3.gui;

import javax.swing.*;
import java.awt.*;

public final class GUIComponents {
    private GUIComponents() {}

    protected static JFrame createFrame()
    {
        JFrame frame = new JFrame("The Guessing Game!");
        frame.setMinimumSize(new Dimension(500, 375));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(
                frame.getContentPane(),
                BoxLayout.Y_AXIS)
        );
        frame.setLocationByPlatform(true);
        return frame;
    }

    protected static JLabel createStatsLabel() {
        JLabel statsLabel = new JLabel("Wins: Losses: Streaks:",
                SwingConstants.LEADING);
        statsLabel.setFont(
                new Font("Arial", Font.PLAIN, 10));
        return statsLabel;
    }

    protected static JPanel createMainPanel()
    {
        JPanel mainPanel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(50);
        mainPanel.setLayout(borderLayout);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(0, 10, 0, 5));
        return mainPanel;
    }

    /** This method creates and returns JPanels that correspond to their
     left, center, top, and bottom positions in the main panel, or
     a plain JPanel object if an unmatched position is passed. **/
    protected static JPanel createSecondaryPanel(String position)
    {
        if (position.equalsIgnoreCase("left")) {
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(
                    new GridLayout(2, 0, 0, 10));
            return leftPanel;
        }
        else if (position.equalsIgnoreCase("center")) {
            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(
                    new BoxLayout(centerPanel,
                            BoxLayout.Y_AXIS));
            return centerPanel;
        }
        else if (position.equalsIgnoreCase("bottom")) {
            return new JPanel(new FlowLayout(FlowLayout.RIGHT));
        }
        else if (position.equalsIgnoreCase("top")) {
            return new JPanel(new FlowLayout(FlowLayout.CENTER,
                    0, 5));
        }
        else
            return new JPanel();
    }

    protected static JLabel createMainLabel()
    {
        JLabel mainLabel = new JLabel(
                "Guess a number between 1 and 16");
        mainLabel.setFont(
                new Font("Arial", Font.BOLD, 22));
        return mainLabel;
    }

    /**Creates the label above the guessLabel display. */
    protected static JLabel createSecondaryLabel()
    {
        JLabel secondaryLabel = new JLabel();
        secondaryLabel.setText("Your guess: ");
        secondaryLabel.setFont(
                new Font("Arial", Font.PLAIN, 12));
        return secondaryLabel;
    }

    /** Creates the label for displaying to user the number of guesses remaining and their previous guesses.*/
    protected static JLabel createProgressLabel() {
        JLabel progressLabel = new JLabel();
        progressLabel.setFont(
                new Font("Arial", Font.BOLD, 13));
        progressLabel.setText("You have 4 guesses.");
        return progressLabel;
    }

    /**Creates the label for displaying to user whether their guess was high, low, a loss or a win.*/
    protected static JLabel createFeedBackLabel()
    {
        JLabel feedbackLabel = new JLabel("Ready", SwingConstants.CENTER);
        feedbackLabel.setFont(
                new Font("Arial", Font.BOLD, 22)
        );
        return feedbackLabel;
    }

    /**Creates and returns the number submitted by the player to be displayed. */
    protected static JLabel createGuessLabel(JSpinner spinner)
    {
        JLabel guessLabel = new JLabel("1", SwingConstants.CENTER);
        guessLabel.setFont(
                new Font("Arial", Font.PLAIN, 150));
        guessLabel.setOpaque(true);
        guessLabel.setBackground(Color.white);
        guessLabel.setText(spinner.getValue().toString());
        guessLabel.setBorder(BorderFactory.createEtchedBorder());
        guessLabel.setMaximumSize(
                new Dimension(220, 220));
        return guessLabel;
    }

    /** This method creates, initializes and returns the game's guess spinner. **/
    protected static JSpinner createGuessSpinner()
    {
        SpinnerNumberModel guessModel = new SpinnerNumberModel(1, 1, 16, 1);
        JSpinner guessSpinner = new JSpinner(guessModel);
        guessSpinner.setPreferredSize(
                new Dimension(120, 120));
        Component editor = guessSpinner.getEditor().getComponent(0);
        editor.setForeground(new Color(142,143,145));

        guessSpinner.setFont(
                new Font("Arial", Font.BOLD, 40));

        return guessSpinner;
    }

    protected static JButton createSubmitButton()
    {
        JButton submit = new JButton("Submit Guess");
        submit.setFont(
                new Font("Arial", Font.BOLD, 13));

        return submit;
    }

    /**Creates and returns a toggle button to disable/enable sounds **/
    protected static JToggleButton createSoundToggle(Icon defaultIcon)
    {
        JToggleButton soundToggle = new JToggleButton(defaultIcon);
        return soundToggle;
    }

    /**Creates and returns the 'sound-on' image resource for sound toggle button. **/
    protected static ImageIcon createSoundOnIcon()
    {
        java.net.URL soundOnURL =
                GuessingGameGUIView.class.getResource(
                        "res/icons/soundOn.png");
        return new ImageIcon(soundOnURL);
    }

    /**Creates and returns the 'sound-off' image resource for sound toggle button. **/
    protected static ImageIcon createSoundOffIcon()
    {
        java.net.URL soundOffURL =
                GuessingGameGUIView.class.getResource(
                        "res/icons/soundOff.png");
        return new ImageIcon(soundOffURL);
    }
}