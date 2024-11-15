/**
 * 
 */
package com.acma.properties.config;

import java.time.Duration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Configuration
@Slf4j
public class RouteConfig {

		
	@Bean
	public RouteLocator routeDefinitions(RouteLocatorBuilder builder) {
		log.info("****Route Definitions Locator****");
		return builder.routes()
				
		.route("01-usermgmt-api-routes", (route->route.path("/acma/users/**")
				.filters(filter->filter
						 .addRequestHeader("userType", "agent")
						 .rewritePath("/acma/users/?(?<segment>.*)", "/users/$\\{segment}")
						 .addResponseHeader("userType", "agent")
						 .localResponseCache(Duration.ofMinutes(3), DataSize.ofMegabytes(500)))
				.uri("lb://ACMA-USERMGMT-SERVICE")
												  
													    
											      ))
//		.route("01a-usermgmt-document-routes", (route->route.path("/swagger-ui.html")//predicate
//													        .uri("lb://ACMA-USERMGMT-SERVICE")))
//		
//		.route("01b-usermgmt-document-routes", (route->route.path("/swagger-ui/**")
//															.uri("lb://ACMA-USERMGMT-SERVICE")))
//		
//		.route("01c-usermgmt-document-routes", (route->route.path("/v3/**")
//															.uri("lb://ACMA-USERMGMT-SERVICE")))
//		
//		.route("02-authz-token-routes", (route->route.path("/token")
//				                                      .uri("lb://ACMA-AUTHN-AUTHZ-SERVICE")))
		
		.build();
		
	}
}
