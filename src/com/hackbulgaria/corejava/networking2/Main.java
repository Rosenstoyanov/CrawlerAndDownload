package com.hackbulgaria.corejava.networking2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println("Input '1' to download");
		System.out.println("Or '2' for Crawler:");
		Scanner scanerChoice = new Scanner(System.in);
		int choice = scanerChoice.nextInt();
		if (choice == 1) {
			System.out.println("URL to Download from:");
			Scanner scanerUrl = new Scanner(System.in);
			String downloadUrl = scanerUrl.nextLine();
			System.out
					.println("Enther path to save img example '/home/user/folder/img.jpg'");
			Scanner scanerDestination = new Scanner(System.in);
			String destinationUrl = scanerUrl.nextLine();
			File destinationFile = new File(destinationUrl);
			destinationFile.createNewFile();
			Download.saveImage(downloadUrl, destinationUrl);

		}
		if (choice == 2) {
			System.out.println("URL to search in: ");
			Scanner scanerUrlToSearch = new Scanner(System.in);
			String urlToSearchIn = scanerUrlToSearch.nextLine();
			System.out.println("Phrase to search: ");
			Scanner scanerPhraseToSearch = new Scanner(System.in);
			String phraseToSearch = scanerPhraseToSearch.nextLine();
			URL resultUrl = null;
			URL UrlToSearchIn = new URL(urlToSearchIn);
			resultUrl = Crawler.crawler(UrlToSearchIn, phraseToSearch);
			System.out.println(resultUrl.toString());
		} else {
			System.out.println("Bad Input!!!");
		}
	}
}
