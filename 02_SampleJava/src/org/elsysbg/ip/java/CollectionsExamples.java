package org.elsysbg.ip.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionsExamples {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mapper();
	}
	public static void mapper(){
		final Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("Bulgaria", 7);
		m.put("France", 9);
		m.put("Germany", 17);
		m.put("USSR", 90);
		m.put("USA", 13);
		m.put("Serbia", 5);
		for (Entry<String, Integer> next : m.entrySet()) {
			if(next.getValue()>10){
			System.out.printf("The population of %s is %d M\n",
					next.getKey(), next.getValue());
			}
		}
		
		
	}

}
