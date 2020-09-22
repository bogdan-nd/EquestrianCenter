package Services;

import Entities.Client;
import Entities.Horse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class StallService {
    private ArrayList<Horse> horses;
    private HashMap<Horse, Client> clientsHorses;

    public StallService(){
        horses = new ArrayList<>();
        clientsHorses = new HashMap<>();
    }

    public void addHorse(Horse horse){
        horses.add(horse);
    }

    public void addHorse(Horse horse, Client client){
        horses.add(horse);
        clientsHorses.put(horse, client);
    }

    public void removeHorse(Horse removedHorse){
        horses.removeIf(horse -> horse.equals(removedHorse));

        Set<Horse> horsesOfClients = clientsHorses.keySet();

        horsesOfClients.removeIf(horse -> horse.equals(removedHorse));
    }

    public boolean isClientHorse(Horse horse){
        Set<Horse> horsesOfClients = clientsHorses.keySet();

        for(Horse secondHorse: horsesOfClients){
            if (horse.equals(secondHorse))
                return true;
        }

        return false;
    }

    public Client getHorseOwner(Horse horse){
        Set<Horse> horsesOfClients = clientsHorses.keySet();

        for(Horse theHorse: horsesOfClients){
            if (theHorse.equals(horse))
                return clientsHorses.get(theHorse);
        }

        return null;
    }

    public ArrayList<Horse> getHorses() {
        return horses;
    }

    @Override
    public String toString(){
        String returnString = "";

        for(Horse horse:horses){
            returnString += horse.toString();
        }

        return returnString;
    }

}
