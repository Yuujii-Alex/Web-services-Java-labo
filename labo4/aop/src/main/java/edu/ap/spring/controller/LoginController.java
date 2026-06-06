package edu.ap.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ap.spring.service.WebUserService;
import edu.ap.spring.entity.WebUser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

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

    @PostMapping(value="/login")
    public String doLogin(HttpServletResponse response,
                          @RequestParam String username,
                          @RequestParam String password) {
        Optional<WebUser> userOpt = userService.findByUsername(username);
        if(userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            // Set cookie upon successful login
            Cookie cookie = new Cookie("Authorisation", userOpt.get().getRole());
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/home";
        }
        return "redirect:/login";
    }

	@PostMapping(value="/user")
  	public String addUser(HttpServletResponse response,
						@RequestParam String username, 
						@RequestParam String password, 
						@RequestParam String role) {
	
		if(userService.usernameExists(username)) {
			System.out.println("User " + username + " already exists");
			return "redirect:/home";
		}
		WebUser user = userService.save(username, password, role);

        // Auto login on registration
        Cookie cookie = new Cookie("Authorisation", role);
        cookie.setPath("/");
        response.addCookie(cookie);

		return "redirect:/home";
	}
}