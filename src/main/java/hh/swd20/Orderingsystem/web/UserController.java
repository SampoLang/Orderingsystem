package hh.swd20.Orderingsystem.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import hh.swd20.Orderingsystem.domain.Signupform;
import hh.swd20.Orderingsystem.domain.User;
import hh.swd20.Orderingsystem.domain.UserRepository;

@Controller
public class UserController {
	@Autowired 
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public String getUsers(HttpServletRequest request) {

		if (request.isUserInRole("ADMIN")) {
	        System.out.println("ADMIN");
	    }
		else {
			System.out.println(request.getUserPrincipal().toString());
		}
	    return "redirect:/itemlistadmin";
	}
	@RequestMapping(value="login")
	public String login() {
		return "login";
	}
	@RequestMapping(value = "/signup")
    public String addStudent(Model model){
    	model.addAttribute("signupform", new Signupform());
        return "signup";
    }
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") Signupform signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	if (userRepository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
		    		userRepository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }
}
