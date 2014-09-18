package org.elsys.internetPrograming.sampleJava;

public class Person {
	int age;

	Person(int age){
		this.age = age;
	}
	
	public boolean equals(Object other){
		if(other instanceof Person){
			Person peep = (Person) other;
			if(this.age == peep.age){
				return true;
			} 
			
			return false;
		}else {
			return false;
		}
	}

}
