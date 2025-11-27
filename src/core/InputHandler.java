package core;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    public String nextString() {
        return scanner.nextLine().trim();
    }

    public int nextInt() {
        while (!scanner.hasNextInt()) scanner.next();
        int v = scanner.nextInt();
        scanner.nextLine();
        return v;
    }

    public int nextIntRange(int min, int max) {
        int v = nextInt();
        return (v < min || v > max) ? min : v;
    }

    public int menuChoice(int size) {
        int c = nextInt();
        return (c >= 1 && c <= size) ? c : -1;
    }
}