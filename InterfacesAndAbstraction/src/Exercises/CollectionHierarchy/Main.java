package Exercises.CollectionHierarchy;

import Exercises.CollectionHierarchy.entities.AddCollection;
import Exercises.CollectionHierarchy.entities.AddRemoveCollection;
import Exercises.CollectionHierarchy.entities.MyListImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] itemsToAdd = reader.readLine().split("\\s+");
        int removeOperationCount = Integer.parseInt(reader.readLine());

        AddCollection addCollection = new AddCollection();
        for (String item : itemsToAdd) {
            System.out.print(addCollection.add(item) + " ");
        }
        System.out.println();

        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        for (String item : itemsToAdd) {
            System.out.print(addRemoveCollection.add(item) + " ");
        }
        System.out.println();

        MyListImpl myList = new MyListImpl();
        for (String item : itemsToAdd) {
            System.out.print(myList.add(item) + " ");
        }
        System.out.println();

        for (int i = 0; i < removeOperationCount; i++) {
            System.out.print(addRemoveCollection.remove() + " ");
        }
        System.out.println();

        for (int i = 0; i < removeOperationCount; i++) {
            System.out.print(myList.remove() + " ");
        }
    }
}
