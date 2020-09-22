package Entities;
import Enums.SportsCategory;
import Enums.HorsemanStatus;

import java.util.UUID;

public class Client{
    private final String name;
    private final HorsemanStatus horsemanStatus;
    private final int weight;
    private final UUID id;
    private int needToPay;
    private final SportsCategory sportCategory;

    public Client(String name, int weight, HorsemanStatus horsemanStatus, SportsCategory sportCategory){
        this.name = name;
        this.weight = weight;
        this.horsemanStatus = horsemanStatus;
        this.id = UUID.randomUUID();
        this.needToPay = 0;
        this.sportCategory = sportCategory;
    }

    public void spendMoney(int moneyAmount){
        needToPay += moneyAmount;
    }

    public SportsCategory getSportCategory() {
        return sportCategory;
    }

    public boolean sameClient(Client secondClient){
        return id.equals(secondClient.id);
    }

    public int getWeight(){
        return weight;
    }

    public HorsemanStatus getHorsemanStatus() {
        return horsemanStatus;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return String.format("\nClient %s, id - %s, %s, need to pay %d$", name, id.toString(), sportCategory, needToPay);
    }
}
