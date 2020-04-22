/**
 * 
 */
package com.smoothstack.training.libraryproject;

import java.util.Scanner;

/**
 * @author seandarsie
 *
 */
public class LibraryInterface {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		UserFlow userFlow = new UserFlow(in);
		userFlow.startLibrarySystem();
		in.close();
	}

}