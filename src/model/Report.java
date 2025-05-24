package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idTeam;
    private String description;
    private LevelSeriousness severityLevel;
    private LocalDateTime reportDate;

    public Report(String description, String idTeam, LocalDateTime reportDate, LevelSeriousness severityLevel) {
        this.description = description;
        this.idTeam = idTeam;
        this.reportDate = reportDate;
        this.severityLevel = severityLevel;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LevelSeriousness getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(LevelSeriousness severityLevel) {
        this.severityLevel = severityLevel;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public String getFormattedReportDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return reportDate.format(format);
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

}
