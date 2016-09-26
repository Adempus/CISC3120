/**
 * Jeff Morin
 * CISC3120-TR
 * 4/14/16
 * */

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.BoxLayout;

public class MultiThread implements ActionListener
{
    // An object to retrieve images.
    private ImageLoader images;

	private void makeGui() {
        // Create the window.
        JFrame frame = new JFrame("Hello World Of Threading!");
        // Set the window size.
        frame.setMinimumSize(new Dimension(800, 400));
        // Close when you click the X.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Stack our components vertically.
        frame.setLayout(
                new BoxLayout(
                        frame.getContentPane(),
                        BoxLayout.Y_AXIS)
        );

        // Create three ImagePanel objects to hold
        // our images and add to frame
        ImagePanel panel1 = new ImagePanel();
        frame.add(panel1);
        ImagePanel panel2 = new ImagePanel();
        frame.add(panel2);
        ImagePanel panel3 = new ImagePanel();
        frame.add(panel3);

        // Set their sizes.
        panel1.setSize(200, 200);
        panel2.setSize(200, 200);
        panel3.setSize(200, 200);

        try {
            images = new ImageLoader("./res/images");
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }

        // Create three threads for each ImagePanel.
        // Send the panel, the array of image paths,
        // and sleep times in milliseconds as arguments.
        Thread t1 = createPanelThread(
                panel1, images.getImageSet1(), 1000
        );
        Thread t2 = createPanelThread(
                panel2, images.getImageSet2(), 2000
        );
        Thread t3 = createPanelThread(
                panel3, images.getImageSet3(), 3000
        );

        // Start all threads
        t1.start();
        t2.start();
        t3.start();

        frame.setVisible(true);
    }

    private Thread createPanelThread(ImagePanel panel,
                                     String[] imagePaths,
                                     long millis)
    {
        return new Thread(() -> {
            while (true) {
                try {
                    for (String path : imagePaths) {
                        panel.setPath(path);
                        Thread.sleep(millis);
                    }
                } catch (Exception ex) {
                    // if something goes wrong, print it and return.
                    // Things that can go wrong are setting the path
                    // to a file that does not exist, or is not a valid image.
                    System.out.println(ex.getMessage());
                    return;
                }
            }
        });
    }

	public void actionPerformed(ActionEvent e) { }

    public static void main(String[] args)
    {
        MultiThread app = new MultiThread();
        app.makeGui();
    }
}