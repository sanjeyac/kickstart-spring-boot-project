package eu.sanprojects.kickstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		return "redirect:/user/list";
	}
	
}
