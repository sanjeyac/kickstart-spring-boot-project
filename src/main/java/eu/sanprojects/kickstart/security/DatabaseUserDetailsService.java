package eu.sanprojects.kickstart.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import eu.sanprojects.kickstart.model.User;
import eu.sanprojects.kickstart.repository.UserRepository;

@Order(Ordered.HIGHEST_PRECEDENCE + 10)
@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        System.out.println("richiesto -> "+username);
        if (user==null)
        	System.out.println("utente non trovato!");
        return new DatabaseUserDetails(user);
    }

}

