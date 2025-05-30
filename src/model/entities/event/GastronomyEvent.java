package model.entities.event;

import model.entities.event.Enums.EventCategory;

import java.time.LocalDate;

public class GastronomyEvent extends Event {
    private EventCategory eventCategory;

    public GastronomyEvent() {
    }

    public GastronomyEvent(String name, String address, LocalDate startDate, LocalDate endDate, String description, EventCategory eventCategory, boolean userParticipation) {
        super(name, address, startDate, endDate, description, userParticipation);
        this.eventCategory = eventCategory;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }
}