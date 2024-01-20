package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

public class MainStream {
    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        if (sum % 2 == 0) {
            integers = integers.stream().filter(num -> num % 2 != 0).collect(Collectors.toList());
        } else {
            integers = integers.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
        }
        return integers;
    }

    private static List<Integer> oddOrEven2(List<Integer> values) {
        return values.stream()
                .collect(collectingAndThen(
                        Collectors.groupingBy(i -> i % 2),
                        map -> map.getOrDefault(
                                (map.getOrDefault(1, List.of()).size() + 1)
                                        % 2,
                                List.of())));
    }

    private static List<Integer> addList(int size) {
        List<Integer> integers = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            integers.add(i);
        }
        return integers;
    }

    public static void main(String[] args) {
        int[] values = new int[]{5, 7, 3, 1, 2, 3, 3, 2, 3};
        System.out.println("Результат метода int minValue(int[] values) через стрим " + minValue(values));


        int size = 4;
        System.out.println("Метод oddOrEven " + oddOrEven(addList(size)));
        System.out.println("Метод oddOrEven в одну строку " + oddOrEven2(addList(size)));
    }
}
