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

public class SingleThread implements ActionListener
{
    private ImageLoader images;

	void makeGui()
	{
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

        ImagePanel panel1 = new ImagePanel();
        ImagePanel panel2 = new ImagePanel();
        ImagePanel panel3 = new ImagePanel();

        try {
            // retrieve the images from the folder
            images = new ImageLoader("./res/images");
            // set the initial images for the panels
            panel1.setPath(images.getImageSet1()[2]);
            panel2.setPath(images.getImageSet2()[2]);
            panel3.setPath(images.getImageSet3()[2]);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);

        Thread thread = new Thread(() -> {
            while (true) {
                changeImages(panel1, images.getImageSet1(), 1000);
                changeImages(panel2, images.getImageSet2(), 2000);
                changeImages(panel3, images.getImageSet3(), 3000);
            }
        });

        thread.start();

		// Show our window.
		frame.setVisible(true);
	}

    private void changeImages(ImagePanel panel,
                              String[] imgPaths,
                              long millis) {
        try {
            for (String path : imgPaths) {
                panel.setPath(path);
                Thread.sleep(millis);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

	public void actionPerformed(ActionEvent e) { }

	public static void main(String[] args)
	{
		SingleThread app = new SingleThread();
		app.makeGui();
        return;
	}
}

