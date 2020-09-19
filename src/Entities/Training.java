package Entities;
import Enums.HorsemanStatus;
import Enums.SportsCategory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Training {
    private Trainer trainer;
    private final Horse horse;
    private final Client client;
    private LocalDateTime startTime;
    private final int duration;

    public Training(Trainer trainer, Horse horse, Client client, LocalDateTime startTime, int duration){
        proveTrainer(trainer, client);
        proveHorse(horse, client);
        proveClient(horse, client);
        this.client = client;
        this.trainer = trainer;
        this.horse = horse;
        this.startTime = startTime;
        this.duration = duration;
    }

    private static void proveTrainer(Trainer trainer, Client client){
        SportsCategory trainerCategory = trainer.getSportCategory();
        SportsCategory clientCategory = client.getSportCategory();
        if(trainerCategory.compareTo(clientCategory) < 0)
            throw new IllegalArgumentException("Trainer must have no less Sport Category level than client");
    }

    private static void proveHorse(Horse horse, Client client){
        HorsemanStatus clientStatus = client.getHorsemanStatus();
        HorsemanStatus horseRiderStatus = horse.getRiderStatus();
        if (clientStatus.compareTo(horseRiderStatus) != 0)
            throw new IllegalArgumentException("Horse rider`s status should be the same with client");
    }

    private static void proveClient(Horse horse, Client client){
        int horseMaxLoad = horse.getMaxLoad();
        int clientWeight = client.getWeight();
        if (clientWeight > horseMaxLoad)
            throw new IllegalArgumentException("Client`s weight should be no more than horse`s max.load");
    }

    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime(){
        return startTime;
    }

    public void setTrainer(Trainer trainer){
        proveTrainer(trainer, client);
        this.trainer = trainer;
    }

    public boolean sameTraining(Client secondClient, LocalDateTime secondDate){
        boolean sameClient = client.sameClient(secondClient);
        boolean sameDate = startTime.equals(secondDate);
        return sameClient && sameDate;
    }

    public boolean isScheduleConflict(LocalDateTime newStartTime){
        boolean isConflict = false;
        LocalDateTime endTime = startTime.plus(duration, ChronoUnit.MINUTES);
        if (newStartTime.compareTo(startTime) >0 && newStartTime.compareTo(endTime) < 0)
            isConflict = true;
        return isConflict;
    }

    public Client getClient(){
        return client;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    @Override
    public String toString(){
        LocalDateTime endTime = startTime.plus(duration, ChronoUnit.MINUTES);
        return String.format("\nTraining %tR - %tR %tD, client - %s, trainer - %s, horse - %s",
                startTime, endTime, startTime, client.getName(), trainer.getName(), horse.getName());
    }

}
