package fr.karspa.hikerthinker.controller;

import fr.karspa.hikerthinker.Entity.ApplicationUser;
import fr.karspa.hikerthinker.Entity.Hike;
import fr.karspa.hikerthinker.dto.HikeDTO;
import fr.karspa.hikerthinker.services.HikeService;
import fr.karspa.hikerthinker.services.TokenService;
import fr.karspa.hikerthinker.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/hikes")

public class HikeController {

    private HikeService hikeService;
    private TokenService tokenService;

    public HikeController(HikeService hikeService, TokenService tokenService) {
        this.hikeService = hikeService;
        this.tokenService = tokenService;
    }

    @GetMapping("/")
    public HikeDTO[] getAllHikes(@RequestHeader(name = "Authorization") String rawToken) {
        String requesterUsername = tokenService.getSubject(rawToken.substring(7));
        return hikeService.findAllHikesByUsername(requesterUsername);
    }


    @GetMapping("/hike")
    public HikeDTO findById(@RequestParam(name = "hikeId") String hikeId, @RequestHeader(name = "Authorization") String rawToken) {
        String requesterUsername = tokenService.getSubject(rawToken.substring(7));
        Long longHikeId = Long.parseLong(hikeId);
        return hikeService.findHikeByIdAndUser(requesterUsername, longHikeId);
    }

    @PostMapping("/create")
    public HikeDTO create(@RequestBody HikeDTO hikeDTO, @RequestHeader(name = "Authorization") String rawToken) {

        //Récupérer l'user faisant la requête
        String requesterUsername = tokenService.getSubject(rawToken.substring(7));

        return hikeService.createNewHike(hikeDTO, requesterUsername);
    }

}
