package com.parking.parkinlot3.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    private Long id;
    private String licensePlate;
    private String parkingSpot;
    private User owner;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "license_plate", nullable = false, unique = true)
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Column(name = "parking_spot", nullable = false)
    public String getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(String parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id") // The foreign key column in the 'car' table
    public User getOwner() {
        return owner;
    }

    public void setUser(User owner) {
        this.owner = owner;
    }
}