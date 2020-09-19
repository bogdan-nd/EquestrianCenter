import Entities.*;
import Enums.HorsemanStatus;
import Enums.SportsCategory;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class App{
    public static void main(String[] args){
        System.out.print("Задача состоит в максимальной автоматизации конного клуба, для менеджеров," +
                " а так же любителей верховой езды, которые стремятся открыть свой клуб.\nПредметная область состоит" +
                "из 5 сервисов:\n1)Cервис - Конюшня\n2)Сервис по уходу за лошадьми\n3)Сервис учета рабочих" +
                "\n4)Сервис расписания тренировок и занятий\n5)Финансовый сервис" +
                "\n\nНу что? Начнем?))\nСоздадим конный клуб MustangClub");

        String centerName = "MustangClub";
        Manager manager = new Manager("Alex",10000);
        EquestrianCenter center = new EquestrianCenter(centerName, manager);

        System.out.print("\nКлуб небольшой, начнем с 4 лошадей\n");

        Horse horseAlex = new Horse("Alex",centerName, HorsemanStatus.PROFESSIONAL, 120,220000);
        Horse horseZirochka = new Horse("Zirochka", centerName,HorsemanStatus.KID, 70,85000);
        Horse horseJames = new Horse("James", centerName,HorsemanStatus.ADULT, 110, 120000);
        Horse horseMiranda = new Horse("Miranda", centerName, HorsemanStatus.ADULT,100, 170000);

        center.addHorse(horseAlex);
        center.addHorse(horseZirochka);
        center.addHorse(horseJames);
        center.addHorse(horseMiranda);

        System.out.print(center.getStallService());

        System.out.print("\n\nТеперь наймем берейторов, ветеринара и конюха");

        Trainer trainerMaria = new Trainer("Maria", SportsCategory.MS, 25000, 1400);
        Trainer trainerVlad = new Trainer("Vlad", SportsCategory.KMS, 20000, 1000);
        Trainer trainerStas = new Trainer("Stas", SportsCategory.MSMK,40000, 2750);

        Vet vetAndrew = new Vet("Andrew", 17800, 500);
        Groom groomIlya = new Groom("Ilya", 12400, 250);

        center.hireStaff(trainerMaria);
        center.hireStaff(trainerVlad);
        center.hireStaff(trainerStas);
        center.hireStaff(vetAndrew);
        center.hireStaff(groomIlya);

        System.out.print("\nКоманда готова:\n" + center.getStaffService() + "\n\nТеперь самое время завести новых клиентов:\n");

        Client clientAlice = new Client("Alice", 40, HorsemanStatus.KID, SportsCategory.BEGINNER);
        Client clientPablo = new Client("Pablo", 90, HorsemanStatus.PROFESSIONAL, SportsCategory.KMS);
        Client clientNina = new Client("Nina",75,HorsemanStatus.ADULT, SportsCategory.grade2);

        Horse clientPabloHorse = new Horse("Black", "Pablo", HorsemanStatus.PROFESSIONAL, 95, 117900);
        Horse clientAliceHorse = new Horse("Snow", "Alice", HorsemanStatus.KID, 90, 80900);

        center.addClient(clientNina);
        center.addClientWithHorse(clientPabloHorse,clientPablo);
        center.addClientWithHorse(clientAliceHorse, clientAlice);

        System.out.print("\nКлиентская база нашего клуба:\n");
        for (Client client: center.getClients()){
            System.out.print(client.toString());
        }
        System.out.print("\n\nСоздадим пару тренировок:\n");

        LocalDateTime pabloTrainingTime = LocalDateTime.now().plus(3, ChronoUnit.DAYS);
        LocalDateTime aliceTrainingTime = LocalDateTime.now().plus(30, ChronoUnit.HOURS);
        Training training = new Training(trainerStas, clientPabloHorse, clientPablo, pabloTrainingTime, 100);
        Training training2 = new Training(trainerVlad, horseZirochka, clientAlice, aliceTrainingTime,100);

        center.addTraining(training);
        center.addTraining(training2);
        System.out.print(center.getScheduleService());
        System.out.print("\n\nКонь James споткнулся и травмировал копыто, а конь клиента Pablo - Black заболел из-за " +
                "интенсивной подготовки к соревнованиям");
        clientPabloHorse.fallIll();
        horseJames.fallIll();

        System.out.print("\nСписок больных лошадей, они требуют лечения и ухода:\n");
        for(Horse horse: center.getIllHorses()){
            System.out.print(horse.toString());
        }

        for(Horse horse: center.getIllHorses()){
            center.recoverHorse(horse, vetAndrew);
            center.careHorse(horse, groomIlya);
        }
        System.out.print("\n\nПосле долгих стараний ветеринаров и конюхов. Лошади пришли в норму!\n");
        System.out.print("\nАлисе не понравился тренер Влад, он сильно строгий, поэтому она теперь занимается с Марией." +
                "\nэто не первая жалоба на Влада, " +
                "поэтому менеджер решил его уволить!\n");

        center.changeTrainingCoach(clientAlice, aliceTrainingTime, trainerMaria);
        center.fireStaff(trainerVlad);
        System.out.print("Пабло выиграл чемпионат, поэтому вечерние тренировки перенесли на утро.");
        LocalDateTime newPabloTrainingTime = pabloTrainingTime.plus(10, ChronoUnit.HOURS);
        center.changeTrainingTime(clientPablo, pabloTrainingTime, newPabloTrainingTime);

        System.out.print("\nНовое расписание:\n");
        System.out.print(center.getScheduleService());
        ArrayList<Horse> hungryHorses = center.getHungryHorses();
        System.out.print("\n\nКто-то кормил лошадей? Они ведь умрут.");
        if(hungryHorses.size() == 0)
            System.out.print("\nВаау! Все лошади накормлены. Конюх точно получит повышение в следующем месяце!");
        else{
            System.out.print("\nНа нас подаст в суд GreenPeace, ой-ой");
        }

        Horse horseOleg = new Horse("Oleg", "Hospital", HorsemanStatus.PROFESSIONAL, 120, 70000);
        System.out.print("\n\nКлубу потребовался ещё одна лошадь для профессиональных всадников." +
                "Клуб решил купить коня Олега и продать лошадь Миранду ( по причине старости)" +
                "\nОбновленный состав:\n");
        center.buyHorse(horseOleg);
        center.soldHorse(horseMiranda);

        System.out.print(center.getStallService());
        System.out.print("\n\nPablo и Alice закончили тренировки на сегодня.");
        center.finishTraining(clientPablo,newPabloTrainingTime);
        System.out.print("\nМы плотно поработали, пришло время раздавать зарпалату!");
        center.paySalaries();
        System.out.print("\n\nЗа этот увлекательный период " + centerName + " " + center.getFinanceService());
        System.out.print("\nВладелец пошел думать куда проинвестировать деньги. А Вам - удачи :)");
    }

}
