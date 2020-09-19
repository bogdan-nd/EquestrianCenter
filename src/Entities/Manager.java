package Entities;

import Entities.Workman;

public class Manager extends Workman {
    public Manager(String name, int salary){
        super(name,salary);
    }

    @Override
    public String toString(){
        return "Manager " + name;
    }

}
