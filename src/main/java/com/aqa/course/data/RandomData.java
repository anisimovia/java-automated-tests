package com.aqa.course.data;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomData {
    private static final int DEFAULT_TITLE_LENGTH = 10;
    private static final int DEFAULT_BODY_LENGTH = 30;
    private static final int DEFAULT_ID_LENGTH = 3;

    public String getTitle() {
        return RandomStringUtils.insecure().nextAlphabetic(DEFAULT_TITLE_LENGTH);
    }

    public String getBody() {
        return RandomStringUtils.insecure().nextAlphabetic(DEFAULT_BODY_LENGTH);
    }

    public String getID() {
        return RandomStringUtils.insecure().nextNumeric(DEFAULT_ID_LENGTH);
    }

    public String getFullName() {
        // Генерируем имя: "Иван" + 5 случайных букв
        return "Ivan " + RandomStringUtils.insecure().nextAlphabetic(5);
    }

    public String getEmail() {
        // Генерируем email: 8 случайных букв + @example.com
        return RandomStringUtils.insecure().nextAlphabetic(8).toLowerCase() + "@example.com";
    }

    public String getAddress() {
        // Генерируем адрес: случайное число + улица
        int houseNumber = new Random().nextInt(100);
        return "Test Street, building " + houseNumber;
    }

        //методы под Practice Form
        public String getFirstName() {
            return RandomStringUtils.insecure().nextAlphabetic(6);
        }

    public String getLastName() {
        return RandomStringUtils.insecure().nextAlphabetic(8);
    }

    public String getMobile() {
        // ровно 10 цифр, первая не 0
        return "9" + RandomStringUtils.insecure().nextNumeric(9);
    }

    public String getDateOfBirth() {
        // фиксированная дата — так надёжнее, чем random
        return "10 May 1990";
    }

    //Создаём тестовую картинку для формы
    public static String createTestPicture() {
        try {
            java.nio.file.Path path = java.nio.file.Paths.get(
                    "src/test/resources/test-picture.png"
            );
            java.nio.file.Files.createDirectories(path.getParent());

            // Минимальный валидный PNG-файл (1x1 пиксель, прозрачный)
            byte[] pngBytes = new byte[] {
                    (byte)0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A,
                    0x00, 0x00, 0x00, 0x0D, 0x49, 0x48, 0x44, 0x52,
                    0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x01,
                    0x08, 0x06, 0x00, 0x00, 0x00, 0x1F, 0x15, (byte)0xC4,
                    (byte)0x89, 0x00, 0x00, 0x00, 0x0A, 0x49, 0x44,
                    0x41, 0x54, 0x78, (byte)0x9C, 0x63, 0x00, 0x01,
                    0x00, 0x00, 0x05, 0x00, 0x01, 0x0D, 0x0A, 0x2D,
                    (byte)0xB4, 0x00, 0x00, 0x00, 0x00, 0x49, 0x45,
                    0x4E, 0x44, (byte)0xAE, 0x42, 0x60, (byte)0x82
            };
            java.nio.file.Files.write(path, pngBytes);
            return path.toAbsolutePath().toString();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать тестовую картинку", e);
        }
    }

    //Утилита для генерации пользователя для BookStore
    public String getUserName() {
        return "user_" + RandomStringUtils.insecure().nextAlphabetic(8).toLowerCase();
    }

    public String getPassword() {
        // Пароль должен содержать цифры, буквы, спецсимволы (по требованиям demoqa)
        return "Pass@" + RandomStringUtils.insecure().nextAlphanumeric(6) + "!";
    }
}
