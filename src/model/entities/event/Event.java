package model.entities.event;

import java.time.LocalDate;

public abstract class Event {

    private static int nextId = 1; //controlador estático

    private int id;
    private String name;
    private String address;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private boolean userParticipation;

    public Event(){
        this.id = nextId++;
    }

    public Event(String name, String address, LocalDate startDate,LocalDate endDate, String description, boolean userParticipation) {
        this.id = nextId++;
        this.name = name;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.userParticipation = userParticipation;
    }

    public boolean getUserParticipation(){ return userParticipation; }

    public void setUserParticipation( boolean userParticipation){ this.userParticipation = userParticipation; }

    public int getId(){ return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getEventDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartDate(){return startDate;}

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = startDate;
    }





    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
