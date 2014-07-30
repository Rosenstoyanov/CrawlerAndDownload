package com.hackbulgaria.corejava.networking2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

public class Download {
	public static void saveImage(String imageUrl, String destinationFile)
			throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}

	public static void main(String[] args) throws IOException {
		//Download();
		String imageUrl = "http://d3dsacqprgcsqh.cloudfront.net/photo/aozrdx0_700b.jpg";
		String destinationFile = "/home/rosen/img/image.jpg";
		File file = new File(destinationFile);
		file.createNewFile();
		saveImage(imageUrl, destinationFile);

	}

}
