package model.controller;

import model.entities.event.*;
import model.entities.event.Enums.EventCategory;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventController {

    Scanner sc = new Scanner(System.in);
    List<Event> eventList = new ArrayList<>();
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDate now = LocalDate.now();

    public void loadEvents(File file) {


        while (true) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String itemCsv = br.readLine();
                while (itemCsv != null) {
                    String[] fields = itemCsv.split(",");
                    String eventName = fields[0];
                    String eventAddress = fields[1];
                    LocalDate startDate = LocalDate.parse(fields[2], fmt);
                    LocalDate endDate = LocalDate.parse(fields[3], fmt);
                    String eventDescription = fields[3];

                    EventCategory category = EventCategory.valueOf(fields[4]);
                    boolean userParticipation = Boolean.parseBoolean(fields[5]);
                    //switch case de cadastro a partir do category
                    switch (category) {
                        case SHOWS:
                            eventList.add(new ShowEvent(eventName, eventAddress, startDate, endDate, eventDescription, category, userParticipation));
                            break;
                        case FESTAS:
                            eventList.add(new PartyEvent(eventName, eventAddress, startDate, endDate, eventDescription, category, userParticipation));
                            break;
                        case EVENTOS_ESPORTIVOS:
                            eventList.add(new SportsEvent(eventName, eventAddress, startDate, endDate, eventDescription, category, userParticipation));
                            break;
                        case GASTRONOMIA:
                            eventList.add(new GastronomyEvent(eventName, eventAddress, startDate, endDate, eventDescription, category, userParticipation));
                            break;
                        default:
                            System.out.println("Invalid category: " + category);
                    }
                    for (Event ev : eventList) {
                        System.out.println(ev);
                    }

                    itemCsv = br.readLine();
                }
                break;
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
    }


    //Metodo evento ocorrendo agora
    public void checkEvents() {

        System.out.println();
        System.out.println("Data de hoje: " + now);

        int i = 1;
        boolean eventOcurring = false;

        for (Event ev : eventList) {
            if (eventList.isEmpty()) {
                System.out.println("Nenhum evento cadastrado no sistema.");
            }

            if (!now.isBefore(ev.getStartDate()) && !now.isAfter(ev.getEndDate())) { //se agora for nao for antes de startDate e nem depois de EndDate
                System.out.println("Evento ocorrendo no momento: " + ev);
                eventOcurring = true;

            }
            if (!eventOcurring) {
                System.out.println("Nenhum evendo ocorrendo no momento!!");
                System.out.print("Eventos mais proximos: ");

                if (ev.getEndDate().isAfter(now)) {
                    System.out.println(i + ev.toString());
                    i++;
                }
                if (i > 3) {
                    break;
                }
            }
        }
    }

    //metodo novo evento
    public void newEvent() {
        boolean userParticipation = false;

        try {
            System.out.println("Cadastrar um novo evento: ");
            System.out.print("Digite o nome: ");
            String eventName = sc.nextLine();
            System.out.print("Digite o endereço: ");
            String eventAddress = sc.nextLine();
            System.out.print("Digite a data de inicio do evento: ");
            LocalDate startDate = LocalDate.parse(sc.nextLine(), fmt);
            System.out.print("Digite a data de encerramento do evento: ");
            LocalDate endDate = LocalDate.parse(sc.nextLine(), fmt);
            System.out.print("Digite a descrição do evento: ");
            String eventDescription = sc.nextLine();
            System.out.print("Você irá participar do evento? ");
            char res = sc.next().charAt(0);

            if (res == 'S') {
                userParticipation = true;

            }

            System.out.print("Digite a categoria do evento: ");
            String input = sc.nextLine().toUpperCase();
            EventCategory eventCategory = EventCategory.valueOf(input);
            switch (eventCategory) {
                case FESTAS:
                    PartyEvent partyEvent = new PartyEvent(eventName, eventAddress, startDate, endDate, eventDescription, eventCategory, userParticipation);
                    eventList.add(partyEvent);
                    break;
                case EVENTOS_ESPORTIVOS:
                    SportsEvent sportsEvent = new SportsEvent(eventName, eventAddress, startDate, endDate, eventDescription, eventCategory, userParticipation);
                    eventList.add(sportsEvent);
                    break;
                case SHOWS:
                    ShowEvent showEvent = new ShowEvent(eventName, eventAddress, startDate, endDate, eventDescription, eventCategory, userParticipation);
                    eventList.add(showEvent);
                    break;
                case GASTRONOMIA:
                    GastronomyEvent gastronomyEvent = new GastronomyEvent(eventName, eventAddress, startDate, endDate, eventDescription, eventCategory, userParticipation);
                    eventList.add(gastronomyEvent);
                    break;
                default:
                    System.out.println("Categoria inválida! ");

            }
        } catch (DateTimeParseException e) {
            System.out.println("Erro: Formato de data nao aceito");
        }
    }

    public void listEvents(){
        System.out.println("Eventos passados: ");
        for(Event ev : eventList){
            if (ev.getEndDate().isBefore(now)){
                System.out.println(ev);
            } if (eventList.isEmpty()){
                System.out.printl("A lista de eventos está vazia");
            }
        }
    }

    public void getById(int id) {
        boolean userParticipation = false;
        for (Event e : eventList) {
            if (e.getId() == id) {
                System.out.println(e);
                System.out.println();
                System.out.println("Deseja participar? (S/N)");
                char res = sc.next().charAt(0);

                if (res == 'S') {
                    userParticipation = true;

                    e.setUserParticipation(userParticipation);
                }
            }
        }
    }

    public void saveEvents(String eventsTargetFileStr){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(eventsTargetFileStr))){
            for(Event event : eventList){
                bw.write(event.toString());
                bw.newLine();
            }
        } catch (IOException e){
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

}
