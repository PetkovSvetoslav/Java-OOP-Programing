package Exercises.Telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> numbers = Arrays.asList(reader.readLine().split("\\s+"));
        List<String> urls = Arrays.asList(reader.readLine().split("\\s+"));

        Smartphone smartphone = new Smartphone(numbers, urls);

        System.out.println(smartphone.call());
        System.out.println(smartphone.browse());
    }
}
