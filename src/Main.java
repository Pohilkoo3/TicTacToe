import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String regex = "[0,1,2][0,1,2]";

    public static void main(String[] args) {
        int[][] field = createField();
        do {
            field = createField();
        } while (playTicTacToe(field));
    }

    static boolean playTicTacToe(int[][] field) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Давай поиграем в крестики нолики!!!");
        Arrays.stream(field).forEach(e -> System.out.println(Arrays.toString(e)));
        outer:
        for (; ; ) {
            System.out.println("Input yor symbol");
            String symbol = scanner.nextLine();
            System.out.println("Input your location");
            String location = scanner.nextLine();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(location);
            if (!matcher.find()) {
                System.out.println("Input wrong location yor symbol. Try again.");
                continue;
            }
            field[Integer.parseInt(location.substring(0, 1))][Integer.parseInt(location.substring(1))] = (symbol.charAt(0) == 'x' || symbol.charAt(0) == 'X' ? 1 : 0);
            Arrays.stream(field).forEach(e -> System.out.println(Arrays.toString(e)));
            int result = checkResult(field);
            switch (result) {
                case 0:
                    System.out.println("\n CONGRATULATIONS !!!!!!!\nWin 0");
                    break outer;
                case 3:
                    System.out.println("\nCONGRATULATIONS !!!!!!!\nWin x");
                    break outer;
            }
        }
        System.out.println("Do you want play again? If yo want - press 1. No - press 0.");
        return scanner.nextInt() != 0;
    }

    static int checkResult(int[][] field) {
        for (int i = 0; i < 3; i++) {
            int sumInString = 0;
            int sumInColumn = 0;
            int sumDiagonal1 = field[0][0] + field[1][1] + field[2][2];
            int sumDiagonal2 = field[0][2] + field[1][1] + field[2][0];
            for (int j = 0; j < 3; j++) {
                sumInString += field[i][j];
                sumInColumn += field[j][i];
            }
            if (sumInString == 0 || sumInColumn == 0 || sumDiagonal1 == 0 || sumDiagonal2 == 0) {
                return 0;
            }
            if (sumInString == 3 || sumInColumn == 3 || sumDiagonal1 == 3 || sumDiagonal2 == 3) {
                return 3;
            }
        }
        return 8;
    }

    static int[][] createField() {
        int[][] field = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = 8;
            }
        }
        return field;
    }
}

