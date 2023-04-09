package by.teachmeskills.penkovsky.homework17;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ChatTest {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    public static void main(String[] args) {
        RateLimitingProperties userMessagesRateLimiting = new RateLimitingProperties(Duration.ofSeconds(20), 10);
        ChatService chatService = new ChatService(userMessagesRateLimiting);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line.equals("история")) {
                for (Message message : chatService.getAll()) {
                    System.out.println(formatMessage(message));
                }
            } else if (line.matches("\\w+: .*")) {
                String[] parts = line.split(": ", 2);
                String username = parts[0];
                String text = parts[1];
                User user = new User(username);
                if (chatService.sendMessage(user, text)) {
                    System.out.println("Сообщение отправлено");
                } else {
                    System.out.println("Слишком частые запросы");
                }
            } else {
                System.out.println("Некорректный ввод");
            }
        }
    }
    private static String formatMessage(Message message) {
        String author = message.getAuthor() != null ? message.getAuthor().getLogin() : "null";
        String text = message.getContent();
        Instant createdInstant = message.getTimestamp();
        String createdString = DATE_FORMATTER.format(createdInstant);
        return String.format("[%s] %s: %s", createdString, author, text);
    }
}
