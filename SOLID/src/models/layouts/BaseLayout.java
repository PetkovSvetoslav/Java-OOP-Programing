package models.layouts;

import enums.ReportLevel;
import interfaces.Layout;

public abstract class BaseLayout implements Layout {
    ReportLevel reportLevel;

    @Override
    public ReportLevel getReportLevel() {
        return reportLevel;
    }
}
