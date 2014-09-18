
public class Main {
	public Main(int a,String b){
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
			Main Mercedes = new Main(i,"Mercedes 190E");
		}else{
			Main Mercedes = new Main(i-10,"Mercedes 190E");
		}
		Main BMW = new Main(i+10,"BMW M3 E30");
		
	}
		System.out.println("Aaaaannndd FINISH");
	
	
	}

}
