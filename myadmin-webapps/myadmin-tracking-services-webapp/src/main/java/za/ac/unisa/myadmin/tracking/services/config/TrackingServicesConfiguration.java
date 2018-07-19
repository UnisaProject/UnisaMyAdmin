package za.ac.unisa.myadmin.tracking.services.config;

import org.apache.cxf.endpoint.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import za.ac.unisa.myadmin.spring.boot.configurations.AbstractServiceConfiguration;
import za.ac.unisa.myadmin.tracking.services.TrackingService;
import za.ac.unisa.myadmin.tracking.services.TrackingServicesConstants;
import za.ac.unisa.myadmin.tracking.services.impl.TrackingServiceImpl;
import za.ac.unisa.myadmin.tracking.services.rest.impl.TrackingRestServiceImpl;

@Configuration
public class TrackingServicesConfiguration extends AbstractServiceConfiguration {

	@Bean(name = "trackingServiceImpl")
	public TrackingService getTrackingServiceImpl() {
		return new TrackingServiceImpl();
	}

	@Bean(name = "trackingServiceImplRestEndPoint")
	public Server trackingServiceImplRestEndPoint() {
		TrackingRestServiceImpl restServiceImpl = new TrackingRestServiceImpl();
		restServiceImpl.setNextDecorator(getTrackingServiceImpl());

		return createRestEndpoint(restServiceImpl, "/rest/" + TrackingServicesConstants.POSTAL_TRACKING_SERVICE_NAME);
	}

}
