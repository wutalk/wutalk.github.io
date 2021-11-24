package com.java8;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FunctionReference {
    @Test
    public void funcAsValue() {
        File[] files = new File(".").listFiles(File::isFile);
        Arrays.stream(files).forEach((File f) -> System.out.println(f.getAbsolutePath()));
    }

    @Test
    public void testMap() {
        List<Pet> pets = Arrays.asList(
                new Pet("doggy", 12, "DOG"),
                new Pet("miao", 8, "CAT"),
                new Pet("dunky", 40, "COMMON"),
                new Pet("bunny", 4, "COMMON"));
        List<String> names = pets.stream()
                .map(Pet::getName)
                .collect(Collectors.toList());
        System.out.println(names);

        List<String> words = Arrays.asList("Hello", "World");
        List<String> distChars = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distChars);
    }

    @Test
    public void testReduce() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }
}

class Pet {
    String name;
    int weight;
    String type;

    public Pet(String name, int weight, String type) {
        this.name = name;
        this.weight = weight;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return weight == pet.weight && Objects.equals(name, pet.name) && Objects.equals(type, pet.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, type);
    }
}