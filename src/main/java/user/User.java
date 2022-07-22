package user;

/*Class User contains information about user id, firstName, lastName and amountOfMoney*/
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private double amountOfMoney;


    public User(int id , String firstName, String lastName, double amountOfMoney) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.amountOfMoney = amountOfMoney;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    @Override
    public String toString() {
        return "\nid: " + id +
                ", firstName: " + firstName +
                ", lastName: " + lastName +
                ", amountOfMoney: " + amountOfMoney;
    }
}
