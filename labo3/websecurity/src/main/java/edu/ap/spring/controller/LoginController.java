package edu.ap.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ap.spring.service.WebUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private final WebUserService userService;

    public LoginController(WebUserService userService) {
        this.userService = userService;
    }
    
    @GetMapping(value="/login")
  	public String geLoginForm() {
	  return "login";
  	}

	@GetMapping(value="/user")
  	public String geUserForm() {
	  return "user";
  	}

	@PostMapping(value="/user")
  	public String addUser(HttpServletRequest request,
						@RequestParam String username, 
						@RequestParam String password, 
						@RequestParam String role) {
	
		if(userService.usernameExists(username)) {
			System.out.println("User " + username + " already exists");
			return "redirect:/home";
		}
		userService.save(username, password, role);

		try {
			request.login(username, password);
		} catch (ServletException e) {
			e.printStackTrace();
		}

		return "redirect:/home";
	}
}