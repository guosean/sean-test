package com.sean.jdk8;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

public class MainClass {

	public static void main(String[] args) {
		// List<String> names = Lists.;
		List<String> names = new ArrayList<String>();
		names.add("Name1");
		names.add("Name2");
		System.out.println(names);
		/*List<String> lowercaseNames = names.stream().map((String name) -> {
			return name.toLowerCase();
		}).collect(Collectors.toList());
		System.out.println(lowercaseNames);*/
	}

}
