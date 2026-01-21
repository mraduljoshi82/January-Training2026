package pack7;

import java.io.*;
import java.util.*;
 
// Employee class
class Employee implements Serializable {
    int id;
    String name;
    double salary;
 
    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
 
    public String toString() {
        return id + " " + name + " " + salary;
    }
}
 
// Service + Main logic
public class lab4_3 {
 
    static void writeEmployees(List<Employee> list) throws IOException {
        ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream("employees.dat"));
        oos.writeObject(list);
        oos.close();
    }
 
    static List<Employee> readEmployees()
            throws IOException, ClassNotFoundException {
 
        ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream("employees.dat"));
        List<Employee> list = (List<Employee>) ois.readObject();
        ois.close();
        return list;
    }
 
    public static void main(String[] args) throws Exception {
 
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Amit", 50000));
        employees.add(new Employee(2, "Neha", 60000));
 
        writeEmployees(employees);
 
        List<Employee> result = readEmployees();
        for (Employee e : result) {
            System.out.println(e);
        }
    }
}
