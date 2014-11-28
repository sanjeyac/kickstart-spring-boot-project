package eu.sanprojects.kickstart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import eu.sanprojects.kickstart.model.converters.StringToUser;
import eu.sanprojects.kickstart.model.converters.UserToString;

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
		registry.addViewController("/login").setViewName("login");
	}

	
	//security
	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class ApplicationSecurity extends
			WebSecurityConfigurerAdapter {
		@Autowired
		private SecurityProperties security;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.anyRequest()
					.permitAll();
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			auth.inMemoryAuthentication()
					.withUser("admin").password("admin").roles("ADMIN", "USER")
					.and()
					.withUser("user").password("user").roles("USER");
		}
	}
}