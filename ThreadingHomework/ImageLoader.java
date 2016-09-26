/**
 * Jeff Morin
 * CISC3120-TR
 * 4/14/16
 *
 *  ImageLoader:
 *  Retrieves image paths from a folder, and loads them into
 *  arrays to be distributed via an object of this class.
 *
 *
 * */

import java.io.File;

public class ImageLoader
{
    // create an array to collect the paths of all images in the directory
    private File[] imagePaths;

    // Create three arrays to hold the paths of images
    // that will be shown for the three slides
    private String[] imageSet1;
    private String[] imageSet2;
    private String[] imageSet3;

    public ImageLoader(String directory)
            throws NullPointerException
    {
        // Retrieve all the image file paths from the directory.
        imagePaths = new File(directory).listFiles();

        int numberOfImgs = imagePaths.length;   // get the number of images that the file contains;
        int imgsPerArray = numberOfImgs / 3;    // calculate (roughly) how many images each array will hold;
        int remainingImgs = numberOfImgs % 3;   // calculate any left over images from the distribution;

        // The images will be distributed evenly in all the arrays,
        // or the size of the third array will accommodate for the disproportion.
        imageSet1 = new String[imgsPerArray];
        imageSet2 = new String[imgsPerArray];
        imageSet3 = new String[imgsPerArray + remainingImgs];

        // Load the image paths into each array.
        for(int i = 0, j = 0 ; i < numberOfImgs ; i++,
                j = (j != imgsPerArray-1 ? j+1 : 0)) {
            if (i < imgsPerArray) {
                imageSet1[j] = getImagePathAt(i);
            } else if (i >= imgsPerArray && i < imgsPerArray * 2) {
                imageSet2[j] = getImagePathAt(i);
            } else {
                // nested loop as a safeguard for disproportionate indexes.
                for(j = 0 ; j < imageSet3.length ; j++, i++) {
                    imageSet3[j] = getImagePathAt(i);
                }
                break;
            }
        }
    }

    protected String getImagePathAt(int index) {
        return imagePaths[index].toString();
    }

    public String[] getImageSet1() {
        return imageSet1;
    }

    public String[] getImageSet2() {
        return imageSet2;
    }

    public String[] getImageSet3() {
        return imageSet3;
    }
}