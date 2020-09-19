package Entities;

import Entities.Workman;

public class Groom extends Workman {
    private final int carePrice;
    private int horseFedNumber;

    public Groom(String name, int salary, int carePrice){
        super(name,salary);
        this.carePrice = carePrice;
        this.horseFedNumber = 0;
    }

    public void giveFood(){
        horseFedNumber += 1;
    }

    public int getCarePrice(){
        return carePrice;
    }

    @Override
    public String toString(){
        return String.format("\nGroom %s, with care price - %d$", name, carePrice);
    }
}
