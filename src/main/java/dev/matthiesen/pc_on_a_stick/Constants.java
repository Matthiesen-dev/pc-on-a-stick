package dev.matthiesen.pc_on_a_stick;

import dev.matthiesen.common.matthiesen_lib_api.MatthiesenLibApi;
import dev.matthiesen.common.matthiesen_lib_api.core.MatthiesenLibApiMetricsManager;
import dev.matthiesen.common.matthiesen_lib_api.core.metric.UniversalMetricContext;
import dev.matthiesen.libs.faststats.ErrorTracker;
import dev.matthiesen.libs.faststats.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Constants {
    public static final String MOD_ID = "pc_on_a_stick";
    public static final String MOD_NAME = "Cobblemon PC on a Stick";
    public static @Token final String METRIC_TOKEN = "4cca9fca1856eb97b6c0ae6e0be9536e";
    public static final ErrorTracker ERROR_TRACKER = MatthiesenLibApiMetricsManager.getErrorTracker();
    @SuppressWarnings("unused")
    public static final UniversalMetricContext METRIC_CONTEXT = MatthiesenLibApiMetricsManager.makeErrorMetricsContext(
            MOD_ID,
            METRIC_TOKEN,
            ERROR_TRACKER
    );

    public static void readyMetric() {
        MatthiesenLibApi.registerModToApiMetrics(MOD_ID);
    }

    public static Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static void createInfoLog(String message) {
        LOGGER.info(message);
    }

    public static void createErrorLog(String message, Throwable throwable) {
        ERROR_TRACKER.trackError(throwable);
        LOGGER.error(message, throwable);
    }
}
