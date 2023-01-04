package models.appenders;

import enums.ReportLevel;
import interfaces.File;
import interfaces.Layout;
import models.files.LogFile;

public class FileAppender extends BaseAppender {
    private File file;

    public FileAppender(Layout layout) {
        super(layout);
        this.file = new LogFile();
    }

    @Override
    protected String appenderType() {
        return "FileAppender";
    }

    @Override
    public void append(String dateTime, ReportLevel reportLevel, String message) {
        if (canAppend(reportLevel)) {
            super.incrementAppendsCount();
            this.file.write(getLayout().format(dateTime, reportLevel.name(), message));
        }
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return super.toString() + ", File Size: " + this.file.getSize();
    }
}
