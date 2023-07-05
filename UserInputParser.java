import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserInputParser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные пользователя в формате: Фамилия Имя Отчество датарождения номертелефона пол");
        String input = scanner.nextLine();

        String[] data = input.split(" ");

        
        if (data.length != 6) {
            System.out.println("Ошибка! Неверное количество данных.");
            return;
        }

        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        String dateOfBirth = data[3];
        String phoneNumber = data[4];
        String gender = data[5];

        try {
            
            if (!isValidDate(dateOfBirth)) {
                throw new InvalidDataFormatException("Дата рождения имеет неверный формат! Ожидается dd.mm.yyyy.");
            }

            if (!isValidPhoneNumber(phoneNumber)) {
                throw new InvalidDataFormatException("Номер телефона имеет неверный формат! Ожидается целое беззнаковое число.");
            }

            if (!isValidGender(gender)) {
                throw new InvalidDataFormatException("Пол может быть только 'm' или 'f'.");
            }

            
            FileWriter writer = new FileWriter(lastName + ".txt", true);
            writer.write(lastName + " " + firstName + " " + middleName + " " + dateOfBirth + " " + phoneNumber + " " + gender + "\n");
            writer.close();

            System.out.println("Данные успешно записаны в файл " + lastName + ".txt");
        } catch (InvalidDataFormatException | IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static boolean isValidDate(String date) {
        
        String regex = "\\d{2}\\.\\d{2}\\.\\d{4}";

        return date.matches(regex);
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        try {
            Long.parseLong(phoneNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidGender(String gender) {
        return gender.equals("m") || gender.equals("f");
    }

    static class InvalidDataFormatException extends Exception {
        public InvalidDataFormatException(String message) {
            super(message);
        }
    }
}
