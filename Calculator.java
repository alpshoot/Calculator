import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
        System.out.println(calc(input));
}


    public static String calc(String input) throws Exception {
        int number1;
        int number2;
        String operation;
        String result;
        boolean romanNumber;

        String[] operands = input.split("[+\\-*/]"); //деление строки  из 2 значений арифметическими знаками +-*/
        if(operands.length ==1)throw new Exception( "Строка не является математической операцией");     //проверка что не введено только одно значение
        if(operands.length !=2)throw new Exception( "Должно быть только два числа");    //проверка что введено только 2 значения и не более

        operation = OperationTest(input);   //метод сравнивает какой знак делит значения и возвращает этот знак

        //если числа римские
        if(Roman.thisRom(operands[0])&& Roman.thisRom(operands[1])){      //метод проверяет какое число введено римсое или арабское
            //конвертируем числа в арабские
            number1 = Roman.convertToArabian(operands[0]);
            number2 = Roman.convertToArabian(operands[1]);
            romanNumber = true;
        }

        //если числа арабские
        else if (!Roman.thisRom(operands[0])&& !Roman.thisRom(operands[1])){     //метод проверяет что введено не римсое число
            number1 = Integer.parseInt(operands[0]);     //конвертируем числа из типа String в int
            number2 = Integer.parseInt(operands[1]);
            romanNumber = false;
        }
        //если одно число римское, другое арабское
        else {
            throw new Exception("Числа должны быть в одной системе счисления");
        }


        if(number1 <=0 || number2 <=0){
            throw new Exception("Числа должны быть больше 0");
        }
        //если числа больше 10
        if(number1 >10 || number2 >10){
            throw new Exception("Числа должны быть от 1 до 10");
        }

        int arab = calculation(number1, number2, operation);     //метод проверяет знак действия и возвращает математическое действие


        if(romanNumber){
            //если римское число меньше либо равно нулю
            if(arab <=0){
                throw new Exception("В римской системе счисления нет отрицательных чисел");
            }
            //конвертируем результат из арабское в римское
            result = Roman.convertToRoman(arab);
        }
        else{
            //конвертируем арабское число из int в тип String
            result = String.valueOf(arab);
        }
        return result;
    }
    public static int calculation(int a, int b, String operation){
        switch (operation) {
            case "+" -> {
                return a+b;
            }
            case "-" -> {
                return a-b;
            }
            case "*" -> {
                return a*b;
            }
            case "/" -> {
                return a/b;
            }
            default -> {
            }
        }
        return 0;
    }

    public static String OperationTest(String example){

        if(example.contains("+"))
            return "+";
        else if(example.contains("-"))
            return "-";
        else if(example.contains("*"))
            return "*";
        else if(example.contains("/"))
            return "/";
        else return null;
    }
}

class Roman {
    static String[]romArray = new String[]{
            "0","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "IVX", "XV",
            "XVI", "", "XVIII", "","XX", "XXI", "", "","XXIV", "XXV", "", "XXVII", "XXVIII", "", "XXX", "",
            "XXXII", "", "", "XXXV", "XXXVI", "", "", "", "XL", "", "XLII",  "", "","XLV", "", "", "XLVIII", "XLIX",
            "L", "", "", "", "LIV", "LV", "LVI", "", "", "", "LX", "", "", "LXIII", "LXIV", "", "", "", "", "",
            "LXX", "", "LXXII", "", "", "", "", "", "", "", "LXXX", "LXXXI", "", "", "", "", "", "", "", "",
            "XC", "", "", "", "", "", "", "", "", "", "C"};

    public static boolean thisRom(String val){
        for (String romArray1 : romArray) {
            if (val.equals(romArray1)) {
                return true;
            }
        }
        return false;
    }
    public static int convertToArabian(String roman){
        for (int i = 0; i<romArray.length; i++){
            if (roman.equals(romArray[i])){
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian){
        return romArray[arabian];
    }
}

