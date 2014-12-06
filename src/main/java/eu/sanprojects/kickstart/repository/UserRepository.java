package eu.sanprojects.kickstart.repository;

import org.springframework.data.repository.CrudRepository;

import eu.sanprojects.kickstart.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findById(Long id);
	User findByUsername(String username);
}