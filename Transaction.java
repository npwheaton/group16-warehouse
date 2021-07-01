import java.io.Serializable;

public class Transaction implements Serializable {
private static final long serialVersionUID = 1L;
private double amount;
private String date;
private Client cl;
public Transaction(double a, String d, Client c) {
amount=a;
date=d;
cl=c;
}
public double getTrans(){return amount;}
public String getdate(){return date;}
public Client getclient(){return cl;}
public String toString(){
String string = cl.getname() + " $" + amount+ " Date: "+ date;
return string;
}
}
