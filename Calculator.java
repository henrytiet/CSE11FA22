/**
Name: Henry Tiet
Email: htiet@ucsd.edu
PID: A16957740
Sources Used: None

This file is my own attempt at operating on decimals. 
Inputs of decimals will be in the form of Strings for this purpose.
With the input of Decimals, it can be easier to modify and compare
characters/digits as there are varying lengths of decimals. */

public class Calculator {
    public static void main(String[] args) {    //testers
        String test1=extractWholeNumber("01.280");
        System.out.println(test1);

        String test2=extractDecimal("1.0002200");
        System.out.println(test2);

        String test3=prependZeros("123.456", 4);
        System.out.println(test3);
        test3=appendZeros(test3, 2);
        System.out.println(test3);

        String test4=stripZeros("000123.45600000");
        String test5=stripZeros("0000123000");
        System.out.println(test4 + " " + test5);
        
    }

    public static String extractWholeNumber(String number) {
        int charDec=-1;
        for(int i=0; i<number.length(); i++) {  //finding char of "."
            if(number.charAt(i)=='.') {
                charDec=i;
            }
        }
        if(charDec==-1) { //if input is a whole number with no decimal
            return number;
        }
        if(charDec==0) {
            return "0";
        }
        else {
           return number.substring(0, charDec);
        }
    }
    
    public static String extractDecimal(String number) {
        int charDec=-1;
        for(int i=0; i<number.length(); i++) {  //finding char of "."
            if(number.charAt(i)=='.') {
                charDec=i;
            }
        }
        if(charDec==-1 || charDec==number.length()-1) { //error case if input
            return "0";                    // is a whole number with no decimal
        }
        else {
            return number.substring(charDec, number.length());
        }
    }

    public static String prependZeros(String number, int numZeros) {
        if(numZeros<0) {    //error case
            return number;
        }
        for(int i=0; i<numZeros; i++) {
            number="0"+number;
        }
        return number;
    }

    public static String appendZeros(String number, int numZeros) {
        if(numZeros<0) {    //error case
            return number;
        }
        for(int i=0; i<numZeros; i++) {
            number=number+"0";
        }
        return number;
    }

    public static String stripZeros(String number) {
        int firstDigit=-1;
        int lastDigit=-1;
        int charDec=-1;
        for(int i=0; i<number.length(); i++) {  //finding char of "."
            if(number.charAt(i)=='.') {
                charDec=i;
            }
        }
        for(int i=0; i<number.length(); i++) {  //finding first digit
            if(number.charAt(i)!='0') {
                firstDigit=i;
                break;
            }
        }
        if(charDec==-1) {   //if no decimal present
            return number.substring(firstDigit, number.length());
        }
        else {
            for(int i=number.length()-1; i>=0; i--) {   //finiding last digit
                if(number.charAt(i)!='0') {
                    lastDigit=i;
                    break;
                }
            }
            return number.substring(firstDigit, lastDigit+1);
        }
    }


}