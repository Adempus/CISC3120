/** @author: Jeff Morin
 * 3/12/2016
 * CISC 3120-TR
 * Homework 4
 * */

package edu.cuny.brooklyn.cisc3120.homework4.gui;
import javax.swing.*;
import java.util.*;

public class MainGUI
{
    public static void main(String[] args)
    {
        Random randNumber = new Random();
        int target = randNumber.nextInt(16) + 1;

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex){
            System.out.println("Nimbus not available.");
        }

        GuessingGameGUI gui = new GuessingGameGUI(4, 16, target);
        gui.play();
    }
}
