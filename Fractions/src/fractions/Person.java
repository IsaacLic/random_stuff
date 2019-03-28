//Isaac Lichter
package fractions;

class Date {

    private int month, year, dayInMonth, nanoseconds;

    Date(Date that) {
        this.month = that.month;
        this.year = that.year;
        this.dayInMonth = that.dayInMonth;
        this.nanoseconds = that.nanoseconds;
    }

    public Date() {
        this.month = 0;
        this.dayInMonth = 0;
        this.year = 0;
        this.nanoseconds = 0;
    }

    public Date(int thatMonth, int thatDayInMonth, 
            int thatYear, int thatNanosecond) {
        this.month = thatMonth;
        this.dayInMonth = thatDayInMonth;
        this.year = thatYear;
        this.nanoseconds = thatNanosecond;
    }
    
    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDayInMonth(int dayInMonth) {
        this.dayInMonth = dayInMonth;
    }

    public void setNanoseconds(int nanoseconds) {
        this.nanoseconds = nanoseconds;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public int getDayInMonth() {
        return this.dayInMonth;
    }

    public int getNanoseconds(int nanoseconds) {
        return this.nanoseconds;
    }

}

class Employee extends Person {

    private double annualSalary;
    private String jobTitle;
    
    public Employee (int id, String firstName, String lastName, Date birthday,
            int annualSalary, String jobTitle){
        
        super.setId(id);
        super.setFirstName(firstName);       
        super.setLastName(lastName);
        super.setBirthDay(birthday);
        this.setAnnualSalary(annualSalary);
        this.setJobTitle(jobTitle);
    }
    
    public Employee (String firstName, String lastName, Date birthday){
        super.setFirstName(firstName);       
        super.setLastName(lastName);
        super.setBirthDay(birthday);
    }
    
    public Employee (String firstName, String lastName, int annualSalary){
        
        super.setFirstName(firstName);       
        super.setLastName(lastName);
        this.setAnnualSalary(annualSalary);
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setAnnualSalary(double s) {
        annualSalary = s;
    }

    public void setJobTitle(String jt) {
        jobTitle = jt;
    }
    
   
        @Override
    public String toString() { 
        return super.toString() + 
                String.format("annualSalary = [%.2f], jobTitle = [%s]", 
                        getAnnualSalary(), getJobTitle());
    }
}

public class Person {

    static int population;

    public Person() {

        System.out.printf("Mazel tov on a new Person!! #%d%n",
                ++Person.population);
        setId(Math.abs((int) (System.nanoTime() / 1000)));
    }
    private int id;
    private String firstName, lastName;
    private Date birthDay;

    public Date getBirthDay() {
        return new Date(birthDay);
    }

    public void setBirthDay(Date d) {
        birthDay = new Date(d);
    }

    public String getFirstName() {
        return new String(firstName);
    }

    public String getLastName() {
        return new String(lastName);
    }

    public void setFirstName(String s) {
        firstName = new String(s);
    }

    public void setLastName(String s) {
        lastName = new String(s);
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        if (_id < 0) {
            throw new RuntimeException(String.format(
                    "Id must be greater than 0. Id as [%d]",
                    _id));
        }
        id = _id;
    }

    public String toString() {
        return String.format("id=[%d], firstName=[%s], lastName=[%s]",
                id, firstName, lastName);
    }
}
