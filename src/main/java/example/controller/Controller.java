package example.controller;
import example.model.*;
import example.repo.*;
import example.service.*;


import java.nio.file.Path;

import java.util.*;

public class Controller {
    private final JsonRepo<Astronaut> astronautRepo;
    private final JsonRepo<MissionEvent> missionEventRepo;
    private final JsonRepo<Supply> supplyRepo;
    private final MixService mixService;

    public Controller(JsonRepo<Astronaut> astronautRepo, JsonRepo<MissionEvent> missionEventRepo, JsonRepo<Supply> supplyRepo, MixService mixService) {
        this.astronautRepo = astronautRepo;
        this.missionEventRepo = missionEventRepo;
        this.supplyRepo = supplyRepo;
        this.mixService = mixService;
    }

    public void run() throws Exception {
        var astronauts = astronautRepo.readAll();
        var missionEvents = missionEventRepo.readAll();
        var supplies = supplyRepo.readAll();

        System.out.println("Astronauts loaded: " + astronauts.size());
        System.out.println("Mission Events loaded: " + missionEvents.size());
        System.out.println("Supplies loaded: " + supplies.size());
        astronauts.forEach(System.out::println);

//        System.out.println("Enter spacecraft name:");
//        String spacecraftName = new Scanner(System.in).nextLine();
//        var filteredAstronauts = mixService.filterBySpacecraftAndStatus(astronauts, spacecraftName);
//        System.out.println("Filtered Astronauts:");
//        filteredAstronauts.forEach(System.out::println);

        System.out.println("Sorted Astronauts:");
        var sorted = mixService.sortByExperience(astronauts);
        sorted.forEach(System.out::println);


    }






}
