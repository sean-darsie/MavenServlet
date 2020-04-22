package com.smoothstack.training.libraryproject;

import java.io.BufferedReader;
import java.io.FileReader;

public interface Reader {
	default public String readFromFile(String path)
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		try(BufferedReader bufStream = new BufferedReader(new FileReader(path))){
			String line = bufStream.readLine();
			while(line!=null){
				if (line.contains(",") == false)
				{
					line = bufStream.readLine();
					continue;
				}
				stringBuilder.append(line+"\n");
				line = bufStream.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to read from author file");
		}
		
		return stringBuilder.toString();
	}
}
