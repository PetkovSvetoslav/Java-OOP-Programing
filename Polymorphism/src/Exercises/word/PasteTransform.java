package Exercises.word;

import java.util.Deque;

public class PasteTransform implements TextTransform {
    private Deque<String> memory;

    public PasteTransform(Deque<String> memory) {
        this.memory = memory;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        String toPaste = this.memory.peek();

        if (startIndex == endIndex) {
            text.insert(startIndex, toPaste);
        }
        text.replace(startIndex, Math.min(endIndex, text.length()), toPaste);
    }
}
