package fr.karspa.hikerthinker.controller;

import fr.karspa.hikerthinker.Entity.Hike;
import fr.karspa.hikerthinker.services.HikeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hike")
public class HikeController {

    private HikeService hikeService;

    public HikeController(HikeService hikeService) {
        this.hikeService = hikeService;
    }

    @GetMapping("/")
    public Hike oui(@RequestParam(name = "hikeId") String hikeId) {
        Long longHikeId = Long.parseLong(hikeId);
        return hikeService.findHikeById(longHikeId);
    }

}
