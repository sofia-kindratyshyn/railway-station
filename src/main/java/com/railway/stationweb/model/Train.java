package com.railway.stationweb.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "trains")
public class Train implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "train_number", unique = true, nullable = false)
    private String trainNumber;

    private String destination;

    @Column(name = "departure_time")
    private String departureTime;

    public Train() {}

    public Train(String trainNumber, String destination, String departureTime) {
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.departureTime = departureTime;
    }

    public Long getId() { return id; }
    public String getTrainNumber() { return trainNumber; }
    public String getDestination() { return destination; }
    public String getDepartureTime() { return departureTime; }

    public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }
    public void setDestination(String destination) { this.destination = destination; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
}

