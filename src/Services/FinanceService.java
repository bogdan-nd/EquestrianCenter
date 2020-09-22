package Services;

import Entities.*;

import java.util.ArrayList;


public class FinanceService{
    private final ClubAccount clubAccount;

    public FinanceService(){

        this.clubAccount = new ClubAccount();
    }

    public void paySalaries(StaffService staffService){
        ArrayList<Groom> grooms = staffService.getGrooms();
        ArrayList<Trainer> trainers = staffService.getTrainers();
        ArrayList<Vet> vets = staffService.getVets();

        for(Groom groom: grooms){
            int groomSalary = groom.getSalary();
            clubAccount.spendMoney(groomSalary);
        }

        for(Vet vet:vets){
            int vetSalary = vet.getSalary();
            clubAccount.spendMoney(vetSalary);
        }

        for(Trainer trainer: trainers){
            int trainerSalary = trainer.getSalary();
            clubAccount.spendMoney(trainerSalary);
        }
    }

    public void trainingPayment(Training training){
        Client client = training.getClient();

        Trainer trainer = training.getTrainer();
        int trainingPrice = trainer.getTrainingPrice();

        client.spendMoney(trainingPrice);
        clubAccount.earnMoney(trainingPrice);
    }

    public void vetPayment(Vet vet, Client client){
        int vetConsultationPrice = vet.getConsultationPrice();
        client.spendMoney(vetConsultationPrice);

        clubAccount.earnMoney(vetConsultationPrice);
    }

    public void groomPayment(Groom groom, Client client){
        int groomCarePrice = groom.getCarePrice();
        client.spendMoney(groomCarePrice);

        clubAccount.earnMoney(groomCarePrice);
    }

    public void buyHorse(Horse horse){
        int horsePrice = horse.getPrice();

        clubAccount.spendMoney(horsePrice);
    }

    public void soldHorse(Horse horse){
        int horsePrice = horse.getPrice();

        clubAccount.earnMoney(horsePrice);
    }

    @Override
    public String toString(){
        return clubAccount.toString();
    }
}
