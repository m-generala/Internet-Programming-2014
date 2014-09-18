package org.elsys.internetPrograming.sampleJava;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int someVariable = 5;
		int otherVariable = ++someVariable;
		for(int i = 1; i < 100; i++){
			if(i % 2 == 0){
				System.out.println("It's even " + i);
			}
		}
		
		Rabbit firstRabbit = new Rabbit(10, Sex.Male, "Gosho", "Traktorist");
		Rabbit secondRabbit = new Rabbit(10, Sex.Male, "Gosho", "Traktorist");
		
		System.out.println(firstRabbit.equals(secondRabbit));
	}

}
