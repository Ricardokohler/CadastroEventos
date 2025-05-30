package model.event;

import model.event.Enums.EventCategory;

import java.time.LocalDate;

public class SportsEvent extends Event {
    private EventCategory eventCategory;

    public SportsEvent() {
    }

    public SportsEvent(String name, String address, LocalDate startDate, LocalDate endDate, String description, EventCategory eventCategory, boolean userParticipation) {
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