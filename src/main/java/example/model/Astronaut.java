package example.model;

public class Astronaut {
    int id;
    String name;
    String spacecraft;
    AstronautStatus status;
    int experienceLevel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpaceCraft() {
        return spacecraft;
    }

    public void setSpaceCraft(String spaceCraft) {
        this.spacecraft = spaceCraft;
    }

    public AstronautStatus getStatus() {
        return status;
    }

    public void setStatus(AstronautStatus status) {
        this.status = status;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public Astronaut() {
    }

    public Astronaut(int id, String name, String spaceCraft, AstronautStatus status, int expreienceLevel) {
        this.id = id;
        this.name = name;
        this.spacecraft = spaceCraft;
        this.status = status;
        this.experienceLevel = expreienceLevel;
    }

    @Override
    public String toString() {
        return "Astronaut{" +
                "[" + id +
                "]" + name + " | " +
                "spaceCraft='" + spacecraft +
                " | " + status +
                "exp=" + experienceLevel;
    }
}
