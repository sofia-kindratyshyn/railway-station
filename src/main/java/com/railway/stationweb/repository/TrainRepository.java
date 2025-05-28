package com.railway.stationweb.repository;

import com.railway.stationweb.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Long> {
    Optional<Train> findByTrainNumber(String trainNumber);
    List<Train> findByDestinationContainingIgnoreCase(String destination);
    List<Train> findByDepartureTime(String departureTime);
    List<Train> findAllByOrderByDepartureTimeAsc();    // для "від раннього"
    List<Train> findAllByOrderByDepartureTimeDesc();   // для "від пізнього"

}
