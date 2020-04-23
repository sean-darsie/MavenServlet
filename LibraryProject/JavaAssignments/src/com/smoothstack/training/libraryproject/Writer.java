package com.smoothstack.training.libraryproject;

import java.io.File;
import java.io.FileWriter;

public interface Writer {
	default public void writeToFile(String path, String data)
	{
		try(FileWriter fileWriter= new FileWriter(new File(path))){
			fileWriter.write(data);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Failed to write to file");
		}
	}
	
	default public void appendToFile(String path, String data)
	{
		try(FileWriter fileWriter= new FileWriter(new File(path),true)){
			fileWriter.append(data);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Failed to append to file");
		}
	}
}
