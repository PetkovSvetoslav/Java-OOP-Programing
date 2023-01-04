package models.layouts;

public class XmlLayout extends BaseLayout {

    @Override
    public String format(String dateTime, String reportLevel, String message) {
        return "<log>" + System.lineSeparator() +
                "   <date>" + dateTime + "</date>" + System.lineSeparator() +
                "   <level>" + reportLevel + "</level>" + System.lineSeparator() +
                "   <message" + message + "</message>" + System.lineSeparator() +
                "</log>";
    }

    @Override
    public String getLayoutType() {
        return "XmlLayout";
    }
}
