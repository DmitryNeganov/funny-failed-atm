package org.dvn.bankomat;

import java.util.HashMap;
import java.util.Map;

public class StubSdk implements Sdk {

    private final Map<Integer, Integer> storage;

    public StubSdk(Map<Integer, Integer> storage) {
        this.storage = storage;
    }


    @Override
    public int countBanknotes(int banknote) {
        return storage.get(banknote);
    }

    @Override
    public void moveBanknoteToDispenser(int banknote, int count) {
        storage.replace(banknote, storage.get(banknote) - count);
        System.out.printf("Перемещаю купюру %s в лоток выдачи, %s штук%n", banknote, count);
    }

    @Override
    public int countBanknotesCurrency(int banknote, Currency currency) {
        return 0;
    }

    @Override
    public void moveBanknoteToDispenser(int banknote, int count, Currency currency) {

    }

    @Override
    public void openDispenser() {
        System.out.printf("Лоток выдачи открыт пользователю%n");
    }
}