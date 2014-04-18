//Rob Hicks and Cody Woodard.
//CS 4444
// Identification through noise psychology experiment.

import java.io.*;
import java.util.*;
import java.lang.Math;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class NoiseGenerator  {

	

Image image;
BufferedImage buffered;
Random r;
File outputfile;
private int noiseValues[];
int seperator = 0;
int width;
int height;
int index;
int threshold[];



// This function takes in a file from the main screen, changes it into a buffered image, and then adds noise to it on
// a pixel by pixel basis. No two images will have the same pixels changed because it is randomly selected.
public Image addNoiseToImage(File imageToBeChanged, int standardDeviation){

	r = new Random();
	try {
		image = ImageIO.read(imageToBeChanged);
	} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	
	
	buffered = (BufferedImage) image;
	width = image.getWidth(null);
	height = image.getHeight(null);
	//System.out.println("Width is: " +width+ "\nHeight is: " +height);
	index = 0;

	double noiseHolder;
	noiseValues = new int[100];
	int delay = 0;
	

	do {
		noiseHolder = (double) r.nextGaussian() * standardDeviation + Math.random() * 12; // find a gaussian number
		
		noiseValues[delay] = (int) noiseHolder;
		delay++;
		
	} while (delay < 100);
	
	int j;
	int i;
	int z;
	int l=0;
	
	for (j=0; j<height; j++){
		z = ((int) Math.floor((Math.random()*10)+1)); //this is where the number of pixels between each point of noise is selected,
													// currently, it ranges from 1 to 10 pixels  between each dot.
		//System.out.print("Z is: " + z+"\n");
		for(i=0;i<width;i++){
			if (l == z)	{
				index = (int) (Math.floor(Math.random() * 99)); // determines how strong the noise is.
				buffered.setRGB(i,j,noiseValues[index]);
				l = 0;
			}
			l++;
		}
		l=0;
	}
	
	
	
	return buffered;

}
}
