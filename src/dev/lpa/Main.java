package dev.lpa;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean flag = true;
        String userInput = "";
        boolean forward = true;

        Scanner scanner = new Scanner(System.in);
        var placesToVisit = new LinkedList<Place>();


        addNewPlace(placesToVisit, new Place("Sydney", 0));
        addNewPlace(placesToVisit, new Place("Adelaide", 1374));
        addNewPlace(placesToVisit, new Place("Alice Springs", 2771));
        addNewPlace(placesToVisit, new Place("Brisbane", 917));
        addNewPlace(placesToVisit, new Place("Darwin", 3972));
        addNewPlace(placesToVisit, new Place("Melbourne", 877));
        addNewPlace(placesToVisit, new Place("Perth", 3923));


        addNewPlace(placesToVisit, new Place("adelaide", 1374));


        var iterator = placesToVisit.listIterator();

        printActions();

        while (flag){

            if (!iterator.hasPrevious()) {
                System.out.println("You are in " + iterator.next().name());
                forward = true;
            }

            if (!iterator.hasNext()) {
                System.out.println("You are in " + iterator.previous().name());
                forward = false;
            }

            System.out.print("Enter Value: ");
            userInput = scanner.nextLine().toUpperCase().substring(0, 1);

            switch (userInput) {
                case "F" -> {
                    if (!forward) {         // Reversing Direction
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next(); // Adjust position forward
                        }
                    }

                    if (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                }
                case "B" -> {
                    if (forward) {         // Reversing Direction
                        forward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous(); // Adjust position backwards
                        }
                    }

                    if (iterator.hasPrevious()) {
                        System.out.println(iterator.previous());
                    }
                }
                case "L" -> {
                    System.out.println(placesToVisit);
//                    for (int i = 0; i < placesToVisit.size(); i++) {
//                        System.out.println(placesToVisit.get(i).name());
//                    }
                }
                case "M" -> printActions();
                case "Q" -> flag = false;

                default -> {

                }
            }

        }


    }

    private static void printActions() {
        System.out.println("""
                Available actions (select word or letter):
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit""");

    }

    private static void printElement(LinkedList<Place> list, int index) {

        System.out.println(list.get(index).name());

    }


    private static void addNewPlace(LinkedList<Place> list, Place place) {

        if (list.contains(place)) {
            System.out.println(place.name() + " is already on the list");
            return;
        }

        for (Place p : list) {
            if (p.name().equalsIgnoreCase(place.name())) {
                System.out.println(place.name() + " is already on the list");
                return;
            }
        }

        int matchedIndex = 0;
        for (var listPlace : list) {
            if (place.distance() < listPlace.distance()) {
                list.add(matchedIndex, place);
                return;
            }

            matchedIndex++;
        }

//        for (int i = 1; i < list.size(); i++) {
//            if (list.get(i - 1).distance() <= place.distance() &&
//                    list.get(i).distance() > place.distance()) {
//                list.add(i, place);
//                return;
//            }
//        }
        list.add(place);
    }
}

record Place(String name, int distance) {

    @Override
    public String toString() {
        return String.format("%s (%d)", name, distance);
    }
}
