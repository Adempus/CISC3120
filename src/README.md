Homework 4
==========

The Guessing game GUI
---------------------

I have provided you with a skeleton of a GUI application.
Your homework assignment is to complete the application, making a GUI version of the guessing game from homework 1.

* Keep track of the number of attempts, and if too many have been made:
  * Disable the `submit` button.  (There is an example of how to do this in the code, search for `setEnabled`)
  * Indicate to the user that they have lost.  Mock them relentlessly.
* If the user has guessed the correct number:
  * Disable the `submit` button.
  * Indicate to the user that they have won.  Graciously admit defeat.
* Modify or replace the number input buttons to restrict the user to only valid options.
  * This means preventing inputs above or below the minimum and maximum numbers.
  * No non-numbers.
  * etc.
* If your input method allows for the user to input duplicate guesses, you must inform them that the guess is a duplicate.

### Ideas for Restricting Input ###

* Replace the number panel with a [`JSpinner`](https://docs.oracle.com/javase/tutorial/uiswing/components/spinner.html).
* Replace the number panel with a [`JSlider`](https://docs.oracle.com/javase/tutorial/uiswing/components/slider.html).
* If pressing a button would result in out of range input, disable it.

### Extra Credit ###

After a match, offer another game.

One way to accomplish this is to create another panel with a label asking for a rematch and buttons indicating `yes` and `no`.
After a match, hide the regular bottom panel, and display the rematch panel.
If they want a rematch, swap the panels again.  Otherwise, close the app.

You could also accomplish this in as much more annoying fashion with a [Dialog box](https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html).

(This can be made even more annoying by adding the image of an animated paperclip in the Dialog.)

