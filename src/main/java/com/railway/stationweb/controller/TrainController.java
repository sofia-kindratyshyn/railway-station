package com.railway.stationweb.controller;

import com.railway.stationweb.model.Train;
import com.railway.stationweb.service.TrainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/trains")
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping
    public String viewTrains(Model model,
                             @RequestParam(required = false) String message) {
        List<Train> trains = trainService.getAllTrains();
        model.addAttribute("trains", trains);
        model.addAttribute("train", new Train());
        if (message != null) {
            model.addAttribute("successMessage", message);
        }
        return "train";
    }

    @PostMapping("/add")
    public String addTrain(@ModelAttribute Train trains,
                           RedirectAttributes redirectAttributes) {
        trainService.addOrUpdateTrain(trains);
        redirectAttributes.addAttribute("message", "Поїзд успішно збережено");
        return "redirect:/trains";
    }

    @PostMapping("/delete")
    public String deleteTrain(@RequestParam String trainNumber,
                              RedirectAttributes redirectAttributes) {
        if (trainService.deleteTrain(trainNumber)) {
            redirectAttributes.addAttribute("message", "Поїзд успішно видалено");
        } else {
            redirectAttributes.addAttribute("message", "Помилка при видаленні");
        }
        return "redirect:/trains";
    }

    @GetMapping("/search")
    public String search(@RequestParam String destination, Model model) {
        List<Train> trains = trainService.findByDestination(destination);
        model.addAttribute("trains", trains);
        model.addAttribute("train", new Train());
        return "train";
    }

    @GetMapping("/search-time")
    public String searchByTime(@RequestParam String time, Model model) {
        List<Train> trains = trainService.findByTime(time);
        model.addAttribute("trains", trains);
        model.addAttribute("train", new Train());
        return "train";
    }

    @GetMapping("/sort-by-time")
    public String sortTrainsByTime(Model model,
                                   @RequestParam(defaultValue = "asc") String order) {
        List<Train> trains = trainService.getAllTrainsSortedByTime(order);
        model.addAttribute("trains", trains);
        model.addAttribute("train", new Train());
        return "train";
    }

    @GetMapping("/refresh")
    public String refreshTrains(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("message", "Список поїздів оновлено");
        return "redirect:/trains";
    }
}