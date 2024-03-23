package com.audioSplitter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.audioSplitter.controller.PingController;
import com.mpatric.mp3agic.Mp3File;

public class TestDemo {

	public static void main(String[] args) {
		// Input audio file path
		String inputFile = "input_audio.mp3";

		// Output directory to save the split audio files
		String outputDir = "output";

		// Split sizes in bytes (10 MB and 10 MB)
		int splitSize1 = 10 * 1024 * 1024; // 10 MB
		int splitSize2 = 10 * 1024 * 1024; // 10 MB
		int splitSize3 = 5 * 1024 * 1024; // 5 MB

		try (FileInputStream fis = new FileInputStream(inputFile);
				BufferedInputStream bis = new BufferedInputStream(fis)) {
			byte[] buffer = new byte[splitSize1];
			int bytesRead;
			int partNumber = 1;
			while ((bytesRead = bis.read(buffer)) != -1) {
				String outputFile = outputDir + "/audio_part_" + partNumber + ".mp3";
				try (FileOutputStream fos = new FileOutputStream(outputFile);
						BufferedOutputStream bos = new BufferedOutputStream(fos)) {
					bos.write(buffer, 0, bytesRead);
				}
				partNumber++;
				if (partNumber == 2) {
					buffer = new byte[splitSize2];
				} else if (partNumber == 3) {
					buffer = new byte[splitSize3];
				}
			}
			System.out.println("Audio file split successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	

//	// Input audio file path
//	String inputFile = "/home/xr/Desktop/desi_hits_1.mp3";
//
//	// Output directory to save the split audio files
//	String outputDir = "/home/xr/Desktop/audio-parts/";
//
//	// Split sizes in bytes (10 MB and 10 MB)
//	int splitSize1 = 10 * 1024 * 1024; // 10 MB
//	int splitSize2 = 10 * 1024 * 1024; // 10 MB
//	int splitSize3 = 5 * 1024 * 1024; // 5 MB
//
//	try (FileInputStream fis = new FileInputStream(inputFile);
//			BufferedInputStream bis = new BufferedInputStream(fis)) {
//		byte[] buffer = new byte[splitSize1];
//		int bytesRead = bis.read(buffer);
//		int partNumber = 1;
//		while (partNumber < 4) {
//
//			String outputFile = outputDir + "/audio_part_" + partNumber + "-" + System.currentTimeMillis() + ".mp3";
//			try (FileOutputStream fos = new FileOutputStream(outputFile);
//					BufferedOutputStream bos = new BufferedOutputStream(fos)) {
//				bos.write(buffer, 0, bytesRead);
//			}
//			System.out.println("partNumber : " + partNumber);
//			partNumber++;
//			if (partNumber == 2) {
//				System.out.println("if. part-number: 2");
//				buffer = new byte[splitSize2];
//			} else if (partNumber == 3) {
//				System.out.println("else. part-number: 3");
//				buffer = new byte[splitSize3];
//			}
//
//			System.out.println("bytesRead : " + (bytesRead = bis.read(buffer)));
//		}
//		System.out.println("Audio file split successfully.");
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//
//}
//}

//	// File path of the MP3 audio file to split
//	String filePath = "/home/xr/Desktop/desi_hits_2.mp3";
//
//	// Output file paths for the three parts
//	String part1Path = "/home/xr/Desktop/part1.mp3";
//	String part2Path = "/home/xr/Desktop/part2.mp3";
//	String part3Path = "/home/xr/Desktop/part3.mp3";
//
//	// Split the MP3 file into three parts
//	try
//	{
//		splitMP3(filePath, part1Path, part2Path, part3Path);
//		System.out.println("MP3 file split successfully.");
//	}catch(
//	IOException e)
//	{
//		e.printStackTrace();
//	}
//}
//
//public static void splitMP3(String inputFilePath, String part1FilePath, String part2FilePath, String part3FilePath)
//		throws IOException {
//	// Open input stream for the MP3 file
//	try (FileInputStream fis = new FileInputStream(inputFilePath);
//			BufferedInputStream bis = new BufferedInputStream(fis)) {
//
//		// Open output streams for each part
//		try (BufferedOutputStream part1Bos = new BufferedOutputStream(new FileOutputStream(part1FilePath));
//				BufferedOutputStream part2Bos = new BufferedOutputStream(new FileOutputStream(part2FilePath));
//				BufferedOutputStream part3Bos = new BufferedOutputStream(new FileOutputStream(part3FilePath))) {
//
//			// Read the first part of the input file
//			byte[] buffer = new byte[1024];
//			int bytesRead;
//			int totalBytesRead = 0;
//
//			while ((bytesRead = bis.read(buffer)) != -1) {
//				// Write the data to the appropriate part file
//				if (totalBytesRead < 10 * 1024 * 1024) {
//					part1Bos.write(buffer, 0, bytesRead);
//				} else if (totalBytesRead < 20 * 1024 * 1024) {
//					part2Bos.write(buffer, 0, bytesRead);
//				} else {
//					part3Bos.write(buffer, 0, bytesRead);
//				}
//
//				totalBytesRead += bytesRead;
//			}
//			// Close all output streams
//			part1Bos.close();
//			part2Bos.close();
//			part3Bos.close();
//		}
//	}
		
		
		
		
}
}
