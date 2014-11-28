package eu.sanprojects.kickstart.model.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import eu.sanprojects.kickstart.model.User;

@Component
public class UserToString implements Converter<User, String> {

	public String convert(User user) {
		return user.getId().toString();
	}

}
