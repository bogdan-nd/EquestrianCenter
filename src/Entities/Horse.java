package Entities;
import Enums.HorsemanStatus;

import java.time.LocalDateTime;

public class Horse {
    private final String name;
    private final String ownerName;
    private final HorsemanStatus riderStatus;
    private final int maxLoad;
    private boolean isIll;
    private LocalDateTime lastTimeEat;
    private int price;

    public Horse(String name, String ownerName, HorsemanStatus riderStatus, int maxLoad, int price) {
        this.name = name;
        this.ownerName = ownerName;
        this.riderStatus = riderStatus;
        this.maxLoad = maxLoad;
        this.isIll = false;
        this.lastTimeEat = LocalDateTime.now();
        this.price = price;
    }

    public HorsemanStatus getRiderStatus() {
        return riderStatus;
    }

    public boolean IsIll(){
        return isIll;
    }

    public void fallIll(){
        isIll = true;
    }

    public void recover(){ isIll = false; }

    public int getMaxLoad() {
        return maxLoad;
    }

    public void eat(){
        this.lastTimeEat = LocalDateTime.now();
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return String.format("\nHorse: name - %s, for %ss, with max. load - %d kg.", name, riderStatus, maxLoad);
    }

    public int getPrice() {
        return price;
    }

    public String getName(){
        return name;
    }

    public LocalDateTime getLastTimeEat() {
        return lastTimeEat;
    }

};



