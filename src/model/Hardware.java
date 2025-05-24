package model;

import java.time.LocalDateTime;

public class Hardware extends Report {

    private boolean failure;
    private String typeComponent;
    private int serialNumber;
    
    public Hardware(int serialNumber, String typeComponent, boolean failure, String description, String idTeam, LocalDateTime reportDate, LevelSeriousness severityLevel) {
        super(description, idTeam, reportDate, severityLevel);
        this.failure = failure;
        this.serialNumber = serialNumber;
        this.typeComponent = typeComponent;
    }

    public boolean isFailures() {
        return failure;
    }

    public void setFailures(boolean failures) {
        this.failure = failures;
    }

    public String getTypeComponent() {
        return typeComponent;
    }

    public void setTypeComponent(String typeComponent) {
        this.typeComponent = typeComponent;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    
    
}
