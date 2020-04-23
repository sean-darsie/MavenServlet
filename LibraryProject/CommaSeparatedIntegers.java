package com.smoothstack.training.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CommaSeparatedIntegers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> list = new ArrayList<>();
		list.add(1144);
		list.add(223);
		list.add(34321);
		list.add(445);
		
		List<String> strings = new NameListGenerator().names();
		
		System.out.println("make a comma separated string with integers from a list");
		System.out.println(commaSeparate(list));
		System.out.println("");
		System.out.println("only print 3 letter words starting with a");
		System.out.println("original: "+strings);
		System.out.println("Final: "+findThreeLetterStrings(strings));

	}
	
	public static String commaSeparate(List<Integer> input)
	{
		String result = input.stream().map(a -> a %2 == 0 ? "e"+a : "o"+a)
		        .collect(Collectors.joining(","));
		
		return result;
	}
	
	public static String utilFunction(Integer input)
	{
		if (input %2 == 0)
		{
			return "e"+input;
		}
		else
		{
			return "o"+input;
		}
	}
	
	public static List<String> findThreeLetterStrings(List<String> strings)
	{
		List<String> result = strings.stream().filter(a -> a.charAt(0) == 'a').filter(a->a.length() == 3).collect(Collectors.toList());
		return result;
	}

}
