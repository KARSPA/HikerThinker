package fr.karspa.hikerthinker.services;

import fr.karspa.hikerthinker.Entity.Hike;
import fr.karspa.hikerthinker.repository.HikeRepository;
import org.springframework.stereotype.Service;

@Service
public class HikeService {

    private final HikeRepository hikeRepository;

    public HikeService(HikeRepository hikeRepository) {
        this.hikeRepository = hikeRepository;
    }

    public Hike findHikeById(Long hikeId){
        return hikeRepository.findById(hikeId).orElse(null);
    }

}
