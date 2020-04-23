package com.smoothstack.training.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaExamples {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NameListGenerator nameList = new NameListGenerator();
		List<String> names = nameList.names();
		String[] nameArray = nameList.nameArray();
		
		
		System.out.println(names);
		System.out.println();
		
		// sort by length ascending
		List<String> lengthSorted = names.stream().sorted((aName, bName) -> aName.length() - bName.length()).collect(Collectors.toList());
		System.out.println("names after being sorted by length ascending");
		System.out.println(lengthSorted);
		System.out.println();

		// sort by length Descending
		lengthSorted = names.stream().sorted((aName, bName) -> bName.length() - aName.length()).collect(Collectors.toList());
		System.out.println("names after being sorted by length descending");
		System.out.println(lengthSorted);
		System.out.println();
		
		// sort alphabetically
		Arrays.sort(nameArray,(a,b) -> a.charAt(0) - b.charAt(0));
		System.out.println("names after being sorted by alphabet");
		System.out.println(nameArray[0]+"\n"+nameArray[1]+"\n"+nameArray[2]+"\n"+nameArray[3]);
		System.out.println();
		
		// strings containing e go first 
		List<String> ePriority = names.stream().filter(x -> x.contains("e")).collect(Collectors.toList());
		ePriority.addAll(names.stream().filter(x -> !x.contains("e")).collect(Collectors.toList()));
		System.out.println("names after being sorted by containing e");
		System.out.println(ePriority);
		System.out.println();
		
		// strings containing e go first 
		Arrays.sort(nameArray,(a,b) -> compareFirstLetter(a,b));
		System.out.println("names after being sorted by e");
		System.out.println(nameArray[0]+"\n"+nameArray[1]+"\n"+nameArray[2]+"\n"+nameArray[3]);
		System.out.println();
		
		
		
		
	}
	
	public static int compareFirstLetter(String a, String b)
	{
		if (a.contains("e"))
			return -1;
		if (b.contains("e"))
			return 1;
		else
			return 0;
		
	}

}
