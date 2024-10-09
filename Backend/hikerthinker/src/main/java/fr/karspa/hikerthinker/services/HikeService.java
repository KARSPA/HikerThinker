package fr.karspa.hikerthinker.services;

import fr.karspa.hikerthinker.Entity.ApplicationUser;
import fr.karspa.hikerthinker.Entity.Hike;
import fr.karspa.hikerthinker.repository.HikeRepository;
import fr.karspa.hikerthinker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class HikeService {

    private final HikeRepository hikeRepository;
    private final UserRepository userRepository;

    public HikeService(HikeRepository hikeRepository, UserRepository userRepository) {
        this.hikeRepository = hikeRepository;
        this.userRepository = userRepository;
    }

    public Hike findHikeById(Long hikeId){
        return hikeRepository.findById(hikeId).orElse(null);
    }


    public Hike[] findAllHikesByUsername(String username) {

        try{
            ApplicationUser user = userRepository.findByUsername(username).get();
            return hikeRepository.findAllByUser(user).get();
        }catch(Exception e){
            return new Hike[0];
        }
    }

}
