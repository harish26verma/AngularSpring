package org.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class testController {
@RequestMapping(value="/home",method=RequestMethod.GET) 	
public String strHEllo(ModelMap model)
{
	model.addAttribute("message","welcome to spring MVC");
	return "welcome";
}

@RequestMapping(value="/home/{user}",method=RequestMethod.GET)
public String strHElloAgain(@PathVariable("user") String user,  ModelMap model)
{
	model.addAttribute("message","welcome again to spring MVC");
	model.addAttribute("userdata",user);
	
	return "welcome";
}
}
