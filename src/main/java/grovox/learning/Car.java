package grovox.learning;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private String manufacturer;
    private String number;
    private int year;
    private String owner;
    private List<String> owners;

    public Car(String manufacturer, String number, int year, String owner) {
        this.manufacturer = manufacturer;
        this.number = number;
        this.year = year;
        this.owner = owner;
        owners = new ArrayList<>();
        owners.add(owner);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
        owners.add(owner);
    }

    public List<String> getOwners() {
        return owners;
    }

    private String testMethod(){
        return "abc";
    }

    private String testMethod(String a){
        return a + "abc";
    }

    public String getDataFromRemoteServer() throws Exception{
        throw new Exception("error!");
    }

    public int testInt(int a){
        return a + 4;
    }
}
