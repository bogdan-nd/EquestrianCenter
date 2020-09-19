package Entities;

public class Workman{
    protected final String name;
    protected int salary;

    Workman(String name, int salary){
        this.name = name;
        this.salary = salary;
    }

    String getName(){
        return name;
    }

    public int getSalary(){
        return salary;
    }
}