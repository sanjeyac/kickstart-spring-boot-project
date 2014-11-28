package eu.sanprojects.kickstart.model.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import eu.sanprojects.kickstart.model.User;
import eu.sanprojects.kickstart.repository.UserRepository;

@Component
public class StringToUser implements Converter<String, User> {

	@Autowired
	UserRepository repository;

	public User convert(String arg) {
		if (arg==null || arg.isEmpty())
			return null;
		Long id = Long.parseLong(arg);
		if (id==null)
			return null;
		return repository.findById(id);
		
	}


}