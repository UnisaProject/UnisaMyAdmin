package za.ac.unisa.myadmin.server.configurations;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Cross-origin resource sharing (CORS) is a mechanism that allows JavaScript on
 * a web page to make XMLHttpRequests to another domain, not the domain the
 * JavaScript originated from.
 * 
 * Access-Control-Allow-Origin: specifies the authorized domains to make
 * cross-domain request (you should include the domains of your REST clients or
 * "*" if you want the resource public and available to everyone – the latter is
 * not an option if credentials are allowed during CORS requests)
 * 
 * Access-Control-Allow-Credentials: indicates if the server allows credentials
 * during CORS requests
 * 
 * Access-Control-Allow-Methods: indicates the methods allowed when accessing
 * the resource
 * 
 * Access-Control-Allow-Headers: used in response to a preflight request to
 * indicate which HTTP headers can be used when making the actual request.
 * 
 * Access-Control-Expose-Headers: lets a server white list headers that browsers
 * are allowed to access
 * 
 * https://www.w3.org/TR/cors/
 * 
 * @author Jannie Louwrens
 *
 */
@Configuration
public class ResponseCorsFilterConfiguration {

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Collections.singletonList(CorsConfiguration.ALL));
		configuration.setAllowedMethods(Arrays.asList("ACL", "CANCELUPLOAD", "CHECKIN", "CHECKOUT", "COPY", "DELETE",
				"GET", "HEAD", "LOCK", "MKCALENDAR", "MKCOL", "MOVE", "OPTIONS", "POST", "PROPFIND", "PROPPATCH", "PUT",
				"REPORT", "SEARCH", "UNCHECKOUT", "UNLOCK", "UPDATE", "VERSION-CONTROL"));
		configuration.setAllowedHeaders(Arrays.asList("Origin", "X-Requested-With", "Content-Type", "Accept", "Key",
				"Authorization", "If-Modified-Since"));
		configuration.setExposedHeaders(Arrays.asList("DAV", "content-length", "Allow", "stacktrace"));
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(36000L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return new CorsFilter(source);
	}

}
