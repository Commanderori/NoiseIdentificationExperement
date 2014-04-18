
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
	System.out.println("Width is: " +width+ "\nHeight is: " +height);
	index = 0;

	double noiseHolder;
	noiseValues = new int[100];
	int delay = 0;
	

	do {
		noiseHolder = (double) r.nextGaussian() * standardDeviation + Math.random() * 12;
		
		noiseValues[delay] = (int) noiseHolder;
		delay++;
		
	} while (delay < 100);
	
	int j;
	int i;
	int z;
	int l=0;
	
	for (j=0; j<height; j++){
		z = ((int) Math.floor((Math.random()*10)+1));
		System.out.print("Z is: " + z+"\n");
		for(i=0;i<width;i++){
			if (l == z)	{
				index = (int) (Math.floor(Math.random() * 99));
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
