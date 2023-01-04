package models.appenders;

import enums.ReportLevel;
import interfaces.Layout;

public class ConsoleAppender extends BaseAppender {
    public ConsoleAppender(Layout layout) {
        super(layout);
    }

    @Override
    protected String appenderType() {
        return "ConsoleAppender";
    }

    @Override
    public void append(String dateTime, ReportLevel reportLevel, String message) {
        if (canAppend(reportLevel)) {
            super.incrementAppendsCount();
            System.out.println(super.getLayout().format(dateTime, reportLevel.name(), message));
        }
    }
}
