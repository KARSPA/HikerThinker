package fr.karspa.hikerthinker.controller;

import fr.karspa.hikerthinker.Entity.Hike;
import fr.karspa.hikerthinker.services.HikeService;
import fr.karspa.hikerthinker.services.TokenService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
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
    public Hike[] getAllHikes(@RequestHeader(name = "Authorization") String rawToken) {
        String requesterUsername = tokenService.getSubject(rawToken.substring(7));
        return hikeService.findAllHikesByUsername(requesterUsername);
    }


    @GetMapping("/hike")
    public Hike findById(@RequestParam(name = "hikeId") String hikeId) {
        Long longHikeId = Long.parseLong(hikeId);
        return hikeService.findHikeById(longHikeId);
    }

}
