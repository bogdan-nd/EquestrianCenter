import Entities.*;
import Services.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public final class EquestrianCenter{
    private final String name;
    private final StaffService staffService;
    private final CareService careService;
    private final ScheduleService scheduleService;
    private final FinanceService financeService;
    private final StallService stallService;
    private final ArrayList<Client> clients;
    private final LocalDateTime establishedDate;

    public EquestrianCenter(String centerName, Manager manager){
        this.name = centerName;
        this.staffService = new StaffService(manager);
        this.careService = new CareService();
        this.scheduleService = new ScheduleService();
        this.financeService = new FinanceService();
        this.stallService = new StallService();
        this.clients = new ArrayList<>();
        this.establishedDate = LocalDateTime.now();
    }

    public void addClient(Client client){
        clients.add(client);
    }

    public void addHorse(Horse horse){
        stallService.addHorse(horse);
    }

    public void addClientWithHorse(Horse horse, Client client){
        if (!clients.contains(client))
            clients.add(client);
        stallService.addHorse(horse,client);
    }

    public void buyHorse(Horse horse){
        financeService.buyHorse(horse);
        stallService.addHorse(horse);
    }

    public void recoverHorse(Horse horse, Vet vet){
        careService.recoverHorse(horse,vet);
        boolean isClientHorse = stallService.isClientHorse(horse);
        if (!isClientHorse)
            return;
        Client horseOwner = stallService.getHorseOwner(horse);
        financeService.vetPayment(vet, horseOwner);
    }

    public void careHorse(Horse horse, Groom groom){
        careService.giveFood(horse,groom);
        boolean isClientHorse = stallService.isClientHorse(horse);
        if (!isClientHorse)
            return;
        Client horseOwner = stallService.getHorseOwner(horse);
        financeService.groomPayment(groom,horseOwner);
    }

    public void soldHorse(Horse horse){
        financeService.soldHorse(horse);
        stallService.removeHorse(horse);
    }

    public void addTraining(Training training){
        scheduleService.addTraining(training);
    }

    public void finishTraining(Client client, LocalDateTime time) {
        Training training = scheduleService.findTraining(client, time);
        scheduleService.removeTraining(client, time);
        financeService.trainingPayment(training);
    }

    public void hireStaff(Object workman){ staffService.hireStaff(workman);}

    public void fireStaff(Object workman){ staffService.fireStaff(workman);}

    public void changeTrainingCoach(Client client, LocalDateTime time, Trainer trainer){
        scheduleService.changeCoach(client,time,trainer);
    }

    public void changeTrainingTime(Client client, LocalDateTime time, LocalDateTime newTime){
        scheduleService.changeDate(client, time,newTime);
    }

    public void paySalaries(){ financeService.paySalaries(staffService);}

    public ArrayList<Horse> getIllHorses(){
        ArrayList<Horse> horses = stallService.getHorses();
        return careService.getIllHorses(horses);
    }

    public ArrayList<Horse> getHungryHorses(){
        ArrayList<Horse> horses = stallService.getHorses();
        return careService.getHungryHorses(horses);
    }

    public StaffService getStaffService() {
        return staffService;
    }

    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    public FinanceService getFinanceService() {
        return financeService;
    }

    public StallService getStallService() {
        return stallService;
    }

    public ArrayList<Client> getClients(){
        return clients;
    }

    @Override
    public String toString(){
        return String.format("\"%s\" equestrian center, est. %tD.",name,establishedDate);
    }
}
