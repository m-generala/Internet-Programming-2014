
public class Main {
	public Main(int a,String b){
		String brand=b;
		int speed=a;
		int topspeed=0;
		if (topspeed < speed){
			topspeed=speed;
		}
		System.out.println(brand + "s' current speed is " + speed + "Km/h");
		System.out.println(brand + "s' top speed is " + (topspeed + 15));
	}
	public static void main(String[] args) {
	for(int i=100;i<260;i+=20){
		
		Main Mercedes = new Main(i,"Mercedes 190E");
		Main BMW = new Main(i+10,"BMW M3 E30");

	}
	
	}

}
