package org.kodluyoruz.mybank.generate.accountgenerate;

import java.util.Random;
import java.util.function.Supplier;

public class AccountGenerate {
    public static Supplier<String> accountGenerate = () -> {
        Random rand = new Random();
        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append("8500"); //i<14
        for (int i=0;i<12;i++) {
            Integer value = rand.nextInt(10);
            accountNumber.append(value);
        }
        return accountNumber.toString();
    };
}
