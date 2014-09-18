package org.elsys.internetPrograming.sampleJava;


public class Main {
	public static void writeCarSpeed(int a,String b){
		String brand=b;
		int speed=a;		
		System.out.println(brand + "s' current speed is " + speed + "Km/h");
	}
	
	public static void main(String[] args) {
		int check = 0;
		System.out.println("Flying start at 130Km/h");
		for(int i=130;i<260;i+=20){
			check+=1;
			System.out.println("-----------Checkpoint " + check + "-------------");
			if(i<190){
				writeCarSpeed(i,"Mercedes 190E");
			}else{
				writeCarSpeed(i-10,"Mercedes 190E");
			}
			writeCarSpeed(i+10,"BMW M3 E30");
			
		}
		System.out.println("Aaaaannndd FINISH");
		Person p1 = new Person(20);
		Person p2 = new Person(20);
		
		System.out.println(p1.equals(p2));
	}

}
