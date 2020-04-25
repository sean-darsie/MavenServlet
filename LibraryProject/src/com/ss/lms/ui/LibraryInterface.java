package com.ss.lms.ui;

import java.util.Scanner;

public class LibraryInterface {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		UserFlow userFlow = new UserFlow(in);
		userFlow.startLibrarySystem();
		in.close();
	}

}
