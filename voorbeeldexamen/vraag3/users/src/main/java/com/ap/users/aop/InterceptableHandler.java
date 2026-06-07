package com.ap.users.aop;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ap.users.service.RedisService;

@Aspect
@Component
public class InterceptableHandler {

    @Autowired
	private RedisService service;

     /*@Before("@annotation(edu.ap.spring.aop.Interceptable)")
     public void beforeInterceptable(JoinPoint joinPoint) {
        System.out.println("Before " + joinPoint.getSignature());
    }

    @After("@annotation(edu.ap.spring.aop.Interceptable)")
    public void afterInterceptable(JoinPoint joinPoint) {
        System.out.println("After " + joinPoint.getSignature());
    }*/

    /*@Before("execution(* edu.ap.spring.jpa.PersonRepository.findAll(..))")
    public void beforeInterceptable2(JoinPoint joinPoint) {
        System.out.println("Before " + joinPoint.getSignature());
    }*/

    /*@Before("execution(* edu.ap.spring.jpa.PersonRepository.findByName(..))")
    public void beforeInterceptable3(JoinPoint joinPoint) {
        System.out.println("Before " + joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        for(Object arg : args) {
            System.out.println(arg);
        }
    }*/

    @Around("execution(public * com.ap.users.controller.UserController.doLogin(..))")
    public String aroundInterceptable(ProceedingJoinPoint joinPoint) throws Throwable {      
        String result = joinPoint.proceed().toString();

        if ("LOGGED IN".equals(result)) {
            // we knippen de argumenten uit de methode (email en paswoord)
            String key = this.bytesToHex("" + joinPoint.getArgs()[0] + joinPoint.getArgs()[1]);
            
            // we zoeken de volledige key in Redis (bijv: users:HEX:5)
            java.util.Set<String> keys = service.keys("users:" + key + ":*");
            
            if(!keys.isEmpty()) {
                String fullKey = keys.iterator().next();
                // splits de key op de dubbele punten om het userid (laatste deel) te pakken
                String[] parts = fullKey.split(":");
                int userid = Integer.parseInt(parts[2]);
                
                // zet het bit op positie 'userid' op true
                service.setBit("login", userid, true);
            }
        }

        return result;
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
