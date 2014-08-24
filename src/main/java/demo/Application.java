package demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.model.ApiInfo;

@Configuration
@ComponentScan
@RestController
@EnableAutoConfiguration
@EnableSwagger
@Api(value = "users", description = "Users API")
public class Application {

//	private static final String SWAGGER_GROUP = "user-api";
	private SpringSwaggerConfig springSwaggerConfig;

	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
				.apiInfo(apiInfo())
				.includePatterns("/.*");
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("User API Title",
				"User Apps API Description", "User API terms of service",
				"User API Contact Email", "User API Licence Type",
				"User API License URL");
		return apiInfo;
	}
//
//	@Bean
//	public SwaggerApiResourceListing swaggerApiResourceListing() {
//		SwaggerApiResourceListing swaggerApiResourceListing = new SwaggerApiResourceListing(
//				springSwaggerConfig.swaggerCache(), SWAGGER_GROUP);
//		swaggerApiResourceListing.setApiInfo(apiInfo());
//		swaggerApiResourceListing.setApiListingReferenceScanner(apiListingReferenceScanner());
//		return swaggerApiResourceListing;
//	}
//
//	@Bean
//	public ApiListingReferenceScanner apiListingReferenceScanner() {
//		ApiListingReferenceScanner apiListingReferenceScanner = new ApiListingReferenceScanner();
//		apiListingReferenceScanner.setSwaggerGroup(SWAGGER_GROUP);
//		apiListingReferenceScanner.setIncludePatterns(Arrays.asList("/.*"));
//		return apiListingReferenceScanner;
//	}

	/*
	 * CRUD c = create = POST r = read = GET u = update = PUT d = delete =
	 * DELETE
	 */
	@RequestMapping(method = { RequestMethod.GET }, value = "/user/{username}", consumes = { "application/json" }, produces = { "application/json" })
	@ApiOperation(value = "Get Users", notes = "Return users")
	public Map<String, String> getUser(@PathVariable("username") String username) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("fullname", "Eddu Melendez");
		System.out.println("User was found.");
		return map;
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/user", consumes = { "application/json" }, produces = { "application/json" })
	@ApiOperation(value = "Add Users", notes = "Add new users")
	public Map<String, String> addUser(
			@RequestBody Map<String, String> requestBody) {
		System.out.println("User was created.");
		return requestBody;
	}

	@RequestMapping(method = { RequestMethod.PUT }, value = "/user", consumes = { "application/json" }, produces = { "application/json" })
	@ApiOperation(value = "Update Users", notes = "Update users")
	public Map<String, String> updateUser(
			@RequestBody Map<String, String> requestBody) {
		System.out.println("User was updated.");
		return requestBody;
	}

	@RequestMapping(method = { RequestMethod.DELETE }, value = "/user/{username}", consumes = { "application/json" }, produces = { "application/json" })
	@ApiOperation(value = "Delete Users", notes = "Delete users")
	public Map<String, String> deleteUser(
			@PathVariable("username") String username) {
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
