package com.example.kafka;

import com.github.javafaker.Faker;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FakeMessage {
    private static final List<String> menuNames = Arrays.asList("갈비탕", "라멘",
            "우동", "짬뽕", "쌀국수");
    private static final List<String> Shop = Arrays.asList("A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1",
            "I1", "J1", "K1", "L1", "M1", "N1", "O1", "P1", "Q1");
    private String getRandomValueFromList(List<String> list, Random random) {
        int size = list.size();
        int index = random.nextInt(size);

        return list.get(index);
    }

    public HashMap<String, String> produce_msg(Faker faker, Random random, int id) {
        String shopId = getRandomValueFromList(Shop, random);
        String menuName = getRandomValueFromList(menuNames, random);
        String ordId = "ord-"+id;
        String customerName = faker.name().fullName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        String address = faker.address().streetAddress();
        LocalDateTime now = LocalDateTime.now();
        String message = String.format("order_id:%s, shop:%s, menu_name:%s, customer_name:%s, phone_number:%s, address:%s, time:%s"
                , ordId, shopId, menuName, customerName, phoneNumber, address
                , now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.KOREAN)));
        HashMap<String, String> messageMap = new HashMap<>();
        messageMap.put("key", shopId);
        messageMap.put("message", message);

        return messageMap;
    }

    public static void main(String[] args) {
        FakeMessage FakeMessage = new FakeMessage();
        long seed = 2022;
        Random random = new Random(seed);
        Faker faker = new Faker(Locale.KOREAN);

        for(int i=0; i < 60; i++) {
            HashMap<String, String> message = FakeMessage.produce_msg(faker, random, i);
            System.out.println("key:"+ message.get("key") + " message:" + message.get("message"));
        }
    }
}
