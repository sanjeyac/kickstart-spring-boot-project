package eu.sanprojects.kickstart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import eu.sanprojects.kickstart.model.User;
import eu.sanprojects.kickstart.model.converters.StringToUser;
import eu.sanprojects.kickstart.model.converters.UserToString;
import eu.sanprojects.kickstart.repository.UserRepository;
import eu.sanprojects.kickstart.security.DatabaseUserDetailsService;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EntityScan
@EnableJpaRepositories
public class Application extends WebMvcConfigurerAdapter  {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//databinders
    @Override
    public void addFormatters(FormatterRegistry registry) {
    	registry.addConverter(new UserToString());
    	registry.addConverter(new StringToUser());
    }
	
	//controller supplementari
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
	}

	
	//security
	@Bean
	public ApplicationSecurity applicationSecurity() {
		return new ApplicationSecurity();
	}

	@Order(Ordered.HIGHEST_PRECEDENCE)
	@Configuration
	protected static class AuthenticationSecurity extends
			GlobalAuthenticationConfigurerAdapter {
		
		@Autowired
		private DatabaseUserDetailsService users;
		
		@Autowired
		private UserRepository userRepository;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			
			PasswordEncoder encoder = new BCryptPasswordEncoder();			
			auth.userDetailsService(users).passwordEncoder(encoder);
			
			if (userRepository.count() == 0){
				User user = new User();
				user.setUsername("user");
				user.setName("user");
				user.setPassword( encoder.encode("user") );
				user.setDescription("default user");
				userRepository.save(user);
			}
		}
	}

	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class ApplicationSecurity extends
			WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/login").permitAll()
					.anyRequest().permitAll();
					/*fullyAuthenticated().and().formLogin()
					.loginPage("/login").failureUrl("/login?error").and()
					.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.and().exceptionHandling()
					.accessDeniedPage("/access?error");*/
		}
	}	

}