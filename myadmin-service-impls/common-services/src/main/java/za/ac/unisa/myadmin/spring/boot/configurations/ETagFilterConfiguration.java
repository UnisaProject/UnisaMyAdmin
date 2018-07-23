package za.ac.unisa.myadmin.spring.boot.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;

/**
 * Configuration that enables the ETag Filter.
 * 
 * The ETag filter creates a hash value for each GET response and sends it to the client with a <code>ETag</code> header
 * (. The client (web browser) can then send the known
 * has with the next GET request. The server will then process the request, and generate a hash again for the the response
 * it would have sent. If the response has matches that has that the client sent, we know that the client already has
 * the correct cached copy of the response and there is no need to send that same response again. The server will then
 * respond with a 304 (NOT MODIFIED) HTTP response to indicate to the client that the response has not changed, and the
 * body will not be sent again.
 *
 * This optimizes bandwidth by reducing the amount of data sent back by the server, while still avoiding potential old
 * cached data on the client; compared to using expires headers for caching which implicitly causes data to be cached
 * until the expiry date has been reached.
 *
 * Using ETags does not add any benefit to server performance as the entire service call is still processed and the view
 * (be it json, html, xml, etc) is still rendered and only then a hash is calculated and compared to the client hash
 * (if the client provided a hash)
 *
 * The gain of using ETag becomes more apparent when the potential response becomes larger. For very small responses the
 * ETag might actually cause a slight overhead as the headers sent might be larger than the actual data.
 */
@Configuration
public class ETagFilterConfiguration {

	@Bean
	public Filter shallowETagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}

}
