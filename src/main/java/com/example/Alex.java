package com.example;

import java.util.List;

public class Alex extends Lion {
    private static final List<String> FRIENDS = List.of("Марти", "Глория", "Мелман");
    private static final String HOME = "Нью-Йоркский зоопарк";

    public Alex(Feline feline) throws Exception {
        super("Самец", feline);
    }

    public List<String> getFriends() {
        return FRIENDS;
    }

    public String getPlaceOfLiving() {
        return HOME;
    }

    @Override
    public int getKittens() {
        return 0;
    }
}