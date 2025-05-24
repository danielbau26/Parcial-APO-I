package ui;

import customExceptions.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import model.Controller;

public class Main{

    private Controller ctr;
    private Scanner sc;

    public Main(){
        this.ctr = new Controller();
        this.sc = new Scanner(System.in);
    }

    public static void main(String[] args){
        Main main = new Main();
        main.menu();
    }

    public void menu(){

        boolean flag = true;

        System.out.println("");
        System.out.println("Bienvenido al menu de reportes: ");

        ctr.loadReports();

        while (flag) {

            System.out.println("Opciones:");
            System.out.println("1. Registrar reportes");
            System.out.println("2. Consultar información de reportes");
            System.out.println("3. Salir");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1:
                    registerReport();
                    break;
                case 2:
                    consultReportInformation();
                    break;
                case 3:
                    flag = false;
                    ctr.saveReports();
                    System.out.println("Saliendo del programa...");
                    ctr.report();
                    sc.close();
                    break;
                default:
                    System.out.println("Por favor ingrese una opcion valida");
                    break;
            }
        }

    }


    public void registerReport(){

        System.out.println("Que reporte desea registrar?");
        System.out.println("1. Hardware");
        System.out.println("2. Software");
        int option = 0;

        while (true){
            option = sc.nextInt();
            if (option>=1 && option <=2){
                break;
            }
        }
        sc.nextLine();

        System.out.println("Digite el id de equipo");
        String idTeam = sc.nextLine();
        System.out.println("Escriba la descripcion del reporte");
        String description = sc.nextLine();
        boolean flag = true;
        String level = "";
        while (flag){
            try {
                System.out.println("Defina el nivel de severiedad");
                System.out.println("1. Alto");
                System.out.println("2. Medio");
                System.out.println("3. Bajo");
                int seriousness = sc.nextInt();
                switch (seriousness) {
                    case 1:
                        level = "ALTO";
                        flag = false;
                        break;
                    case 2:
                        level = "MEDIO";
                        flag = false;
                        break;
                    case 3:
                        level = "BAJO";
                        flag = false;
                        break;
                    default:
                        throw new ErrorLevelSeriousnessException();
                }
                
            } catch (ErrorLevelSeriousnessException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.nextLine();

        LocalDateTime fecha = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        while (fecha == null) {
            System.out.println("Ingresa la fecha (YYYY-MM-DD HH:MM): ");
            String entrada = sc.nextLine();
            try {
                fecha = LocalDateTime.parse(entrada, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato incorrecto. Intente otra vez.");
            }
        }

        if (option == 1){
             System.out.println("Ingrese el tipo de componente:");
            String typeComponent = sc.nextLine();

            System.out.println("Ingrese el número serial del componente:");
            int serialNumber = 0;
            while (serialNumber == 0){
                try {
                    serialNumber = sc.nextInt();
                    if (serialNumber <= 0){
                        throw new NumberIntegerPositiveException();
                    }
                } catch (NumberIntegerPositiveException e) {
                    System.out.println(e.getMessage());
                    serialNumber = 0;
                }
            }
            System.out.println("¿Se requiere cambio de componente? (si/no):");
            String failureOption = "";
            boolean failure = false;
            while (true){
                failureOption = sc.nextLine().toUpperCase();
                if (failureOption.equals("SI")){
                    failure = false;
                    break;
                }else if (failureOption.equals("NO")){
                    failure = true;
                    break;
                }
            }

            ctr.registerHardware(serialNumber, typeComponent, failure, description, idTeam, fecha, level);
            System.out.println("Reporte de hardware registrado correctamente.");

        }else{
            System.out.println("Ingrese el sistema operativo:");
            String os = sc.nextLine();

            System.out.println("Ingrese el nombre del software:");
            String softwareName = sc.nextLine();

            System.out.println("Ingrese la versión del software (Fomato: A.B.C): ");
            String version = "";
            while (version.equals("")){
                try {
                    version = sc.nextLine();
                    if (version.matches("\\d+\\.\\d+\\.\\d+")) {
                    } else {
                        throw new IncorrectVersionException();
                    }
                } catch (IncorrectVersionException e) {
                    System.out.println(e.getMessage());
                    version = "";
                } catch (Exception e){
                    System.out.println("Eror inesperado");
                    version = "";
                }
            }

            ctr.registerSoftware(os, softwareName, version, description, idTeam, fecha, level);
            System.out.println("Reporte de software registrado correctamente.");
        }

    }

    public void consultReportInformation(){

        System.out.println("Consulta de reportes");
        System.out.println("1. Consultar información por id de equipo");
        System.out.println("2. Consultar por niveles de severiedad");
        System.out.println("3. Consultar reportes desde una fecha determinada");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                consultsIdTeam();
                break;
            case 2:
                consultSerriousness();
                break;
            case 3:
                consultDate();
                break;
            default:
                System.out.println("Opción incorrecta");
                break;
        }
    }

    public void consultsIdTeam(){
        System.out.println("");
        System.out.println(ctr.showReportsIdTeam());
        System.out.println("");
        while(true){
            try {
                System.out.println("Digite el id de equipo para mostrar la información de ese reporte");
                String idTeam = sc.nextLine();
                System.out.println("");
                ctr.verifyIdTeam(idTeam);
                System.out.println(ctr.consultInfoIdTeam(idTeam));
                break;
            } catch (Exception e) {
                System.out.println("El id de team no existe entre los reportes. Intente de nuevo");
            }
        }
    }

    public void consultSerriousness(){
        System.out.println("");
        System.out.println(ctr.showReportsSeriousness());
        System.out.println("");
        boolean flag = true;

        while (true) { 
            try {
                System.out.println("Digite el nivel de severidad para mostrar la información de esos reportes (ALTO/MEDIO/BAJO)");
                String seriousness = "";
                while (flag){
                    seriousness = sc.nextLine().toUpperCase();
                    switch (seriousness) {
                        case "ALTO":
                            flag = false;
                            break;
                        case "MEDIO":
                            flag = false;
                            break;
                        case "BAJO":
                            flag = false;
                            break;
                        default:
                            System.out.println("Escriba correctamente el nivel de severiedad (ALTO/MEDIO/BAJO)");
                            break;
                    }
                }
                ctr.verifyLevelSeriousness(seriousness);
                System.out.println(ctr.consultInfoSeverityLevel(seriousness));
                break;
            } catch (Exception e) {
                System.out.println("El nivel de severiedad no existe entre los reportes. Intente de nuevo");
                flag = true;
            }
        }
    }

    public void consultDate(){
        System.out.println("");
        String dates = ctr.findDates();
        System.out.println("El rango de fecha de los reportes es de: " + dates);
        System.out.println("Ingresa una fecha y se mostraran los reportes desde esa fecha.");
        String [] startEndDate = dates.split(" - ");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(startEndDate[0], format);
        LocalDateTime endDate = LocalDateTime.parse(startEndDate[1], format);
        LocalDateTime fecha = null;

        while (fecha == null) {
            System.out.println("Ingresa la fecha (YYYY-MM-DD HH:MM): ");
            String entrada = sc.nextLine();
            try {
                fecha = LocalDateTime.parse(entrada, format);
                System.out.println("Fecha ingresada: " + fecha);
                ctr.verifyDate(fecha, startDate, endDate);
                System.out.println("");
                System.out.println(ctr.consultReportsDate(fecha));
            } catch (DateTimeParseException e) {
                System.out.println("Formato incorrecto. Intente otra vez.");
                fecha = null;
            } catch (Exception e){
                System.out.println(e.getMessage());
                fecha = null;
            }
        }
    }
}