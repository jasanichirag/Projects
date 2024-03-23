package com.audioSplitter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.audioSplitter.controller.PingController;
import com.mpatric.mp3agic.Mp3File;

@SpringBootApplication

@Import({ PingController.class })
public class Application extends SpringBootServletInitializer {

	public static void splitMp3(String filePath, int partSizeInBytes) {
		try {

			File file = new File(filePath);
			String fileName = file.getName();
//			System.out.println(fileName);

			// Read the original MP3 file
			Mp3File mp3File = new Mp3File(filePath);
			int fileSize = (int) mp3File.getLength();

			// Calculate the number of parts needed
			int numParts = (int) Math.ceil((double) fileSize / partSizeInBytes);

			// Split the MP3 file into parts
			try (FileInputStream fis = new FileInputStream(filePath)) {

				byte[] buffer = new byte[partSizeInBytes];

				for (int i = 0; i < numParts; i++) {

					int bytesRead = fis.read(buffer, 0, partSizeInBytes);
					if (bytesRead > 0) {
						// Write the part to a new file
						File outputFile = new File("/home/xr/Desktop/audio-parts/part_" + (i + 1) + "-" + fileName);
						try (FileOutputStream fos = new FileOutputStream(outputFile)) {

							fos.write(buffer, 0, bytesRead);
						}
					}
				}
			}

			System.out.println("MP3 file split successfully into " + numParts + " parts.");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		System.out.println("Audio splitter project run...");

//		 Input audio file path
		String filePath = "/home/xr/Desktop/testing_audio.mp3";

		// Specify the size of each part in bytes (e.g., 10 MB = 10 * 1024 * 1024 bytes)
		int partSizeInBytes = 10 * 1024 * 1024;

		// Split the MP3 file
		splitMp3(filePath, partSizeInBytes);
	}
}

//		String filePath = "/home/xr/Desktop/desi_hits_1.mp3";
//		splitAudio(filePath);
//	}
//
//	public static void splitAudio(String filePath) {
//		try {
//			System.out.println("filePath : " + filePath);
//
//			// Open the audio input stream
//			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
//			AudioFormat format = audioInputStream.getFormat();
//			System.out.println("format : " + format);
////			long frameSize = format.getFrameSize();
////			System.out.println("frameSize : " + frameSize);
//			// Define threshold for silence detection
//			double silenceThreshold = 10 * 1024 * 1024; 
//
//			// Buffer for reading audio frames
//			byte[] buffer = new byte[(int) (10 * 1024 * 1024)]; // Adjust buffer size as needed
//			int bytesRead;
//
//			// Output file variables
//			int partNumber = 1;
//			ByteArrayOutputStream partStream = new ByteArrayOutputStream();
//			long partDuration = 0;
//
//			// Iterate over audio frames
//			while ((bytesRead = audioInputStream.read(buffer)) != -1) {
//
//				System.out.println("in while loop.................");
//				// Process audio data to detect silence
//				double amplitude = calculateAmplitude(buffer);
//				System.out.println("amplitude : " + amplitude);
//				boolean isSilent = amplitude < silenceThreshold;
//
//				System.out.println("isSilent : " + isSilent);
//				// Add audio data to current part
//				partStream.write(buffer, 0, bytesRead);
//				partDuration += bytesRead / format.getSampleRate() * 1000; // Calculate duration in
//				System.out.println("partDuration : " + partDuration); // milliseconds
//
//				// If silent, save part as separate file
//				if (isSilent && partDuration > 0) {
//					System.out.println("first...");
//					savePartToFile(partStream.toByteArray(),
//							"/home/xr/Desktop/audio-parts/output" + partNumber + ".mp3");
//					partNumber++;
//					partStream.reset();
//					partDuration = 0;
//				}
//			}
//
//			// Save the last part if not empty
//			if (partStream.size() > 0) {
//				System.out.println("second...");
//				savePartToFile(partStream.toByteArray(), "/home/xr/Desktop/audio-parts/output" + partNumber + ".mp3");
//			}
//
//			// Close the audio input stream
//			audioInputStream.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	// Calculate amplitude of audio frame
//	private static double calculateAmplitude(byte[] audioData) {
//		System.out.println("calculateAmplitude...");
//		long sum = 0;
//		for (int i = 0; i < audioData.length; i += 2) {
//			short sample = (short) ((audioData[i] & 0xFF) | (audioData[i + 1] << 8));
//			sum += sample * sample;
//		}
//		double rms = Math.sqrt(sum / (audioData.length / 2));
//		return rms;
//	}
//
//	// Save audio part to file
//	private static void savePartToFile(byte[] data, String fileName) {
//		System.out.println("savePartToFile : ");
//		try (FileOutputStream fos = new FileOutputStream(fileName)) {
//			fos.write(data);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
