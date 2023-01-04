package Exercises.word;

import java.util.Deque;

public class CutTransform implements TextTransform {
    private Deque<String> memory;

    public CutTransform(Deque<String> memory) {
        this.memory = memory;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        String cutString = text.substring(startIndex, endIndex);
        text.replace(startIndex, endIndex, "");

        this.memory.clear();
        this.memory.push(cutString);
    }
}
