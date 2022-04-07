import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {

        String signX = "X";
        String sign0 = "0";
        boolean isStopGame = false;
        boolean gameMod = false;
        String[][] gameMatrix = initGameMatrix();

        welcomePack(gameMatrix);

        switchGameMod(gameMod, gameMatrix, signX, sign0, isStopGame);


    }
    // приветствия

    public static void welcomePack(String[][] gameMatrix) {

        System.out.println("Привет! Перед вами поле для игры в Крестики-Нолики. У вас есть возможность сыграть с другом или против компьютера");
        System.out.println("Для начала выберите режим игры, затем поочередно проставляйте свой символ, указывая координаты(они начинаются с нуля, пример: \"0 1\")");
        System.out.println("Первыми ходят крестики. Приятной игры!");

        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix.length; j++) {
                System.out.print("| " + gameMatrix[i][j] + " |");
            }
            System.out.println();
        }
    }
    // переключаем процесс с пвп на пве и играем до тех пор, пока не захотим выйти
    public static void switchGameMod(boolean gameMod, String[][] gameMatrix, String signX, String sign0, boolean isStopGame) {

        while (!isStopGame) {
            gameMod = pvpOrNo();

            if (gameMod) {

                gameMatrix = initGameMatrix();
                gamePVP(gameMatrix, signX, sign0);

            } else {

                gameMatrix = initGameMatrix();
                gamePVE(gameMatrix, signX, sign0);

            }
            isStopGame = isStopGame();
        }
    }

    public static void gamePVP(String[][] gameMatrix, String signX, String sign0) {

        while (true) {

            player1Move(gameMatrix, signX);

            if (hasWinner(gameMatrix, signX)) {

                break;
            }
            if (!hasEmptyCell(gameMatrix)) {

                System.out.println("Ничья!");

                break;
            }

            player2Move(gameMatrix, sign0);

            if (hasWinner(gameMatrix, sign0)) {
                break;
            }

            if (!hasEmptyCell(gameMatrix)) {

                System.out.println("Ничья!");

                break;
            }

        }

    }
    // игровой процесс с пк
    public static void gamePVE(String[][] gameMatrix, String signX, String sign0) {

        while (true) {

            player1Move(gameMatrix, signX);

            if (hasWinner(gameMatrix, signX)) {

                break;
            }
            if (!hasEmptyCell(gameMatrix)) {

                System.out.println("Ничья!");

                break;
            }

            pcPlayerMove(gameMatrix, sign0);

            if (hasWinner(gameMatrix, sign0)) {
                break;
            }

            if (!hasEmptyCell(gameMatrix)) {

                System.out.println("Ничья!");

                break;
            }

        }
    }

    //
    public static String getGameMod() {


        Scanner scanner = new Scanner(System.in);

        String gameMod;

        while (true) {
            System.out.println("Укажите режим игры, для игры с другом, напишите PVP, с компьютером - PVE. Буду требовать до тех пор, пока не введете корректно:");

            gameMod = scanner.nextLine();

            if (gameMod.equalsIgnoreCase("PVP") || gameMod.equalsIgnoreCase("PVE")) {
                break;
            }
        }
        return gameMod;

    }

    public static boolean pvpOrNo() {

        String gameMod = getGameMod();

        if (gameMod.equalsIgnoreCase("PVP")) {

            return true;
        }
        return false;
    }
    // принимаем координаты в виде инт массива, сразу отсеивая чепуху в виде букв
    public static int[] getPlayersCoordinates() {

        System.out.println("вводи цифрами координаты квадрата 3x3, куда необходимо разместить символ:");

        int[] userValueArray = new int[2];

        checkTheInputToNumbers(userValueArray);

        return userValueArray;

    }


    public static void printPlayersSymbol(String[][] gameMatrix) {

        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix.length; j++) {

                System.out.print("| " + gameMatrix[i][j] + " |");
            }

            System.out.println();

        }
    }

    // ход 1 игрока
    public static void player1Move(String[][] gameMatrix, String signX) {
        int i;
        int j;

        do {
            System.out.print("Первый игрок ");
            int[] arrayValue = getPlayersCoordinates();

            i = arrayValue[0];
            j = arrayValue[1];

        } while (!isValidCell(gameMatrix, i, j));

        gameMatrix[i][j] = signX;

        printPlayersSymbol(gameMatrix);


    }

    //ход второго игрока
    public static void player2Move(String[][] gameMatrix, String sign0) {
        int i;
        int j;

        do {
            System.out.print("Второй игрок ");
            int[] arrayValue = getPlayersCoordinates();

            i = arrayValue[0];
            j = arrayValue[1];

        } while (!isValidCell(gameMatrix, i, j));

        gameMatrix[i][j] = sign0;

        printPlayersSymbol(gameMatrix);

    }
     // ход ПК, проверки более простые, тк пк у нас тупенький и генерит все в рамках границ поля
    public static void pcPlayerMove(String[][] gameMatrix, String sign0) {
        int i;
        int j;

        do {

            i = (int) (Math.random() * 3);
            j = (int) (Math.random() * 3);

        } while (!isFreeCellPC(gameMatrix, i, j));

        gameMatrix[i][j] = sign0;

        System.out.println("Компьютер разместил символ:");

        printPlayersSymbol(gameMatrix);
    }

    // проверка на то, что полученные координаты в рамках поля и что клетка по координатам свободна
    public static boolean isValidCell(String[][] gameMatrix, int i, int j) {

        if (i < 0 || i > 2 || j < 0 || j > 2) {

            System.out.println("Введены координаты, выходящие за рамки координат поля!");

            return false;

        } else if (gameMatrix[i][j].equalsIgnoreCase("X") || gameMatrix[i][j].equalsIgnoreCase("0")) {

            System.out.println("Клетка занята!");

            return false;

        }

        return true;
    }
   // простая проверка на пустую клетку для пк
    public static boolean isFreeCellPC(String[][] gameMatrix, int i, int j) {

        if (gameMatrix[i][j].equalsIgnoreCase("X") || gameMatrix[i][j].equalsIgnoreCase("0")) {

            return false;

        }
        return true;
    }


    public static int[] checkTheInputToNumbers(int[] userValueArray) {

        boolean check = false;
        Scanner scanner = new Scanner(System.in);

        while (!check) {
            if (scanner.hasNextInt()) {
                for (int i = 0; i < userValueArray.length; i++) {
                    userValueArray[i] = scanner.nextInt();
                }
                check = true;

            } else {
                System.out.println("Допускается только ввод цифр, повторите:");
                scanner.next();
                check = false;
            }
        }
        return userValueArray;
    }

    public static String[][] initGameMatrix() {

        String[][] gameMatrix = new String[3][3];

        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix.length; j++) {

                gameMatrix[i][j] = "_";
            }
        }
        return gameMatrix;

    }
    // к сожалению, пока только такое примитивнейшее сравнение на победу. В свободное время проведу рефакторинг
    public static boolean hasWinner(String[][] gameMatrix, String symbol) {

        for (int i = 0; i < 3; i++) {

            if ((gameMatrix[i][0].equals(symbol) || gameMatrix[i][0].equals(symbol)) && (gameMatrix[i][1].equals(symbol) ||
                    gameMatrix[i][1].equals(symbol)) && (gameMatrix[i][2].equals(symbol) || gameMatrix[i][2].equals(symbol)) ||
                    ((gameMatrix[0][i].equals(symbol) || gameMatrix[0][i].equals(symbol)) && (gameMatrix[1][i].equals(symbol) ||
                            gameMatrix[1][i].equals(symbol)) && (gameMatrix[2][i].equals(symbol) || gameMatrix[2][i].equals(symbol)))) {

                System.out.println("Победил, красава )))");

                return true;
            }

        }

        if ((gameMatrix[0][0].equals(symbol) || gameMatrix[0][0].equals(symbol)) && (gameMatrix[1][1].equals(symbol) ||
                gameMatrix[1][1].equals(symbol)) && (gameMatrix[2][2].equals(symbol) || gameMatrix[2][2].equals(symbol)) ||
                ((gameMatrix[2][0].equals(symbol) || gameMatrix[2][0].equals(symbol)) && (gameMatrix[1][1].equals(symbol) ||
                        gameMatrix[1][1].equals(symbol)) && (gameMatrix[0][2].equals(symbol) || gameMatrix[0][2].equals(symbol)))) {

            System.out.println("Победил, красава )))");

            return true;
        }


        return false;
    }

   // проверка на ничью, в случае, если пустых клеток не осталось
    public static boolean hasEmptyCell(String[][] gameMatrix) {

        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix.length; j++) {

                if (gameMatrix[i][j].equals("_")) {
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean isStopGame() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Если наигрались, напишите слово \"выйти\", для продолжения жмем любую клавишу:");
        String result = scanner.nextLine();

        if (result.equalsIgnoreCase("выйти")) {

            return true;

        }

        return false;
    }

}



