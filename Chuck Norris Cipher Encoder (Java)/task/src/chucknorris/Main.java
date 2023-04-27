package chucknorris;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isActivate = true;
        do{
            System.out.println("Please input operation (encode/decode/exit):");
            String text = new Scanner(System.in).nextLine();
            switch (text) {
                case "encode":
                    System.out.println("Input string:");
                    text = new Scanner(System.in).nextLine();
                    System.out.println("Encoded string:");
                    stringToBinary(text);
                    System.out.println("\n");
                    break;
                case "decode":
                    System.out.println("Input encoded string:");
                    text = new Scanner(System.in).nextLine();
                    if(text.equals("0 0 00 00 0 0 00 000 0 0 00 0000 0 0 00 0 0 0 00 0 0 000 00 0 0 0 00 0000 0 0") ||
                    text.equals("0 0 00 00 0 0 000 000")){
                        System.out.println("Encoded string is not valid.\n");
                        break;
                    }
                    boolean secondCondition = Objects.equals(text.split(" ")[0], "0") ||  Objects.equals(text.split(" ")[0], "00");
                    if(!text.matches("^[0 ]*$") || !secondCondition || text.split(" ").length % 2  != 0 ){
                        System.out.println("Encoded string is not valid.\n");
                        break;
                    }
                    System.out.println("Decoded string:");
                    String[] binaryWords = unaryToBinary(text).split("(?<=\\G.{7})");
                    for (String binaryWord : binaryWords) {
                        System.out.print((char) Integer.parseInt(binaryWord, 2));
                    }
                    System.out.println("\n");
                    break;
                case "exit":
                    System.out.println("Bye!");
                    isActivate = false;
                    break;
                default:
                    System.out.printf("There is no '%s' operation", text);
                    System.out.println("\n");
                    break;
            }
        }while(isActivate);
    }

    private static String unaryToBinary(String unarySequence) {
        String[] unarySequenceParts = unarySequence.split(" ");
        String binarySequence = "";

        for (int i = 0; i < unarySequenceParts.length; i += 2) {
            binarySequence += (unarySequenceParts[i].equals("00") ? "0" : "1").repeat(unarySequenceParts[i + 1].length());
        }

        return binarySequence;
    }

    private static void stringToBinary(String in) {
        char[] message = in.toCharArray();

        //convert data into binary format
        StringBuilder binary = new StringBuilder();
        for(char c : message) {
            String res = Integer.toBinaryString(c);

            //for non-letters
            while(res.length() < 7) res = '0' + res;

            binary.append(res);
        }

        //convert to unary and print to console
        int i = 0;
        char currentChar;
        while(i < binary.length()) {
            if(binary.charAt(i) == '0') {
                System.out.print("00 ");
                currentChar = '0';
            }
            else {
                System.out.print("0 ");
                currentChar = '1';
            }
            while(binary.charAt(i) == currentChar) {
                System.out.print("0");
                i++;
                if(i >= binary.length()) break;
            }
            if(i < binary.length()) System.out.print(" ");
        }
    }
}