package com.railway.stationweb.service;

import com.railway.stationweb.model.Train;
import com.railway.stationweb.repository.TrainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Train> getTrainsSortedAsc() {
        return trainRepository.findAllByOrderByDepartureTimeAsc();
    }

    public List<Train> getTrainsSortedDesc() {
        return trainRepository.findAllByOrderByDepartureTimeDesc();
    }

}