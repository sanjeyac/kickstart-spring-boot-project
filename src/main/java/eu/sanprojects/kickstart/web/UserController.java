package eu.sanprojects.kickstart.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.sanprojects.kickstart.model.User;
import eu.sanprojects.kickstart.repository.UserRepository;

@Controller
public class UserController {

	public static String FORM_VIEW = "user/user";
	public static String LIST_VIEW = "user/list";

	@Autowired
	UserRepository repository;
	
	@ModelAttribute
	public void referenceData(Model model){
		
	}

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String list(Model model) {
		Iterable<User> users = repository.findAll();
		model.addAttribute("list", users);
		return LIST_VIEW;
	}
	
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String get(@PathVariable Long id, Model model) {
		User user = repository.findById(id);
		model.addAttribute("user", user);
		return FORM_VIEW;
	}
	
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String prepareForm( Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return FORM_VIEW;
	}	

	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String post(@Valid User user, BindingResult bindingResult) {		
		if (bindingResult.hasErrors()) {
			return FORM_VIEW;
		}
		repository.save(user);
		return "redirect:/user/list";
	}		
	
}
