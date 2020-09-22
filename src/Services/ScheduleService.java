package Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import Entities.Trainer;
import Entities.Training;
import Entities.Client;

public class ScheduleService {
    private ArrayList<Training> trainings;

    public ScheduleService(){
        this.trainings = new ArrayList<>();
    }

    public void addTraining(Training newTraining){
        proveTraining(newTraining);
        trainings.add(newTraining);
    }

    private void proveTraining(Training newTraining){
        Client client = newTraining.getClient();

        for(Training training : trainings){
            Client trainingClient = training.getClient();

            if (trainingClient != client)
                continue;

            LocalDateTime newTrainingTime = newTraining.getStartTime();
            boolean isConflict = training.isScheduleConflict(newTrainingTime);

            if (isConflict)
                throw new IllegalArgumentException("Client has training in this time");
        }
    }

    public Training findTraining(Client client, LocalDateTime time) {
        for (Training training : trainings) {
            if (training.sameTraining(client, time))
                return training;
        }

        return null;
    }
    public void changeCoach(Client client, LocalDateTime time, Trainer newTrainer){
        Training training = findTraining(client, time);

        if (training != null)
            training.setTrainer(newTrainer);
    }

    public void changeDate(Client client, LocalDateTime time, LocalDateTime newTime){
        Training training = findTraining(client, time);

        if(training != null)
            training.setStartTime(newTime);
    }

    public void removeTraining(Client client, LocalDateTime time){
        Training removedTraining = findTraining(client,time);

        if (removedTraining != null)
            trainings.remove(trainings);
    }

    @Override
    public String toString(){
        String returnedString="";

        for(Training training:trainings){
            returnedString += training.toString();
        }

        return returnedString;
    }
}
