package org.dvn.bankomat;

import java.util.HashMap;
import java.util.Map;

public class ATM {
    public int getTotalSumRub() {
        return totalSumRub;
    }

    private final Sdk sdk;
    private final Map<Integer, Integer> storageRub = new HashMap<>();
    private int totalSumRub;
    private final Map<Integer, Integer> storageUsd = new HashMap<>();
    private int totalSumUsd;
    private final Map<Integer, Integer> moveToDispenser = new HashMap<>();

    public ATM(Sdk sdk) {
        this.sdk = sdk;

        storageRub.put(50, sdk.countBanknotes(50));
        storageRub.put(100, sdk.countBanknotes(100));
        storageRub.put(500, sdk.countBanknotes(500));
        storageRub.put(1000, sdk.countBanknotes(1000));
        storageRub.put(5000, sdk.countBanknotes(5000));

        this.totalSumRub = 0;
        for (int i: storageRub.keySet()) {
            totalSumRub += i * storageRub.get(i);
        }

        storageUsd.put(5, sdk.countBanknotes(50));
        storageUsd.put(10, sdk.countBanknotes(100));
        storageUsd.put(20, sdk.countBanknotes(500));
        storageUsd.put(100, sdk.countBanknotes(1000));

        this.totalSumUsd = 0;
        for (int i: storageUsd.keySet()) {
            totalSumUsd += i * storageUsd.get(i);
        }

    }

    public void getMoney(int sum) throws Exception {

        if (sum > totalSumRub) {
            throw new Exception("Не достаточно деняк");
        }

        sum = getBanknotes(5000, sum);
        sum = getBanknotes(1000, sum);
        sum = getBanknotes(500, sum);
        sum = getBanknotes(100, sum);
        sum = getBanknotes(50, sum);

        if (sum > 0) {
            throw new Exception("Нет подходящих номиналов");
        }

        for (int i: moveToDispenser.keySet()) {
            sdk.moveBanknoteToDispenser(i, moveToDispenser.get(i));
        }

        totalSumRub -= sum;
        sdk.openDispenser();
    }

    public void getMoneyCurrency(int sum, Currency currency) throws Exception {

        int total;

        if (currency == Currency.USD) {
            total = totalSumUsd;
        } else if (currency == Currency.RUB) {
            total = totalSumRub;
        } else {
            throw new Exception("неправильная валюта");
        }

        if (sum > totalSumRub) {
            throw new Exception("Не достаточно деняк");
        }

        sum = getBanknotes(5000, sum);
        sum = getBanknotes(1000, sum);
        sum = getBanknotes(500, sum);
        sum = getBanknotes(100, sum);
        sum = getBanknotes(50, sum);

        if (sum > 0) {
            throw new Exception("Нет подходящих номиналов");
        }

        for (int i: moveToDispenser.keySet()) {
            sdk.moveBanknoteToDispenser(i, moveToDispenser.get(i));
        }

        total -= sum;
        sdk.openDispenser();
    }

    private int getBanknotes(int banknote, int sum) {
        int needed = sum / banknote;
        int have = storageRub.get(banknote);
        int countBanknotes;
        if (have >= needed) {
            storageRub.replace(banknote, have - needed);
            countBanknotes = needed;
        } else {
            storageRub.replace(banknote, 0);
            countBanknotes = have;
        }

        moveToDispenser.put(banknote, countBanknotes);
        sum = sum - countBanknotes * banknote;

        return sum;
    }



}



// для тестов
// реализацию можно и нужно менять
