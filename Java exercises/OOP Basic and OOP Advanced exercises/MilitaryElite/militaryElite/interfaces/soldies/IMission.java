package militaryElite.interfaces.soldies;

public interface IMission {
    String getState();

    void completeMission();

    void setState(String state);
}
