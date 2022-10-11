import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();
        System.out.println(calc(inputText));
    }

    public static String calc(String input) throws Exception{

        if ("".equals(input)) {
            throw new Exception();
        }

        String[] operands = input.split(" ");

        if (operands.length != 3 || !"+-*/".contains(operands[1])) {
            throw new Exception();
        }

        int firstNumber = -1;
        int secondNumber = -1;
        boolean isRomanian = false;

        if (isArabianNumber(operands[0]) && isArabianNumber(operands[2])) {
            firstNumber = Integer.parseInt(operands[0]);
            secondNumber = Integer.parseInt(operands[2]);
        } else if (isRomanianNumber(operands[0]) && isRomanianNumber(operands[2])) {
            isRomanian = true;
            firstNumber = RomanNumbers.toArabianNumber(operands[0]);
            secondNumber = RomanNumbers.toArabianNumber(operands[2]);
        } else {
            throw new Exception();
        }

        if (!isCorrectNumber(firstNumber) || !isCorrectNumber(secondNumber)) {
            throw new Exception();
        }

        int result;

        switch (operands[1]) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            default:
                result = firstNumber / secondNumber;
        }

        if (isRomanian && result <= 0) {
            throw new Exception();
        }

        if (isRomanian) {
            return RomanNumbers.toRomanianNumber(result);
        } else {
            return String.valueOf(result);
        }
    }

    private static boolean isArabianNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isRomanianNumber(String input) {
        return RomanNumbers.toArabianNumber(input) != -1;
    }

    private static boolean isCorrectNumber(int number) {
        return number >= 1 && number <= 10;
    }
}
