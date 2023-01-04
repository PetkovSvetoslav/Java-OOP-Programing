package models.loggers;

import enums.ReportLevel;
import interfaces.Appender;
import interfaces.Logger;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class MessageLogger implements Logger {
    private Appender[] appenders;

    public MessageLogger(Appender... appenders) {
        this.appenders = appenders;
    }

    private void callAllAppenders(String dateTime, ReportLevel reportLevel, String message) {
        for (Appender appender : this.appenders) {
            appender.append(dateTime, reportLevel, message);
        }
    }

    @Override
    public void logInfo(String dateTime, String message) {
        callAllAppenders(dateTime, ReportLevel.INFO, message);
    }

    @Override
    public void logWarning(String dateTime, String message) {
        callAllAppenders(dateTime, ReportLevel.WARNING, message);
    }

    @Override
    public void logError(String dateTime, String message) {
        callAllAppenders(dateTime, ReportLevel.ERROR, message);
    }

    @Override
    public void logCritical(String dateTime, String message) {
        callAllAppenders(dateTime, ReportLevel.CRITICAL, message);
    }

    @Override
    public void logFatal(String dateTime, String message) {
        callAllAppenders(dateTime, ReportLevel.FATAL, message);
    }

    @Override
    public String toString() {
        return "Logger info" + System.lineSeparator() +
                Arrays.stream(this.appenders)
                        .map(Objects::toString)
                        .collect(Collectors.joining(System.lineSeparator()));
    }
}
