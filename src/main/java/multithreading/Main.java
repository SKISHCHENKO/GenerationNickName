package multithreading;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    public static AtomicInteger countThree = new AtomicInteger(0);
    public static AtomicInteger countFour = new AtomicInteger(0);
    public static AtomicInteger countFive = new AtomicInteger(0);

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        Runnable isPalindromeLogic = () -> {
            for (String text : texts) {
                if (isPalindrome(text) && text.length() == 3) {
                    countThree.getAndIncrement();
                }
                if (isPalindrome(text) && text.length() == 4) {
                    countFour.getAndIncrement();
                }
                if (isPalindrome(text) && text.length() == 5) {
                    countFive.getAndIncrement();
                }
            }
        };

        Runnable isSingleCharLogic = () -> {
            for (String text : texts) {
                if (isSingleChar(text) && text.length() == 3) {
                    countThree.getAndIncrement();
                }
                if (isSingleChar(text) && text.length() == 4) {
                    countFour.getAndIncrement();
                }
                if (isSingleChar(text) && text.length() == 5) {
                    countFive.getAndIncrement();
                }
            }
        };

        Runnable isSortedLogic = () -> {
            for (String text : texts) {
                if (isSorted(text) && text.length() == 3) {
                    countThree.getAndIncrement();
                }
                if (isSorted(text) && text.length() == 4) {
                    countFour.getAndIncrement();
                }
                if (isSorted(text) && text.length() == 5) {
                    countFive.getAndIncrement();
                }
            }
        };

        Thread thread1 = new Thread(isPalindromeLogic);
        Thread thread2 = new Thread(isSingleCharLogic);
        Thread thread3 = new Thread(isSortedLogic);
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Красивых слов с длиной 3: " + countThree + " шт.");
        System.out.println("Красивых слов с длиной 4: " + countFour + " шт.");
        System.out.println("Красивых слов с длиной 5: " + countFive + " шт.");
    }

    public static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isSingleChar(String text) {
        char firstChar = text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) != firstChar) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSorted(String text) {
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) < text.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}