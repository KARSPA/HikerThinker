package fr.karspa.hikerthinker;

import fr.karspa.hikerthinker.Entity.ApplicationUser;
import fr.karspa.hikerthinker.Entity.Role;
import fr.karspa.hikerthinker.repository.RoleRepository;
import fr.karspa.hikerthinker.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HikerthinkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HikerthinkerApplication.class, args);
	}

	//En gros, a chaque lancement de l'application, on vérifie si il y a déja des roles dans la BDD, si il n'y en a pas,
	//c'est qu'elle n'est pas encore créée par Jpa, donc on ajoute des users à la main.
	//On return direct si on trouve "ADMIN" car ça indique que notre BDD est déja hydratée.
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {

			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;


			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser("admin", "admin@admin.fr",passwordEncoder.encode("admin123"), roles);

			userRepository.save(admin);
		};
	}
}
