package pack4;




import java.time.LocalDate;
import java.time.Period;

class Person {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public Person(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    // (a) Method to calculate age
    public int calculateAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    // (b) Method to return full name
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void displayDetails() {
        System.out.println("Full Name: " + getFullName());
        System.out.println("Age: " + calculateAge());
    }
}

public class lab15 {

    public static void main(String[] args) {
        Person p = new Person("Gungun", "Choudhary", LocalDate.of(2002, 3, 18));
        p.displayDetails();
    }
}

