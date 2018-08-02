package za.ac.unisa.myadmin.spring.boot.configurations;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;

public class DefaultMetricsServletContextListener extends MetricsServlet.ContextListener {

	public static final MetricRegistry METRIC_REGISTRY = new MetricRegistry();

	@Override
	protected MetricRegistry getMetricRegistry() {
		return METRIC_REGISTRY;
	}

}
