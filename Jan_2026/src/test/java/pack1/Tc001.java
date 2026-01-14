package pack1;



public class Tc001 {
	private static double width;
	private static double height;
	private static double depth;
	private static int boxid;
	
	public static  double volume() {
		double temp;
		temp = width * height * depth;
		return temp;
	}
	

	public static void main(String[] args) {
		
		System.out.println(volume());
		
	}

}