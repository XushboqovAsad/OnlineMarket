package com.OnlineMarket.domain.status.location;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DistrictValidator {
    public static boolean isDistrictValid(Region region, String district) {
        switch (region) {
            case Toshkent:
                return Arrays.stream(ToshkentDistrict.values())
                        .anyMatch(d -> d.name().equalsIgnoreCase(district)) ||
                        Arrays.stream(ToshkentCityDistrict.values())
                                .anyMatch(d -> d.name().equalsIgnoreCase(district));
            case Sirdaryo:
                return Arrays.stream(SirdaryoDistrict.values())
                        .anyMatch(d -> d.name().equalsIgnoreCase(district));
            case Buxoro:
                return Arrays.stream(BuxoroDistrict.values())
                        .anyMatch(d -> d.name().equalsIgnoreCase(district));
            case Jizzax:
                return Arrays.stream(JizzaxDistrict.values())
                        .anyMatch(d -> d.name().equalsIgnoreCase(district));
            case Navoiy:
                return Arrays.stream(NavoiyDistrict.values())
                        .anyMatch(d -> d.name().equalsIgnoreCase(district));
            case Xorazm:
                return Arrays.stream(XorazmDistrict.values())
                        .anyMatch(d -> d.name().equalsIgnoreCase(district));
            case Andijon:
                return Arrays.stream(AndijonDistrict.values())
                        .anyMatch(d -> d.name().equalsIgnoreCase(district));
            case Fargona:
                return Arrays.stream(FargonaDistrict.values())
                        .anyMatch(d -> d.name().equalsIgnoreCase(district));
            case Namangan:
                return Arrays.stream(NamanganDistrict.values())
                        .anyMatch(d -> d.name().equalsIgnoreCase(district));
            case Samarqand:
                return Arrays.stream(SamarqandDistrict.values())
                        .anyMatch(d -> d.name().equalsIgnoreCase(district));
            case Qashqadaryo:
                return Arrays.stream(QashqadaryoDistrict.values())
                        .anyMatch(d -> d.name().equalsIgnoreCase(district));
            case Surxondaryo:
                return Arrays.stream(SurxondaryoDistrict.values())
                        .anyMatch(d -> d.name().equalsIgnoreCase(district));
            case Qoraqalpogiston:
                return Arrays.stream(QoraqalpogistonDistrict.values())
                        .anyMatch(d -> d.name().equalsIgnoreCase(district));
            default:
                return false;
        }
    }


    public static List<String> getDistrictsByRegion(Region region) {
        switch (region) {
            case Toshkent:
                return Stream.concat(
                        Arrays.stream(ToshkentDistrict.values()).map(Enum::name),
                        Arrays.stream(ToshkentCityDistrict.values()).map(Enum::name)
                ).collect(Collectors.toList());
            case Sirdaryo:
                return Arrays.stream(SirdaryoDistrict.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Buxoro:
                return Arrays.stream(BuxoroDistrict.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Jizzax:
                return Arrays.stream(JizzaxDistrict.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Navoiy:
                return Arrays.stream(NavoiyDistrict.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Xorazm:
                return Arrays.stream(XorazmDistrict.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Andijon:
                return Arrays.stream(AndijonDistrict.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Fargona:
                return Arrays.stream(FargonaDistrict.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Namangan:
                return Arrays.stream(NamanganDistrict.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Samarqand:
                return Arrays.stream(SamarqandDistrict.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Qashqadaryo:
                return Arrays.stream(QashqadaryoDistrict.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Surxondaryo:
                return Arrays.stream(SurxondaryoDistrict.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            case Qoraqalpogiston:
                return Arrays.stream(QoraqalpogistonDistrict.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            default:
                return Collections.emptyList();
        }
    }
}
