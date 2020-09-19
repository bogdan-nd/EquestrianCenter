package Entities;

import Enums.SportsCategory;

public final class Trainer extends Workman {
    private final SportsCategory sportCategory;
    private final int trainingPrice;

    public Trainer(String name, SportsCategory sportCategory, int salary, int trainingPrice){
        super(name,salary);
        this.sportCategory = sportCategory;
        this.trainingPrice = trainingPrice;
    }

    public SportsCategory getSportCategory() {
        return sportCategory;
    }

    public int getTrainingPrice(){
        return trainingPrice;
    }

    @Override
    public String toString(){
        return String.format("\nRough rider %s, sport category - %s, training price - %d$",
                name, sportCategory, trainingPrice);
    }
}
