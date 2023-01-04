package models.appenders;

import enums.ReportLevel;
import interfaces.Appender;
import interfaces.Layout;

public abstract class BaseAppender implements Appender {
    private final Layout layout;
    private ReportLevel reportLevel;
    private int appendsCount;

    protected BaseAppender(Layout layout) {
        this.layout = layout;
        this.reportLevel = ReportLevel.INFO;
        this.appendsCount = 0;
    }

    protected Layout getLayout() {
        return this.layout;
    }

    protected abstract String appenderType();

    protected void incrementAppendsCount() {
        this.appendsCount++;
    }

    @Override
    public void setReportLevel(ReportLevel reportLevel) {
        this.reportLevel = reportLevel;
    }

    @Override
    public boolean canAppend(ReportLevel reportLevel) {
        return this.reportLevel.ordinal() <= reportLevel.ordinal();
    }

    @Override
    public String toString() {
        return String.format(
                "Appender Type: %s, Layout Type: %s" +
                        ", Report Level: %s, Messages Appended: %d"
                , this.appenderType(), this.layout.getLayoutType()
                , this.reportLevel.name(), this.appendsCount);
    }
}
