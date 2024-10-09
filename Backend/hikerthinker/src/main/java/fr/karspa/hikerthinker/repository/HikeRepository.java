package fr.karspa.hikerthinker.repository;

import fr.karspa.hikerthinker.Entity.ApplicationUser;
import fr.karspa.hikerthinker.Entity.Hike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HikeRepository extends JpaRepository<Hike, Long> {

    Optional<Hike[]> findAllByUser(ApplicationUser user);
}
