//Isaac Lichter
package fractions;

public class Main {
    
    public static void main(String[] args) {
        
        Person isaac = new Person();
        
        String firstName = "Isaac";
        String lastName = "Lichter";
        
        isaac.setFirstName(firstName);
        isaac.setLastName(lastName);
        Date birthday = new Date(9, 3, 1997, 0);
        
        isaac.setBirthDay(birthday);
        
        firstName = "Nathan";
        lastName = "Vulakh";
        birthday.setYear(2000);
        
        System.out.println(isaac.getFirstName() + " " + isaac.getLastName());
        System.out.println(isaac.getBirthDay().getYear());
        
    }
    
}
