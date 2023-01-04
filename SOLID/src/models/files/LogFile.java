package models.files;

import interfaces.File;

public class LogFile implements File {
    private int size;
    private final StringBuilder content;

    public LogFile() {
        this.size = 0;
        this.content = new StringBuilder();
    }

    private int getMessageSize(String message) {
        return message.chars()
                .filter(Character::isAlphabetic)
                .sum();
    }

    @Override
    public void write(String message) {
        this.content.append(message);
        this.size += getMessageSize(message);
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
