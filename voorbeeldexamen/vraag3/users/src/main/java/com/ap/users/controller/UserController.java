package com.ap.users.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ap.users.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class UserController {

    private final RedisService service;

    public UserController(RedisService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getIndex() {
        return "redirect:/signup";
    }

    @GetMapping("/signup")
    public String getUsers() {
        return "signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    public String saveUser(@RequestParam String email, @RequestParam String password, Model model) {
        
        if (!service.exists("usercount")) {
            service.incr("usercount");
        }
        
        String key = "users:" + bytesToHex(email + password) + ":" + service.getKey("usercount");
		if(!service.exists(key)) {
			service.setKey(key, email);
			service.incr("usercount");
		}   

		return "SIGNED UP";
	}

    @GetMapping("/login")
    public String getLoginString() {
        return "login";
    }
    
    @PostMapping("/login")
    @ResponseBody // returned de string en niet een pagina
    public String doLogin(@RequestParam String email, @RequestParam String password, Model model) {
        
        String key = bytesToHex(email + password);
        Set<String> s = service.keys("users:" + key + ":*");
        if (!s.isEmpty())
        {
            return "LOGGED IN";
        }
        
        return "NOT LOGGED IN";
    }
    
    @GetMapping("/user/{userid}")
	@ResponseBody // returned de string en niet een pagina
	public String getUser(@org.springframework.web.bind.annotation.PathVariable("userid") String userid) {
		Set<String> keys = service.keys("users:*:" + userid);
        if(!keys.isEmpty()) {
            String key = keys.iterator().next(); // pak de eerste (en enige) match
            return service.getKey(key); // geef de opgeslagen email terug
        }
        return "User not found";
	}

    private String bytesToHex(String str) {
        String retString = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest((str).getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < encodedhash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            retString = hexString.toString();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return retString;
    }

}
