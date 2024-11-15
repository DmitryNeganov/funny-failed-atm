package org.dvn;

import org.dvn.bankomat.ATM;
import org.dvn.bankomat.StubSdk;

import java.util.HashMap;
import java.util.Map;

public class Main {



    public static void main(String[] args) {

//        Map<Integer, Integer> mapa = new HashMap<>();
//        mapa.put(50, 2);
//        mapa.put(100, 3);
//        mapa.put(500, 4);
//        mapa.put(1000, 5);
//        mapa.put(5000, 2);
//
//        StubSdk stubSdk = new StubSdk(mapa);
//        ATM atm = new ATM(stubSdk);
//
//        System.out.println(atm.getTotalSum());
//
//        try {
//            atm.getMoney(6000);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        Map<Integer, Integer> mapa = new HashMap<>();
        mapa.put(50, 0);
        mapa.put(100, 0);
        mapa.put(500, 0);
        mapa.put(1000, 5);
        mapa.put(5000, 0);

        StubSdk stubSdk = new StubSdk(mapa);
        ATM atm = new ATM(stubSdk);

        System.out.println(atm.getTotalSumRub());

        try {
            atm.getMoney(6000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}