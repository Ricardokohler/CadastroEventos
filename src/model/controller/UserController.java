package model.controller;

import model.entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserController {
   Scanner sc = new Scanner(System.in);
    public List<User> userList = new ArrayList<>();


   public void uploadUsers(File file) {
       while (true) {
           try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
           }
       }
   }

   //new user
    public void newUser(){
        System.out.println("Cadastre um novo usuario: ");
        System.out.print("Digite o nome: ");
        String name = sc.nextLine();
        System.out.print("Digite o email: ");
        String eMail = sc.nextLine();
        System.out.print("Digite a senha: ");
        String password = sc.nextLine();

        User user = new User(name, eMail, password);
        userList.add(user);
    }

    public void saveUsers(String targetFileStr){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
            for(User x : userList){
                bw.write(x.toString());
                bw.newLine();
            }
        } catch (IOException e){
            System.out.println("Error writing file: " + e.getMessage());
        }

    }
}
