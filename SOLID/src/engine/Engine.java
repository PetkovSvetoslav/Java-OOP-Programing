package engine;

import enums.ReportLevel;
import interfaces.Appender;
import interfaces.Layout;
import interfaces.Logger;
import models.appenders.AppenderWorkshop;
import models.layouts.LayoutWorkshop;
import models.loggers.MessageLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Engine implements Runnable {

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int n = Integer.parseInt(reader.readLine());

            Appender[] appenders = new Appender[n];

            for (int i = 0; i < n; i++) {
                String[] tokens = reader.readLine().split("\\s+");
                String appenderType = tokens[0];
                String layoutType = tokens[1];

                Layout layout = LayoutWorkshop.produce(layoutType);
                Appender appender = AppenderWorkshop.produce(appenderType, layout);

                if (tokens.length == 3) {
                    ReportLevel reportLevel = ReportLevel.valueOf(tokens[2]);
                    appender.setReportLevel(reportLevel);
                }

                appenders[i] = appender;
            }

            Logger logger = new MessageLogger(appenders);
            String input;
            while (!"END".equals(input = reader.readLine())) {
                String[] tokens = input.split("\\|");

                ReportLevel reportLevel = ReportLevel.valueOf(tokens[0]);
                String dateTime = tokens[1];
                String message = tokens[2];

                switch (reportLevel) {
                    case INFO -> logger.logInfo(dateTime, message);
                    case WARNING -> logger.logWarning(dateTime, message);
                    case ERROR -> logger.logError(dateTime, message);
                    case CRITICAL -> logger.logCritical(dateTime, message);
                    case FATAL -> logger.logFatal(dateTime, message);
                    default -> throw new IllegalArgumentException("Invalid report level: " + reportLevel + "!");
                }
            }

            System.out.println(logger);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
