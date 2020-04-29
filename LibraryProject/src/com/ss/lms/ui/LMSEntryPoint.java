/**
 * 
 */
package com.ss.lms.ui;

import java.util.Scanner;

/**
 * @author seandarsie
 *
 */
public class LMSEntryPoint {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		LibraryInterface lms = new LibraryInterface(in);
		lms.start();
		in.close();
	}
}
