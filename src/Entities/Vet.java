package Entities;

import Entities.Workman;

public final class Vet extends Workman {
    private final int consultationPrice;
    private int recoveredHorsesNumber;

    public Vet(String name, int salary, int consultationPrice){
        super(name,salary);
        this.consultationPrice = consultationPrice;
        this.recoveredHorsesNumber = 0;
    }

    public int getConsultationPrice(){
        return consultationPrice;
    }

    public void recoverHorse(){
        recoveredHorsesNumber += 1;
    }

    public int getRecoveredHorsesNumber() {
        return recoveredHorsesNumber;
    }

    @Override
    public String toString(){
        return String.format("Vet %s, consultation Price - %d$", name, consultationPrice);
    }


}
