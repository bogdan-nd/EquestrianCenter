package Entities;
import java.time.LocalDateTime;


public class ClubAccount {
    private int moneyAmount;
    private final int seedCapital = 1000000;

    public ClubAccount(){
        this.moneyAmount= seedCapital;
    }

    public void spendMoney(int wasteAmount){
        moneyAmount -= wasteAmount;
    }

    public void earnMoney(int earningAmount){
        moneyAmount += earningAmount;
    }

    @Override
    public String toString(){
        int earned = moneyAmount - seedCapital;

        return String.format("Equestrian Center earned %d$", earned);
    }
}

