package example.service;


import example.model.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.util.stream.Collectors.toList;

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
    public void writeInFile(Path file, List<Astronaut> sorted) throws IOException {
        List<String> lines = sorted.stream().map(Object::toString).toList();
        Files.write(file, lines);
    }

    public int calcPoints(MissionEvent e) {
        int p = e.getBasePoints();
        int d = e.getDay();
        return switch (e.getType()) {
            case EVA -> p + 2 * d;
            case SYSTEM_FAILURE -> p - 3 -d;
            case SCIENCE -> p + (d%4);
            case MEDICAL ->  p - (d%3);
            case COMMUNICATION -> p + 5;
        };
    }
    public List<String> eventsWithComputedPoints(List<MissionEvent> events) {
        return events.stream()
                .limit(5)
                .map(e -> String.format("Event %s -> rawPoints = %d -> computedPoints: %d", e.getId(),e.getBasePoints() ,calcPoints(e)))
                .toList();
    }
    public record RankEntry(Astronaut astronaut, int totalScore) { }
    public List<RankEntry> top5Ranking(List<Astronaut> astronauts, List<MissionEvent> events, List<Supply> supplies) {
        Map<Integer, Integer> scoreById = new HashMap<>();
        for (MissionEvent e : events) {
            scoreById.merge(e.getAstronautId(), calcPoints(e), Integer::sum);
        }
        for (Supply s : supplies) {
            scoreById.merge(s.getAstronautId(), s.getValue(), Integer::sum);
        }
        List<RankEntry> ranking = new ArrayList<>();
        for (Astronaut a : astronauts) {
            int total = scoreById.getOrDefault(a.getId(), 0);
            ranking.add(new RankEntry(a, total));
        }
        ranking.sort(Comparator.comparingInt(RankEntry::totalScore).reversed().thenComparing(re -> re.astronaut().getName()));
        return ranking.stream().limit(5).toList();
    }
    public record Counter(Object type, int count) { }
    public List<Counter> countBy(List<MissionEvent> entities){
        Map<Object, Integer> countByType = new HashMap<>();
        for (MissionEvent t: entities){
            countByType.merge(t.getType(), 1, Integer::sum);
        }

        return countByType.entrySet().stream().map(cbt -> new Counter(cbt.getKey(), cbt.getValue()))
                .sorted(Comparator.comparingInt(Counter::count).reversed()
                        .thenComparing(c -> c.type().toString())).toList();

    }
    public void writeInFile2(Path file, List<Counter> sorted) throws IOException {
        List<String> lines = sorted.stream().map(Object::toString).toList();
        Files.write(file, lines);
    }


}
