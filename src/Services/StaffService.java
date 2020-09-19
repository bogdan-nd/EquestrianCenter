package Services;

import Entities.Groom;
import Entities.Manager;
import Entities.Trainer;
import Entities.Vet;

import java.util.ArrayList;

public class StaffService {
    private ArrayList<Trainer> trainers;
    private ArrayList<Groom> grooms;
    private ArrayList<Vet> vets;
    private final Manager manager;

    public StaffService(Manager manager) {
        this.trainers = new ArrayList<>();
        this.grooms = new ArrayList<>();
        this.vets = new ArrayList<>();
        this.manager = manager;
    }

    public void hireStaff(Object staff){
        if(staff instanceof Trainer)
            trainers.add((Trainer) staff);
        else if(staff instanceof Groom)
            grooms.add((Groom) staff);
        else if(staff instanceof Vet)
            vets.add((Vet) staff);
    }

    public void fireStaff(Object staff){
        if(staff instanceof Trainer)
            trainers.remove(staff);
        else if(staff instanceof Groom)
            grooms.remove(staff);
        else if(staff instanceof Vet)
            vets.remove(staff);
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public ArrayList<Groom> getGrooms() {
        return grooms;
    }

    public ArrayList<Vet> getVets() {
        return vets;
    }

    @Override
    public String toString(){
        String returnString = "\nTrainers: ";
        for(Trainer trainer:trainers){
            returnString += trainer.toString();
        }
        returnString+="\nVets: ";
        for(Vet vet:vets){
            returnString+=vet.toString();
        }
        returnString += "\nGrooms: ";
        for(Groom groom:grooms){
            returnString += groom.toString();
        }
        return returnString;
    }
}
