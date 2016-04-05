/** @author: Jeff Morin
 * 3/12/2016
 * CISC 3120-TR
 * Homework 4
 * */

package edu.cuny.brooklyn.cisc3120.homework4.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GuessingGameGUI implements ActionListener, KeyListener
{
    private int limit;
    private int maxInt;
    private int target;
    private int attempts;
    private int attemptsLeft;
    private List<Integer> guessList;
    private Stack<String> keyInputStack;

    // These components are members so they can be accessed in
    // actionPerformed(), and several other methods.
    private JLabel guessLabel;
    private JLabel feedbackLabel;
    private JLabel progressLabel;
    private JLabel instructionLabel;
    private JButton submit;
    private JToggleButton soundToggle;
    private JSpinner guessSpinner;
    private ImageIcon soundOnIcon;
    private ImageIcon soundOffIcon;

    // Custom colors
    final private Color VICTORY_GREEN = new Color(34, 178, 34);
    final private Color LOW_BLUE = new Color(40, 40, 211);
    final private Color HIGH_RED = new Color(178, 34, 34);

    // Sound effects
    final private SoundEffects WINNING_SOUND = SoundEffects.BELL;
    final private SoundEffects INCORRECT_SOUND = SoundEffects.BUZZER;
    final private SoundEffects LOOSING_SOUND = SoundEffects.GAME_OVER;

    public GuessingGameGUI(int limit, int maxInt, int target)
    {
        SoundEffects.init();    // load sounds into memory
        this.limit  = limit;
        this.maxInt = maxInt;
        this.target = target;
        guessList = new ArrayList<>(this.limit);
        keyInputStack = new Stack<>();
        progressLabel = new JLabel();
        instructionLabel = new JLabel();
        progressLabel.setFont(
                new Font("Arial", Font.BOLD, 13));
        progressLabel.setText("You have 4 guesses. ");
        instructionLabel.setFont(
                new Font("Arial", Font.PLAIN, 10));
        instructionLabel.setText("Use up/down arrow keys " +
                "or just type and enter the number to change guess. ");
        attemptsLeft = this.limit-1;
        attempts = 0;
    }

    private void handleSubmit()
    {
        keyInputStack.clear();
        guessLabel.setText(
                guessSpinner.getValue().toString());

        try {
            int value = (int) guessSpinner.getValue();

            // fire alert on repeated guesses.
            if (guessList.contains(value)) {
                JOptionPane.showMessageDialog(
                        null, "You've already guessed " + value + ".",
                        "ID10T!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            attempts++;
            trackAndUpdate(value);

            if (attempts == limit && value != target) {
                feedbackLabel.setText("You Loose. Number: " + target);
                LOOSING_SOUND.play();
                enableReplay();
                return;
            }
            if (value > target) {
                feedbackLabel.setForeground(HIGH_RED);
                feedbackLabel.setText("Too High!");
            } else if (value < target) {
                feedbackLabel.setForeground(LOW_BLUE);
                feedbackLabel.setText("Too Low!");
            } else {
                winningEffect();
                WINNING_SOUND.play();
                feedbackLabel.setForeground(VICTORY_GREEN);
                feedbackLabel.setText("You Win!");
                enableReplay();
                return;
            }
            INCORRECT_SOUND.play();

        } catch (NumberFormatException nfe) {
            // Ignore integer parse exception...
            // What can cause this exception to be thrown?
            feedbackLabel.setText("You're Dumb!");
        }
    }

    /** This method generates a new random
        number and resets all defaults for a new game. **/
    private void handleReplay()
    {
        target = (int) (Math.random() * maxInt + 1);
        feedbackLabel.setForeground(Color.BLACK);
        feedbackLabel.setText("Ready");
        guessLabel.setForeground(Color.BLACK);
        guessLabel.setText("1");
        progressLabel.setText(limit + " guesses left.");
        submit.setText("Submit Guess");
        submit.setActionCommand("submit");
        guessSpinner.setValue(1);
        guessList.clear();
        attemptsLeft = limit - 1;
        attempts = 0;
    }

    /** handler for toggle button press **/
    private void handleSoundToggle()
    {
        if(soundToggle.isSelected()) {
            soundToggle.setIcon(soundOffIcon);
            SoundEffects.option = SoundEffects.SoundOption.SOUND_OFF;
        } else {
            soundToggle.setIcon(soundOnIcon);
            SoundEffects.option = SoundEffects.SoundOption.SOUND_ON;
        }
    }

    private void handleNumber(int value)
    {
        String text = guessLabel.getText();
        if (text.equals("0")) text = "";
        text += Integer.toString(value);
        guessLabel.setText(text);
    }

    public void actionPerformed(ActionEvent e)
    {
        // You can "debug" your code by printing to stdout.
        // If you are using eclipse, the results will be written to the eclipse
        // log console.
        //
        // This command is printing out the action command for the button that
        // was pressed.

        //System.out.println(e.getActionCommand());

        // Is the button that triggered this event a number button?
        try {
            int value = Integer.parseInt(e.getActionCommand());
            handleNumber(value);
            return;
        } catch (NumberFormatException nfe) {
            // Ignore integer parse exception...
            // This happens when "submit" or "BS" is pressed.
        }

        // Is it the submit button?
        if (e.getActionCommand().equals("submit")) {
            handleSubmit();
            return;
        }
        // replay button?
        if (e.getActionCommand().equals("replay")) {
            handleReplay();
        }
        // or sound toggle button?
        if (e.getActionCommand().equals("toggle")) {
            handleSoundToggle();
        }
    }

    /** enable spinner suggestibility on numeric keystrokes.
        NOTE: any key value backed by a valid unicode character invokes the keyTyped method
     **/
    public void keyTyped(KeyEvent key)
    {
        char keyInput = key.getKeyChar();

        if (Character.isDigit(keyInput)) {
            if (keyInputStack.size() == 2 || (keyInputStack.size() == 1 &&
                    new Integer(keyInputStack.peek()) > 1)) {
                keyInputStack.clear();
            }

            keyInputStack.push(Character.toString(keyInput));
            Integer number = new Integer(keyInputStack.toString()
                    .replaceAll("\\D", ""));

            try {
                guessSpinner.setValue(number);
            } catch (IllegalArgumentException iae) {
                guessSpinner.setValue(new Integer(keyInputStack.pop()));
                keyInputStack.clear();
            }
        }
    }

    /** This method makes it so that the spinner's value can be changed
        using the UP or DOWN arrow keys.
     **/
    public void keyPressed(KeyEvent key)
    {
        instructionLabel.setVisible(false);
        try {
            switch (key.getKeyCode()) {
                case KeyEvent.VK_UP :
                    guessSpinner.getModel()
                            .setValue(guessSpinner.getNextValue());
                    break;
                case KeyEvent.VK_DOWN :
                    guessSpinner.getModel()
                            .setValue(guessSpinner.getPreviousValue());
                    break;
            }
        } catch(IllegalArgumentException iae) {
            // The range of the spinner's model is fixed,
            // so out of range values won't be written to it anyway.
        }
    }

    public void keyReleased(KeyEvent e) { }

    private JFrame createFrame()
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

    private JPanel createMainPanel()
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
    private JPanel createSecondaryPanel(String position)
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

    private JLabel createMainLabel()
    {
        JLabel mainLabel = new JLabel(
                "Guess a number between 1 and " + maxInt);
        mainLabel.setFont(
                new Font("Arial", Font.BOLD, 22));
        return mainLabel;
    }

    private JLabel createSecondaryLabel()
    {
        JLabel secondaryLabel = new JLabel();
        secondaryLabel.setText("Your guess: ");
        secondaryLabel.setFont(
                new Font("Arial", Font.PLAIN, 12));
        return secondaryLabel;
    }

    private JLabel createFeedBackLabel()
    {
        JLabel feedbackLabel = new JLabel("Ready", SwingConstants.CENTER);
        feedbackLabel.setFont(
                new Font("Arial", Font.BOLD, 22)
        );
        return feedbackLabel;
    }

    private JLabel createGuessLabel()
    {
        JLabel guessLabel = new JLabel("1", SwingConstants.CENTER);
        guessLabel.setFont(
                new Font("Arial", Font.PLAIN, 150));
        guessLabel.setOpaque(true);
        guessLabel.setBackground(Color.white);
        guessLabel.setText(guessSpinner.getValue().toString());
        guessLabel.setBorder(BorderFactory.createEtchedBorder());
        guessLabel.setMaximumSize(
                new Dimension(220, 220));
        return guessLabel;
    }

    /** This method creates, initializes and returns the guess spinner. **/
    private JSpinner createGuessSpinner()
    {
        Integer[] guessRange = new Integer[maxInt];
        for (int i = 1 ; i <= guessRange.length ; i++) {
            guessRange[i - 1] = i;
        }
        SpinnerListModel guessModel = new SpinnerListModel(guessRange);
        JSpinner guessSpinner = new JSpinner(guessModel);
        guessSpinner.setPreferredSize(
                new Dimension(120, 120));
        guessSpinner.setFont(
                new Font("Arial", Font.BOLD, 40));
        // Make spinner contents read-only:
        guessSpinner.getEditor().getComponent(0).setEnabled(false);
        return guessSpinner;
    }

    private JButton createSubmitButton()
    {
        // The local 'submit' JButton instance shadows the global 'submit' JButton instance.
        JButton submit = new JButton("Submit Guess");
        submit.setActionCommand("submit");
        submit.setFont(
                new Font("Arial", Font.BOLD, 13));
        submit.addActionListener(this);
        submit.addKeyListener(this);
        return submit;
    }

    /** create a toggle button to disable/enable sounds **/
    private JToggleButton createSoundToggle()
    {
        JToggleButton soundToggle = new JToggleButton(soundOnIcon);
        soundToggle.addActionListener(this);
        soundToggle.setActionCommand("toggle");
        return soundToggle;
    }

    /** retrieves 'sound-on' image resource for sound toggle button. **/
    private ImageIcon createSoundOnIcon()
    {
        java.net.URL soundOnURL =
                GuessingGameGUI.class.getResource(
                        "res/icons/soundOn.png");
        return new ImageIcon(soundOnURL);
    }

    /** retrieves 'sound-off' image resource for sound toggle button. **/
    private ImageIcon createSoundOffIcon()
    {
        java.net.URL soundOffURL =
                GuessingGameGUI.class.getResource(
                        "res/icons/soundOff.png");
        return new ImageIcon(soundOffURL);
    }

    /** method to track previous guesses, and update
        the remaining number of attempts. **/
    private void trackAndUpdate(int guess)
    {
        guessList.add(guess);
        String grammar = attemptsLeft != 1 ? " guesses left. "
                : " guess left. ";
        progressLabel.setText(attemptsLeft + grammar +
                " Past guesses: " + guessList.toString()
                .replaceAll("[\\[\\]]", ""));
        attemptsLeft--;
    }

    /** method to intermittently flash the winning guess.
     * (it is somehow hindered by other running processes) **/
    private void winningEffect()
    {
        ActionListener effect = new ActionListener() {
            int flashes = 0;
            public void actionPerformed(ActionEvent evt) {
                if (flashes < 8) {
                    flashes++;
                    if (flashes % 2 == 0)
                        guessLabel.setForeground(VICTORY_GREEN);
                    else
                        guessLabel.setForeground(Color.WHITE);
                }
            }
        };
        javax.swing.Timer timer =
                new javax.swing.Timer(145, effect);
        timer.start();
    }

    public void play()
    {
        // Create all of the containers.
        JFrame window = createFrame();
        JPanel mainPanel = createMainPanel();
        JPanel topPanel = createSecondaryPanel("top");
        JPanel leftPanel = createSecondaryPanel("left");
        JPanel centerPanel = createSecondaryPanel("center");
        JPanel bottomPanel = createSecondaryPanel("bottom");

        // Create all of the components.
        JLabel mainLabel = createMainLabel();
        JLabel secondaryLabel = createSecondaryLabel();
        submit = createSubmitButton();
        guessSpinner = createGuessSpinner();
        guessLabel = createGuessLabel();
        feedbackLabel = createFeedBackLabel();
        soundOffIcon = createSoundOffIcon();
        soundOnIcon = createSoundOnIcon();
        soundToggle = createSoundToggle();

        // Add all of the components to their containers.
        topPanel.add(mainLabel);
        leftPanel.add(guessSpinner, submit);
        leftPanel.add(submit);
        centerPanel.add(secondaryLabel);
        centerPanel.add(guessLabel);
        centerPanel.add(feedbackLabel);
        centerPanel.add(progressLabel);
        bottomPanel.add(instructionLabel);
        bottomPanel.add(soundToggle);

        // Add all of the containers to their positions in the main container.
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the main container to the window.
        window.add(mainPanel);
        window.setVisible(true);
    }

    /** replay mechanism **/
    private void enableReplay()
    {
        submit.setText("Replay");
        submit.setActionCommand("replay");
    }
}