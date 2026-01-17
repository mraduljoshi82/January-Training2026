package pack6;

public class TC_Exception {
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String str = null;
			str.equals("Hello");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Well Come to JAVA...");
 
	}
 
}