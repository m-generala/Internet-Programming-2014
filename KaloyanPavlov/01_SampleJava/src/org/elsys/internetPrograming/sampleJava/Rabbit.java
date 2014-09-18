package org.elsys.internetPrograming.sampleJava;

public class Rabbit {
	private int age;
	private Sex sex;
	private String name;
	private String jobName;

	public Rabbit(int age, Sex sex, String name, String jobName){
		
	}
	
	
	public int getAge() {		
		return age;
	}


	public void setAge(int age) {
		if(age < 0){
			throw new IllegalArgumentException("Age must be a positive number");
		}
		
		this.age = age;
	}


	public Sex getSex() {
		return sex;
	}


	public void setSex(Sex sex) {
		this.sex = sex;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public boolean equals(Object other){
		Rabbit otherRabbit = other instanceof Rabbit ? (Rabbit) other : null;
		
		if(otherRabbit == null){
			return false;
		}
		
		if(otherRabbit.age == this.age && otherRabbit.name == this.name 
				&& otherRabbit.sex == this.sex 
				&& otherRabbit.jobName == this.jobName){
			return true;
		}
	
		
		return false;
	}
}
