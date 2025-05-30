import model.controller.EventController;
import model.controller.UserController;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args){

        EventController eventController = new EventController();
        UserController userController = new UserController();

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o caminho do db 'user': "); //D:/Programaçao/logica e algoritmo/IntelliJ/CadastroEventos/save/users.csv
        String userPath = sc.nextLine();
        File file = new File(userPath);
        userController.uploadUsers(file);

        String folderPath = file.getParent();
        String targetFileStr = folderPath + "\\users.data";

        System.out.println("Digite o caminho do db 'events': "); //D:/Programaçao/logica e algoritmo/IntelliJ/CadastroEventos/save/events.csv
        String eventsPath = sc.nextLine();


        File eventsFile = new File(eventsPath);
        eventController.loadEvents(eventsFile);

        String eventsFolderPath = eventsFile.getParent();
        String eventsTargetFileStr = eventsFolderPath + "\\events.data";

        eventController.checkEvents();

        System.out.println("Digite um comando: ");
        int esc =  1;
        while (esc != 0) {
            System.out.print("\n1 - Cadastrar um novo usuario \n" +
                    "2 - Cadastrar um novo evento \n" +
                    "3 - buscar todos eventos cadastrados \n" +
                    "4 - Buscar um evento pelo id \n" +
                    "5 - sair do programa\n");
            int r = sc.nextInt();
            sc.nextLine();

            if (r == 5) {esc = 0;}

            switch (r) {
                case 1:
                    userController.newUser();
                    break;

                case 2:
                   eventController.newEvent();
                   break;

                case 3:
                    eventController.listEvents();
                    break;

                case 4:
                    System.out.print("Qual o id do evento? ");
                    int id = sc.nextInt();
                    eventController.getById(id);
                    break;

                case 5:
                    userController.saveUsers(targetFileStr);
                    eventController.saveEvents(eventsTargetFileStr);
                    break;

                default:
                    System.out.println("ERRO! Digite novamente.");
                    break;
            }
        }
    }
}