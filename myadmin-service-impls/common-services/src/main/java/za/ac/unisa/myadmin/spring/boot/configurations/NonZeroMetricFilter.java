package za.ac.unisa.myadmin.spring.boot.configurations;

import com.codahale.metrics.Counting;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;

public class NonZeroMetricFilter implements MetricFilter {

    @Override
    public boolean matches(String string, Metric metric) {
        if (metric instanceof Counting) {
            Counting counting = (Counting) metric;
            if (counting.getCount() > 0l) {
                return true;
            }
            return false;
        }

        if (metric instanceof Gauge) {
            Gauge gauge = (Gauge) metric;
            Object value = gauge.getValue();
            if (value instanceof Long) {
                Long l = (Long) value;
                if (l > 0l) {
                    return true;
                } 
                return false;
            }
        }
        return true;
    }

}
