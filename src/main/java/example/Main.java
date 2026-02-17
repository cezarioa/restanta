package example;
import example.controller.Controller;
import example.model.*;
import example.repo.JsonRepo;
import example.service.MixService;

public class Main {
    public static void main(String[] args) throws Exception {
        var astronautsRepo = new JsonRepo<Astronaut>("src/main/resources/astronauts.json", Astronaut[].class);
        var missionEventsRepo = new JsonRepo<MissionEvent>("src/main/resources/events.json", MissionEvent[].class);
        var supplyRepo = new JsonRepo<Supply>("src/main/resources/supplies.json", Supply[].class);
        var MixService = new MixService();
        var controller = new Controller(astronautsRepo, missionEventsRepo,  supplyRepo, MixService);

        controller.run();

    }
}
