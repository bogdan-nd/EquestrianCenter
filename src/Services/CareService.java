package Services;

import Entities.Horse;
import Entities.Vet;
import Entities.Groom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class CareService{

    public ArrayList<Horse> getIllHorses(ArrayList<Horse> horses){
        ArrayList<Horse> illHorses = new ArrayList<>();
        for(Horse horse: horses){
            boolean horseIsIll = horse.IsIll();
            if (!horseIsIll)
                continue;
            illHorses.add(horse);
        }
        return illHorses;
    }

    public ArrayList<Horse> getHungryHorses(ArrayList<Horse> horses) {
        ArrayList<Horse> hungryHorses = new ArrayList<>();
        int getHungerHours = 5;
        for (Horse horse : horses) {
            LocalDateTime eatLastTime = horse.getLastTimeEat();
            LocalDateTime hadToEat = eatLastTime.plus(getHungerHours, ChronoUnit.HOURS);
            boolean isHungry = hadToEat.isBefore(LocalDateTime.now());
            if (isHungry)
                hungryHorses.add(horse);
        }
        return hungryHorses;
    }

    public void recoverHorse(Horse illHorse, Vet vet) {
        illHorse.recover();
        vet.recoverHorse();
    }

    public void giveFood(Horse hungryHorse, Groom groom){
        hungryHorse.eat();
        groom.giveFood();
    }

}
