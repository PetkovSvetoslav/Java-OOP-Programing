package interfaces;

import enums.ReportLevel;

public interface Layout {

    String format(String dateTime, String reportLevel, String message);

    ReportLevel getReportLevel();

    String getLayoutType();
}
