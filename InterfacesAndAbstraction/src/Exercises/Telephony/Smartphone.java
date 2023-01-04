package Exercises.Telephony;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Smartphone implements Callable, Browsable {
    private final List<String> numbers;
    private final List<String> urls;

    private final static Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");
    private final static Pattern URL_PATTERN = Pattern.compile("^[\\D]+$");

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String call() {
        StringBuilder out = new StringBuilder();
        for (String number : this.numbers) {
            if (isANumber(number)) {
                out.append("Calling... ").append(number);
            } else {
                out.append("Invalid number!");
            }
            out.append(System.lineSeparator());
        }
        return out.toString().trim();
    }

    @Override
    public String browse() {
        StringBuilder out = new StringBuilder();
        for (String url : this.urls) {
            if (containNumber(url)) {
                out.append("Invalid URL!");
            } else {
                out.append("Browsing: ").append(url).append("!");
            }
            out.append(System.lineSeparator());
        }
        return out.toString().trim();
    }

    private boolean isANumber(String number) {
        Matcher matcher = NUMBER_PATTERN.matcher(number);
        return matcher.find();
    }

    private boolean containNumber(String text) {
        Matcher matcher = URL_PATTERN.matcher(text);
        return !matcher.find();
    }
}
