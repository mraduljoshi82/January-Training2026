package pack4;

class animal
{
	void sound()
	{
		System.out.println("animal makes a sound");
	}
}
class cat extends animal
{
	void sound()
	{
		System.out.println("cat makes sound");
	}
	
}
 
 
 
public class tc_overriding {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			animal a=new cat();
			a.sound();
	
		
		
		
		
	}
