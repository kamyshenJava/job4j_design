package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        List<String> answers = this.readPhrases();
        List<String> log = new ArrayList<>();
        String welcome = "Добро пожаловать в чат с самым тупым ботом в мире!";
        log.add(welcome);
        System.out.println(welcome);
        String question = "";
        boolean shouldAnswer = true;
        Random random = new Random();
        while (!OUT.equals(question)) {
            question = input.nextLine();
            log.add(question);
            if (STOP.equals(question) || OUT.equals(question)) {
                shouldAnswer = false;
                continue;
            }
            if (CONTINUE.equals(question)) {
                shouldAnswer = true;
            }
            if (shouldAnswer) {
                String answer = answers.get(random.nextInt(answers.size()));
                log.add(answer);
                System.out.println(answer);
            }
        }
        String farewell = "Спасибо за общение! До встречи!";
        log.add(farewell);
        this.saveLog(log);
        System.out.println(farewell);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            br.lines().forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ConsoleChat cc = new ConsoleChat("./log_chat_with_bot.txt", "./bot_answers.txt");
        cc.run();
    }
}
