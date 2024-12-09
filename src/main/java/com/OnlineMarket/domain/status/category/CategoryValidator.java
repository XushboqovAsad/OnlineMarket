package com.OnlineMarket.domain.status.category;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryValidator {
    public static boolean isCategoryValid(BigCategory bigCategory, String category) {
        switch (bigCategory){
            case Home:
                return Arrays.stream(Home.values())
                        .anyMatch(c -> c.name().equalsIgnoreCase(category));
            case Electronics:
                return Arrays.stream(Electronics.values())
                        .anyMatch(c -> c.name().equalsIgnoreCase(category));
            case Transport:
                return Arrays.stream(Transport.values())
                        .anyMatch(c -> c.name().equalsIgnoreCase(category));
            case Children:
                return Arrays.stream(Children.values())
                        .anyMatch(c -> c.name().equalsIgnoreCase(category));
            case Clothes:
                return Arrays.stream(Clothes.values())
                        .anyMatch(c -> c.name().equalsIgnoreCase(category));
            case Sport:
                return Arrays.stream(Sport.values())
                        .anyMatch(c -> c.name().equalsIgnoreCase(category));
            case Edu_materials:
                return Arrays.stream(EduMaterials.values())
                        .anyMatch(c -> c.name().equalsIgnoreCase(category));
            default:
                return false;
        }
    }


    public static List<String> getCategoryByBigCategory(BigCategory bigCategory) {
        switch (bigCategory){
            case Home:
                return Arrays.stream(Home.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Electronics:
                return Arrays.stream(Electronics.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Transport:
                return Arrays.stream(Transport.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Children:
                return Arrays.stream(Children.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Clothes:
                return Arrays.stream(Clothes.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Sport:
                return Arrays.stream(Sport.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Edu_materials:
                return Arrays.stream(EduMaterials.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            default:
                return Collections.emptyList();
        }
    }
}
