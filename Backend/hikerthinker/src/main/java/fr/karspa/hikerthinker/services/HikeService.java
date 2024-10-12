package fr.karspa.hikerthinker.services;

import fr.karspa.hikerthinker.Entity.ApplicationUser;
import fr.karspa.hikerthinker.Entity.Hike;
import fr.karspa.hikerthinker.dto.HikeDTO;
import fr.karspa.hikerthinker.repository.HikeRepository;
import fr.karspa.hikerthinker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class HikeService {

    private final HikeRepository hikeRepository;
    private final UserRepository userRepository;

    public HikeService(HikeRepository hikeRepository, UserRepository userRepository) {
        this.hikeRepository = hikeRepository;
        this.userRepository = userRepository;
    }

    public HikeDTO findHikeByIdAndUser(String username, Long hikeId){

        try{
            ApplicationUser user = userRepository.findByUsername(username).get();
            return hikeRepository.findHikeByIdAndUser(hikeId, user).get().toDTO(true);
        }catch(Exception e){
            return new HikeDTO();
        }
    }


    public HikeDTO[] findAllHikesByUsername(String username) {

        try{
            ApplicationUser user = userRepository.findByUsername(username).get();
            Hike[] hikes = hikeRepository.findAllByUser(user).get();
//            for (Hike hike : hikes) {
//                System.out.println(hike);
//            }
            return Arrays.stream(hikes).filter(hike -> !hike.isModel()).map(hike->hike.toDTO(false)).toArray(HikeDTO[]::new);
        }catch(Exception e){
            return new HikeDTO[0];
        }
    }

}
