package pack3;

public class person {
	String FirstName;
	String LastName;
	char Gender;
	public person(String fn ,String ln , char g) {
		this.FirstName=fn;
		this.LastName=ln;
		this.Gender=g;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public char getGender() {
		return Gender;
	}
	public void setGender(char gender) {
		Gender = gender;
	}


	
}
