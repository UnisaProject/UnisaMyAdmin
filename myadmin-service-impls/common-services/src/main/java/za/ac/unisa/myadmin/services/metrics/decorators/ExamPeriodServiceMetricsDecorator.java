package za.ac.unisa.myadmin.services.metrics.decorators;

import java.util.List;

import org.apache.commons.logging.LogFactory;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExamPeriodService;
import za.ac.unisa.myadmin.exam.services.dto.ExamPeriodInfo;
import za.ac.unisa.myadmin.services.base.decorators.ExamPeriodServiceDecorator;
import za.ac.unisa.myadmin.spring.boot.configurations.DefaultMetricsServletContextListener;

public class ExamPeriodServiceMetricsDecorator extends ExamPeriodServiceDecorator implements ExamPeriodService {

	private String metricsPrefix = "main";
	private final MetricRegistry metricRegistry;

	public ExamPeriodServiceMetricsDecorator() {
		metricRegistry = DefaultMetricsServletContextListener.METRIC_REGISTRY;
	}

	@Override
	public ExamPeriodInfo getExamPeriod(Integer code) throws DoesNotExistException, MissingParameterException,
			InvalidParameterException, OperationFailedException {
		Timer timer = metricRegistry
				.timer(MetricRegistry.name(ExamPeriodService.class, metricsPrefix, "getExamPeriod"));
		Timer.Context context = timer.time();
		try {
			return getNextDecorator().getExamPeriod(code);
		} catch (RuntimeException ex) {
			LogFactory.getLog(this.getClass()).error("Unexpected RuntimeException", ex);
			throw new OperationFailedException("Unexpected RuntimeException:" + ex.toString(), ex);
		} finally {
			context.stop();
		}
	}

	@Override
	public List<ExamPeriodInfo> getExamPeriods() throws OperationFailedException {
		Timer timer = metricRegistry
				.timer(MetricRegistry.name(ExamPeriodService.class, metricsPrefix, "getExamPeriods"));
		Timer.Context context = timer.time();
		try {
			return getNextDecorator().getExamPeriods();
		} catch (RuntimeException ex) {
			LogFactory.getLog(this.getClass()).error("Unexpected RuntimeException", ex);
			throw new OperationFailedException("Unexpected RuntimeException:" + ex.toString(), ex);
		} finally {
			context.stop();
		}
	}

	@Override
	public List<ExamPeriodInfo> getExamPeriodsByCodes(List<Integer> codes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Timer timer = metricRegistry
				.timer(MetricRegistry.name(ExamPeriodService.class, metricsPrefix, "getExamPeriodsByCodes"));
		Timer.Context context = timer.time();
		try {
			return getNextDecorator().getExamPeriodsByCodes(codes);
		} catch (RuntimeException ex) {
			LogFactory.getLog(this.getClass()).error("Unexpected RuntimeException", ex);
			throw new OperationFailedException("Unexpected RuntimeException:" + ex.toString(), ex);
		} finally {
			context.stop();
		}
	}

	public String getMetricsPrefix() {
		return metricsPrefix;
	}

	public void setMetricsPrefix(String metricsPrefix) {
		this.metricsPrefix = metricsPrefix;
	}

}
