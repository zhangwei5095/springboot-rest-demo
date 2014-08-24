package demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan
@RestController
@EnableAutoConfiguration
public class Application {
	/*
	 * CRUD
	 * c = create = POST
	 * r = read = GET
	 * u = update = PUT
	 * d = delete = DELETE
	 */
	@RequestMapping(method = {RequestMethod.GET}, value = "/user/{username}", consumes = {"application/json"}, produces={"application/json"})
	public Map<String, String> getUser(@PathVariable("username") String username) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("fullname", "Eddu Melendez");
		System.out.println("User was found.");
		return map;
	}
	
	@RequestMapping(method = {RequestMethod.POST}, value = "/user", consumes = {"application/json"}, produces={"application/json"})
	public Map<String, String> addUser(@RequestBody Map<String, String> requestBody) {
		System.out.println("User was created.");
		return requestBody;
	}
	
	@RequestMapping(method = {RequestMethod.PUT}, value = "/user", consumes = {"application/json"}, produces={"application/json"})
	public Map<String, String> updateUser(@RequestBody Map<String, String> requestBody) {
		System.out.println("User was updated.");
		return requestBody;
	}
	
	@RequestMapping(method = {RequestMethod.DELETE}, value = "/user/{username}", consumes = {"application/json"}, produces={"application/json"})
	public Map<String, String> deleteUser(@PathVariable("username") String username) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("fullname", "Eddu Melendez");
		System.out.println("User was deleted.");
		return map;
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
