package de.humi.advent;

import de.humi.advent.utils.FileUtils;
import de.humi.advent.utils.Puzzle;

import java.nio.file.Path;
import java.util.List;

public class Aoc03 implements Puzzle {
    private static final String input = ".\\inputs\\input03.txt";
    private final List<String> bags = FileUtils.getContentOfFile(Path.of(input));

    @Override
    public void solve() {
        int result = bags.stream()
                .map(Bag::new)
                .map(Bag::findDuplicateItem)
                .map(this::calcWeight)
                .reduce(0, Integer::sum);
        System.out.println(result);
    }

    public int calcWeight(char item) {
        return item >= 97 ? item - 96 : item - 38;
    }

    public static class Bag {
        private final String compartment1;
        private final String compartment2;

        public Bag(String bag) {
            this.compartment1 = bag.substring(0, bag.length() / 2);
            this.compartment2 = bag.substring(bag.length() / 2);
        }

        public char findDuplicateItem() {
            for (char item : compartment2.toCharArray()) {
                if (compartment1.contains("" + item)) {
                    return item;
                }
            }
            throw new RuntimeException();
        }

    }
}
