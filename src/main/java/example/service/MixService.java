package example.service;


import example.model.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MixService {
    public List<Astronaut> filterBySpacecraftAndStatus(List<Astronaut> astronauts, String spacecraft) {
        return astronauts.stream()
                .filter(v -> v.getSpaceCraft().equals(spacecraft) && v.getStatus().equals(AstronautStatus.ACTIVE))
                .toList();
    }

    public List<Astronaut> sortByExperience(List<Astronaut> astronauts) {
        return astronauts.stream()
                .sorted(Comparator.comparingInt(Astronaut::getExperienceLevel).reversed()
                        .thenComparing(Astronaut::getName))
                .toList();


    }
}
