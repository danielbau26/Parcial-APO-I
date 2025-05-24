package model;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Controller {

    private ArrayList <Report> rts;

    public Controller(){
        this.rts = new ArrayList<>();
    }
    
    public void registerHardware(int serialNumber, String typeComponent, boolean failure, String description, String idTeam, LocalDateTime reportDate, String level){
        LevelSeriousness severityLevel = null;
        if (level.equals("ALTO")){
            severityLevel = LevelSeriousness.ALTO;
        }else if (level.equals("MEDIO")){
            severityLevel = LevelSeriousness.MEDIO;
        } else{
            severityLevel = LevelSeriousness.BAJO;
        }

        Hardware newReportHardware = new Hardware(serialNumber, typeComponent, failure, description, idTeam, reportDate, severityLevel);
        rts.add(newReportHardware);
    }

    public void registerSoftware(String operatingSystem, String softwareName, String version, String description, String idTeam, LocalDateTime reportDate, String level){
        LevelSeriousness severityLevel = null;
        if (level.equals( "ALTO")){
            severityLevel = LevelSeriousness.ALTO;
        }else if (level.equals("MEDIO")){
            severityLevel = LevelSeriousness.MEDIO;
        } else{
            severityLevel = LevelSeriousness.BAJO;
        }

        Software newReportSoftware = new Software(operatingSystem, softwareName, version, description, idTeam, reportDate, severityLevel);
        rts.add(newReportSoftware);
    }

    public String showReportsIdTeam(){
        String print = "";
        if (rts == null){
            return "No hay nada que reportar";
        }
        for (int i=0; i<rts.size(); i++){
            Report report = rts.get(i);
            if (report instanceof Hardware) {
                Hardware hardwareReport = (Hardware)report;
                String idTeam = hardwareReport.getIdTeam();
                LevelSeriousness level = hardwareReport.getSeverityLevel();
                String severityLevel = level.name();

                print += (i+1) + " (Hardware). " + "id del equipo: " + idTeam + ", nivel de severiedad: " + severityLevel + "\n";
            }else if (report instanceof Software){
                Software softwareReport = (Software)report;
                String idTeam = softwareReport.getIdTeam();
                LevelSeriousness level = softwareReport.getSeverityLevel();
                String severityLevel = level.name();

                print += (i+1) + " (Software). " + "id del equipo: " + idTeam + ", nivel de severiedad: " + severityLevel + "\n";
            }
        }

        return print;
    }

    public void verifyIdTeam (String idTeam) throws Exception{
        boolean flag = false;
        for (int i = 0; i<rts.size(); i++){
            Report report = rts.get(i);
            String idTeamReport = report.getIdTeam();
            if (idTeamReport.equals(idTeam)){
                flag = true;
                break;
            }
        }
        if (flag == false){
            throw new Exception();
        }
    }

    public String consultInfoIdTeam(String idTeam){
        String info = "";

        for (int i=0; i<rts.size(); i++){
            Report report = rts.get(i);
            String idTeamReport = report.getIdTeam();
            if (idTeamReport.equals(idTeam) && report instanceof Hardware){
                Hardware hardware = (Hardware) report;
                info += "Tipo de reporte: Hardware \n";
                info += "Id de equipo: " + idTeam + "\n";
                info += "Descripción: " + hardware.getDescription() + "\n";
                info += "Nivel de severiedad: " + hardware.getSeverityLevel().name() + "\n";
                info += "Fecha del reporte: " + hardware.getFormattedReportDate() + "\n";
                info += "Tipo de componente: " + hardware.getTypeComponent() + "\n";
                info += "Número de serializacion: " + hardware.getSerialNumber() + "\n";
                info += "Requiere cambio: " + hardware.isFailures() + "\n";
                info += "\n";
            }else if (idTeamReport.equals(idTeam) && report instanceof Software){
                Software software = (Software) report;
                info += "Tipo de reporte: Software \n";
                info += "Id de equipo: " + idTeam + "\n";
                info += "Descripción: " + software.getDescription() + "\n";
                info += "Nivel de severiedad: " + software.getSeverityLevel().name() + "\n";
                info += "Fecha del reporte: " + software.getFormattedReportDate() + "\n";
                info += "Sistema operativo: " + software.getOperatingSystem() + "\n";
                info += "Nombre del software: " + software.getSoftwareName() + "\n";
                info += "Versión: " + software.getVersion() + "\n";
                info += "\n";
            }
        }

        return info;
    }

    public String showReportsSeriousness(){
        String print = "";
        if (rts == null){
            return "No hay nada que reportar";
        }
        for (int i=0; i<rts.size(); i++){
            Report report = rts.get(i);
            if (report instanceof Hardware) {
                Hardware hardwareReport = (Hardware)report;
                LevelSeriousness level = hardwareReport.getSeverityLevel();
                String severityLevel = level.name();

                print += (i+1) + " (Hardware). " + "nivel de severiedad: " + severityLevel + "\n";
            }else if (report instanceof Software){
                Software softwareReport = (Software)report;
                LevelSeriousness level = softwareReport.getSeverityLevel();
                String severityLevel = level.name();

                print += (i+1) + " (Software). " + "nivel de severiedad: " + severityLevel + "\n";
            }
        }

        return print;
    }

    public void verifyLevelSeriousness (String seriousness) throws Exception{
        boolean flag = false;
        for (int i = 0; i<rts.size(); i++){
            Report report = rts.get(i);
            String severityLevelReport = report.getSeverityLevel().name();
            if (severityLevelReport.equals(seriousness)){
                flag = true;
                break;
            }
        }
        if (flag == false){
            throw new Exception();
        }
    }

    public String consultInfoSeverityLevel(String seriousness){
        String info = "";

        for (int i=0; i<rts.size(); i++){
            Report report = rts.get(i);
            String seriousnessReport = report.getSeverityLevel().name();
            if (seriousnessReport.equals(seriousness) && report instanceof Hardware){
                Hardware hardware = (Hardware) report;
                info += "Tipo de reporte: Hardware \n";
                info += "Id de equipo: " + hardware.getIdTeam() + "\n";
                info += "Descripción: " + hardware.getDescription() + "\n";
                info += "Nivel de severiedad: " + hardware.getSeverityLevel().name() + "\n";
                info += "Fecha del reporte: " + hardware.getFormattedReportDate() + "\n";
                info += "Tipo de componente: " + hardware.getTypeComponent() + "\n";
                info += "Número de serializacion: " + hardware.getSerialNumber() + "\n";
                info += "Requiere cambio: " + hardware.isFailures() + "\n";
                info += "\n";
            }else if (seriousnessReport.equals(seriousness) && report instanceof Software){
                Software software = (Software) report;
                info += "Tipo de reporte: Software \n";
                info += "Id de equipo: " + software.getIdTeam() + "\n";
                info += "Descripción: " + software.getDescription() + "\n";
                info += "Nivel de severiedad: " + software.getSeverityLevel().name() + "\n";
                info += "Fecha del reporte: " + software.getFormattedReportDate() + "\n";
                info += "Sistema operativo: " + software.getOperatingSystem() + "\n";
                info += "Nombre del software: " + software.getSoftwareName() + "\n";
                info += "Versión: " + software.getVersion() + "\n";
                info += "\n";
            }
        }

        return info;
    }

    public String findDates() {
        String print = "";
        if (rts == null) {
            return "No hay nada que reportar";
        }

        LocalDateTime mayor = rts.get(0).getReportDate();
        LocalDateTime menor = rts.get(0).getReportDate();

        for (int i = 1; i < rts.size(); i++) {
            Report report = rts.get(i);
            LocalDateTime dateReport = report.getReportDate();

            if (dateReport.isAfter(mayor)) {
                mayor = dateReport;
            }
            if (dateReport.isBefore(menor)) {
                menor = dateReport;
            }
        }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        print = menor.format(formatter) + " - " + mayor.format(formatter);

        return print;
    }

    public void verifyDate(LocalDateTime fecha, LocalDateTime startDate, LocalDateTime endDate) throws Exception{
        if (fecha.isBefore(startDate) || fecha.isAfter(endDate)){
            throw new Exception();
        }
    }

    public String consultReportsDate(LocalDateTime fecha){
        String info = "";

        for (int i=0; i<rts.size(); i++){
            Report report = rts.get(i);
            LocalDateTime reportDate = report.getReportDate();
            if (reportDate.isAfter(fecha) && report instanceof Hardware){
                Hardware hardware = (Hardware) report;
                info += "Tipo de reporte: Hardware \n";
                info += "Id de equipo: " + hardware.getIdTeam() + "\n";
                info += "Descripción: " + hardware.getDescription() + "\n";
                info += "Nivel de severiedad: " + hardware.getSeverityLevel().name() + "\n";
                info += "Fecha del reporte: " + hardware.getFormattedReportDate() + "\n";
                info += "Tipo de componente: " + hardware.getTypeComponent() + "\n";
                info += "Número de serializacion: " + hardware.getSerialNumber() + "\n";
                info += "Requiere cambio: " + hardware.isFailures() + "\n";
                info += "\n";
            }else if(reportDate.isAfter(fecha) && report instanceof Software){
                Software software = (Software) report;
                info += "Tipo de reporte: Software \n";
                info += "Id de equipo: " + software.getIdTeam() + "\n";
                info += "Descripción: " + software.getDescription() + "\n";
                info += "Nivel de severiedad: " + software.getSeverityLevel().name() + "\n";
                info += "Fecha del reporte: " + software.getFormattedReportDate() + "\n";
                info += "Sistema operativo: " + software.getOperatingSystem() + "\n";
                info += "Nombre del software: " + software.getSoftwareName() + "\n";
                info += "Versión: " + software.getVersion() + "\n";
                info += "\n";
            }
        }

        return info;
    }

    public void saveReports(){
        File file = new File("data\\databaseReports.dat");

        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))){
            for (int i=0; i<rts.size(); i++){
                Report r = rts.get(i);
                writer.writeObject(r);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no fue encontrado");
        } catch (IOException e){
            System.out.println("Ocurrió un error al intentar leer o escribir datos");
        } catch (Exception e){
                System.out.println("Eror inesperado");
        }
    }

    public void loadReports(){
        File file = new File("data\\databaseReports.dat");
        
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    try {
                        Report r = (Report) reader.readObject();
                        if (r == null){
                            System.out.println("No hay reportes");
                            break;
                        }
                        rts.add(r);
                    } catch (EOFException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println("Error: clase desconocida al leer un reporte.");
                    } catch (IOException e) {
                        System.out.println("Error al leer un reporte: " + e.getMessage());
                        break;
                    }
                }
                System.out.println("Reportes cargados correctamente.");
            } catch (FileNotFoundException e) {
                System.out.println("Archivo de reportes no encontrado.");
            } catch (IOException e) {
                System.out.println("Error al abrir el archivo de reportes");
            } catch (Exception e){
                System.out.println("Eror inesperado");
            }
    }

    public void report(){
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        File fileHardware = new File("reports\\Reporte_Hardware_" + formatDate + ".txt");
        File fileSoftware = new File("reports\\Reporte_Software_" + formatDate + ".txt");

        try (BufferedWriter writerHw = new BufferedWriter(new FileWriter(fileHardware)); BufferedWriter writerSw = new BufferedWriter(new FileWriter(fileSoftware))) {
            for (int i=0; i<rts.size(); i++){
                Report report = rts.get(i);
                if(report instanceof Hardware){
                    Hardware h = (Hardware) report;
                    writerHw.write(h.getIdTeam() + "-" + h.getDescription() + "-" + h.getSeverityLevel() + "-" + h.getReportDate() + 
                    "-" + h.getTypeComponent() + "-" + h.getSerialNumber() + "-" + h.isFailures());
                    writerHw.newLine();
                }else if(report instanceof Software){
                    Software s = (Software) report;
                    writerSw.write(s.getIdTeam() + "-" + s.getDescription() + "-" + s.getSeverityLevel() + "-" + s.getReportDate() + "-" + 
                    s.getOperatingSystem() + "," + s.getSoftwareName() + "," + s.getVersion());
                    writerSw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el reporte");
        } catch (DateTimeException e) {
            System.out.println("Error con el formato de fecha");
        } catch (Exception e) {
            System.out.println("Error inesperado");
        }
    }

}
