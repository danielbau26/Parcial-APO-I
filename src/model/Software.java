package model;

import java.time.LocalDateTime;

public class Software extends Report {

    private String operatingSystem;
    private String softwareName;
    private String version;

    public Software(String operatingSystem, String softwareName, String version, String description, String idTeam, LocalDateTime reportDate, LevelSeriousness severityLevel) {
        super(description, idTeam, reportDate, severityLevel);
        this.operatingSystem = operatingSystem;
        this.softwareName = softwareName;
        this.version = version;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    


    
}
