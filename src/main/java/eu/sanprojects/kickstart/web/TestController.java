package eu.sanprojects.kickstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping(value="/test/testException")
	public void testException() throws Exception{
		throw new Exception("Testing exception");
	}
	
}
