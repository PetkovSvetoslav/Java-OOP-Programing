package Lab.IOUtils;

import java.util.Arrays;

public class InputParser {

    public static int[] parseArray(String line, String pattern) {
        return Arrays.stream(line.split(pattern))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
