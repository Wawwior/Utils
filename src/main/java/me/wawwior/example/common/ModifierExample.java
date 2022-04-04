package me.wawwior.example.common;

import me.wawwior.utils.common.Modifier;

public class ModifierExample {

    public static void main(String[] args) {

        Modifier<String> stringMod = s -> String.format(".-x:#X %s X#:x-.", s);

        System.out.println(stringMod.modify("Foo"));
        System.out.println(stringMod.modify("Bar"));

        Modifier<Integer> intMod = i -> (int) Math.sqrt(i) * i;

        System.out.println(intMod.modify(25));
        System.out.println(intMod.modify(9));

        Modifier<Double> sigmoid = d -> 1 / (1 + Math.pow(Math.E, -d));

        System.out.println(sigmoid.modify(36.75));
        System.out.println(sigmoid.modify(2.75));
        System.out.println(sigmoid.modify(6.2));

    }

}

