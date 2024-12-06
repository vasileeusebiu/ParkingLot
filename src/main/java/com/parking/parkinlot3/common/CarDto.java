package com.parking.parkinlot3.common;

public class CarDto {
    Long id;

    String licensePlate;

    String parkingSpot;

    String ownerName;

    public CarDto(Long id, String licensePlate, String parkingSpot, String ownerName) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.parkingSpot = parkingSpot;
        this.ownerName = ownerName;
    }
    public long getId(){
        return id;
    }
    public String getLicensePlate(){
        return licensePlate;
    }
    public String getParkingSpot(){
        return parkingSpot;
    }
    public String getOwnerName(){
        return ownerName;
    }
}
