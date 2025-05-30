import model.event.Enums.EventCategory;
import model.*;
import model.event.*;

import java.io.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List <User> userList = new ArrayList<>();
        List <Event> eventList = new ArrayList<>();
        boolean userParticipation = false;

        //leitura dos USUARIOS salvos no users.csv
        System.out.println("Digite o caminho do db 'user': "); //D:/Programaçao/logica e algoritmo/IntelliJ/CadastroEventos/save/users.csv
        String userPath = sc.nextLine(); //le o local informado como string
        File file = new File(userPath);

        String folderPath = file.getParent(); //encontra a pasta do arquivo a partir do local informado
        String targetFileStr = folderPath + "\\users.data"; //em tese deveria sobrepor o arquivo antigo com o novo
        while(true) {


            try (BufferedReader br = new BufferedReader(new FileReader(userPath))) {
                String userCsv = br.readLine();
                while (userCsv != null) {
                    String[] fields = userCsv.split(",");
                    String userName = fields[0];
                    String userEmail = fields[1];
                    String userPassword = fields[2];

                    User user = new User(userName, userEmail, userPassword);
                    userList.add(user);
                    userCsv = br.readLine();
                }
                break;
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
                userPath = sc.nextLine();
            }
        }

        System.out.println("Digite o caminho do db 'events': "); //D:/Programaçao/logica e algoritmo/IntelliJ/CadastroEventos/save/events.csv
        String eventsPath = sc.nextLine();//le o local informado como string
        File eventsFile = new File(eventsPath);//em tese aqui deveria transformar a string em um arquivo que o java entende

        String eventsFolderPath = eventsFile.getParent(); //encontra a pasta do arquivo a partir do local informado
        String eventsTargetFileStr = eventsFolderPath + "\\events.data"; //em tese deveria sobrepor o arquivo antigo com o novo


        //leitura dos EVENTOS salvos no events.csv
        while (true) {
            try (BufferedReader br = new BufferedReader(new FileReader(eventsPath))) {
                String itemCsv = br.readLine();
                while (itemCsv != null) {
                    String[] fields = itemCsv.split(",");
                    String eventName = fields[0];
                    String eventAddress = fields[1];
                    LocalDate startDate = LocalDate.parse(fields[2], fmt);
                    LocalDate endDate = LocalDate.parse(fields[3], fmt);
                    String eventDescription = fields[3];
                    EventCategory category = EventCategory.valueOf(fields[4]);

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
                    for(Event ev : eventList){
                        System.out.println(ev);
                    }

                    itemCsv = br.readLine();
                }
                break;
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
                eventsPath = sc.nextLine();
            }
        }




        //leitura dos 3 eventos mais proximos, se estiver occorrendo agora deve avisar que esta oocorrendo e listar os 3 seguintes, senao, apenas listar os tres seguintes
        LocalDate now = LocalDate.now();
        System.out.print("Data de hoje: " + now);

        int i = 1;
        boolean eventOcurring = false;

        for(Event ev : eventList) {
            if(!now.isBefore(ev.getStartDate()) && !now.isAfter(ev.getEndDate())){ //se agora for nao for antes de startDate e nem depois de EndDate
                System.out.println("Evento ocorrendo no momento: " + ev);
                eventOcurring = true;

            } if(!eventOcurring){
                System.out.println("Nenhum evendo ocorrendo no momento!!");
                System.out.print("Eventos mais proximos: ");

                if (ev.getEndDate().isAfter(now)) {
                    System.out.println(i + ev.toString());
                    i++;
                }
                if(i > 3){
                    break;
                }
            }
        }


        //
        System.out.println("Digite um comando: ");
        int esc =  1;
        while (esc != 0) {
            System.out.print("\n 1 - Cadastrar um novo usuario \n 2 - Cadastrar um novo evento \n 3 - buscar todos eventos cadastrados\n 4 - Buscar um evento pelo id" +
                    "\n 5 - sair do programa");

            int r = sc.nextInt();
            sc.nextLine();
            if (r == 3) {esc = 0;}
            switch (r) {
                case 1:
                    //cadastrar um novo usuário
                    System.out.println("Cadastre um novo usuario: ");
                    System.out.print("Digite o nome: ");
                    String name = sc.nextLine();
                    System.out.print("Digite o email: ");
                    String eMail = sc.nextLine();
                    System.out.print("Digite a senha: ");
                    String password = sc.nextLine();

                    User user = new User(name, eMail, password);
                    userList.add(user);
                    break;

                case 2:
                    //cadastrar um novo evento
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

                        if(res == 'S') {
                            userParticipation = true;

                        } if (res == 'N'){
                            userParticipation = false;
                        }

                        System.out.print("Digite a categoria do evento: ");
                        String input = sc.nextLine().toUpperCase();
                        EventCategory eventCategory = EventCategory.valueOf(input);
                        switch (eventCategory) {
                            case FESTAS:
                                PartyEvent partyEvent = new PartyEvent(eventName, eventAddress, startDate, endDate, eventDescription,  eventCategory, userParticipation);
                                eventList.add(partyEvent);
                                break;
                            case EVENTOS_ESPORTIVOS:
                                SportsEvent sportsEvent = new SportsEvent(eventName, eventAddress, startDate,endDate, eventDescription, eventCategory, userParticipation);
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
                    }catch  (DateTimeParseException e){
                        System.out.println("Erro: Formato de data nao aceito");
                    }
                    break;

                case 3:
                    //Listar Eventos
                    System.out.println("Eventos passados: ");
                    for(Event ev : eventList){
                        if (ev.getEndDate().isBefore(now)){
                            System.out.println(ev);
                        }
                    }
                    System.out.println("Eventos futuros: ");
                    for(Event ev : eventList){
                        if (ev.getEndDate().isAfter(now)){
                            System.out.println(ev);
                        }
                    }
                    break;

                case 4:
                    System.out.print("Qual o id do evento? ");
                    int id = sc.nextInt();

                    for(Event e : eventList){
                        if(e.getId()==id){
                            System.out.println(e);
                            System.out.println();
                            System.out.println("Deseja participar? (S/N)");
                            char res = sc.next().charAt(0);

                            if(res == 'S') {
                                userParticipation = true;
                            } if (res == 'N'){
                                 userParticipation = false;
                            }
                            e.setUserParticipation(userParticipation);
                        }
                    }

                    break;

                case 5:
                    //inicio da escrita do save
                    try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
                        for(User x : userList){
                            bw.write(x.toString());
                            bw.newLine();
                        }
                    } catch (IOException e){
                        System.out.println("Error writing file: " + e.getMessage());
                    }

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(eventsTargetFileStr))){
                        for(Event event : eventList){
                            bw.write(event.toString());
                            bw.newLine();
                        }
                    } catch (IOException e){
                        System.out.println("Error writing file: " + e.getMessage());
                    }
                    break;

                    //fim da escrita do save
                default:
                    System.out.println("ERRO! Digite novamente.");
                    break;
            }
        }
    }
}