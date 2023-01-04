package interfaces;

import enums.ReportLevel;

public interface Appender {

    void append(String dateTime, ReportLevel reportLevel, String message);

    void setReportLevel(ReportLevel reportLevel);

    boolean canAppend(ReportLevel reportLevel);
}
