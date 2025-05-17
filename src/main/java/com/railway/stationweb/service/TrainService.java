package com.railway.stationweb.service;

import com.railway.stationweb.model.Train;
import com.railway.stationweb.repository.TrainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class TrainService {

    private final TrainRepository trainRepository;

    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    public void addOrUpdateTrain(Train train) {
        trainRepository.findByTrainNumber(train.getTrainNumber())
                .ifPresentOrElse(
                        existing -> {
                            existing.setDestination(train.getDestination());
                            existing.setDepartureTime(train.getDepartureTime());
                            trainRepository.save(existing);
                        },
                        () -> trainRepository.save(train)
                );
    }

    public boolean deleteTrain(String trainNumber) {
        return trainRepository.findByTrainNumber(trainNumber)
                .map(train -> {
                    trainRepository.delete(train);
                    return true;
                })
                .orElse(false);
    }

    public List<Train> getAllTrainsSortedByTime(String order) {
        List<Train> trains = trainRepository.findAll();

        trains.sort((t1, t2) -> {
            LocalTime time1 = LocalTime.parse(t1.getDepartureTime());
            LocalTime time2 = LocalTime.parse(t2.getDepartureTime());
            return order.equals("asc") ?
                    time1.compareTo(time2) :
                    time2.compareTo(time1);
        });

        return trains;
    }

    public List<Train> findByDestination(String destination) {
        return destination.isBlank() ?
                getAllTrains() :
                trainRepository.findByDestinationContainingIgnoreCase(destination);
    }

    public List<Train> findByTime(String time) {
        return time.isBlank() ?
                getAllTrains() :
                trainRepository.findByDepartureTime(time);
    }
}