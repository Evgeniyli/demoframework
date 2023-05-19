package com.ui.core.report;

import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.ui.core.utils.DateUtils.*;
import static com.ui.core.utils.MessageColour.ANSI_CYAN;
import static com.ui.core.utils.MessageColour.ANSI_YELLOW;

public class TestReporter {

    public static final Logger logger = LogManager.getLogger(TestReporter.class);
    private static final String DEBUG_STATUS = "debug";
    private static final String ERROR_STATUS = "error";
    private final String pattern = MONTH_NUMBER_FORM + "-" + DAY + "-" + YEAR + " " + HOUR_24_FORMAT + ":" + MINUTE + ":" + SECOND + " " + AM_PM_MARKER;
    private final String timeStep = getDateTime(getCurrentTimeZone(), pattern, 0);

    /**
     * Report debug step
     *
     * @param debugStepMessage debug message
     * @param parameters       message constructing parameters
     * @see String#format
     */
    public static void reportDebugStep(String debugStepMessage, Object... parameters) {
        String message = String.format(new TestReporter().timeStep + " - " + debugStepMessage, parameters);
        checkLoggerStatus("true", DEBUG_STATUS, String.format(debugStepMessage, getParametersByNewObject(parameters)));
        reportAllureStep(message);
    }

    /**
     * Report Error step
     *
     * @param debugStepMessage debug message
     * @param parameters       message constructing parameters
     * @see String#format
     */
    public static void reportErrorStep(String debugStepMessage, Object... parameters) {
        String message = String.format(new TestReporter().timeStep + " - " + debugStepMessage, parameters);
        checkLoggerStatus("true", ERROR_STATUS, String.format(debugStepMessage, getParametersByNewObject(parameters)));
        reportAllureStep(message);
    }

    private static void checkLoggerStatus(String status, String loggerInfo, String message) {
        if (status.equals("true") && loggerInfo.equals("debug")) {
            logger.debug(message);
        }
        if (status.equals("true") && loggerInfo.equals("error")) {
            logger.error(message);
        }
        if (status.equals("true") && loggerInfo.equals("info")) {
            logger.info(message);
        }
    }


    @Step("{0}")
    private static void reportAllureStep(String stepMessage) {

    }


    /**
     * Get parameter for console with color by creation new object
     *
     * @param parameters logger
     * @return new instance
     */
    public static Object[] getParametersByNewObject(Object[] parameters) {
        Object[] newObject = new Object[parameters.length];
        String temp;
        for (int i = 0; i < parameters.length; i++) {
            temp = ANSI_YELLOW + parameters[i] + ANSI_CYAN;
            newObject[i] = temp;
        }
        return newObject;
    }
}
