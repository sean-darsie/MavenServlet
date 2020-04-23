package com.smoothstack.training.lambdas;

import java.util.ArrayList;
import java.util.List;

public class NameListGenerator {
	public List<String> names()
	{
		List<String> names = new ArrayList<>();
		
		names.add("helga");
		names.add("germ");
		names.add("heidi");
		names.add("david");
		names.add("sven");
		names.add("khalid");
		names.add("albert");
		names.add("abc");
		names.add("bca");
		return names;
	}
	
	public String[] nameArray()
	{
		String[] nameArray = new String[4];
		nameArray[0] = "zyx";
		nameArray[1] = "mno";
		nameArray[2] = "abc";
		nameArray[3] = "eec";
		return nameArray;
	}
}
