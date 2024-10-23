package model;

import java.sql.Date;

public class Room {
    private int roomId;
    private String renterName;
    private String renterContact;
    private Date moveInDate;
    private int payCycle;
    private String note;

    public Room(int roomId, String renterName, String renterContact, Date moveInDate, int payCycle, String note) {
        this.roomId = roomId;
        this.renterName = renterName;
        this.renterContact = renterContact;
        this.moveInDate = moveInDate;
        this.payCycle = payCycle;
        this.note = note;
    }

    public Room(String renterName, String renterContact, Date moveInDate, int payCycle, String note) {
        this.renterName = renterName;
        this.renterContact = renterContact;
        this.moveInDate = moveInDate;
        this.payCycle = payCycle;
        this.note = note;
    }

    public int getRoomId() { return roomId; }
    public String getRenterName() { return renterName; }
    public String getRenterContact() { return renterContact; }
    public Date getMoveInDate() { return moveInDate; }
    public int getPayCycle() { return payCycle; }
    public String getNote() { return note; }

    public void setRoomId(int roomId) { this.roomId = roomId; }
    public void setRenterName(String renterName) { this.renterName = renterName; }
    public void setRenterContact(String renterContact) { this.renterContact = renterContact; }
    public void setMoveInDate(Date moveInDate) { this.moveInDate = moveInDate; }
    public void setPayCycle(int payCycle) { this.payCycle = payCycle; }
    public void setNote(String note) { this.note = note; }
}

