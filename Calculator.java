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
    private static final int DIGIT0=48;
    private static final int DIGIT9=57;

    public static void main(String[] args) {    //testers
        // String test1=extractWholeNumber("01.280");
        // System.out.println(test1);

        // String test2=extractDecimal("1.0002200");
        // System.out.println(test2);

        // String test3=prependZeros("123.456", 4);
        // System.out.println(test3);
        // test3=appendZeros(test3, 2);
        // System.out.println(test3);

        // String test4=stripZeros("000123.45600000");
        // String test5=stripZeros("0000.000");
        // System.out.println(test4 + " " + test5);
        
        // System.out.println(addDigits('5', '4', false));
        // System.out.println(addDigits('5', '5', true));
        // System.out.println(carryOut('5', '7', false));

        // System.out.println(add("0", "00"));
        System.out.println(multiply("5", 120));
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
            return number.substring(charDec+1, number.length());
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
        int firstNonZero=-1;
        int lastNonZero=-1;
        int charDec=-1;
        for(int i=0; i<number.length(); i++) {  //finding char of "."
            if(number.charAt(i)=='.') {
                charDec=i;
            }
        }
        for(int i=0; i<number.length(); i++) {  //finding first digit
            if(number.charAt(0)=='.') {
                number=prependZeros(number, 1);
                firstNonZero=0;
                break;
            }
            else if(number.charAt(i)!='0' && number.charAt(i)!='.') {
                firstNonZero=i;
                break;
            }
            else if(i+1<number.length()) {
                if(number.charAt(i)=='0' && number.charAt(i+1)=='.') {
                    firstNonZero=i;
                    break;
                }
            }
        }
        for(int i=number.length()-1; i>=0; i--) {   //finding last digit
            if(number.charAt(number.length()-1)=='.') {
                number=appendZeros(number, 1);
                lastNonZero=number.length()-1;
                break;
            }
            if(number.charAt(i)!='0' && number.charAt(i)!='.') {
                lastNonZero=i;
                break;
            }
            else if(i>0) {
                if(number.charAt(i)=='0' && number.charAt(i-1)=='.') {
                    lastNonZero=i;
                    break;
                }  
            }
        }
        if(firstNonZero==-1 && lastNonZero==-1) {
            return "0";
        }
        if(charDec==-1) {   //if no decimal present
            return number.substring(firstNonZero, number.length());
        }
            return number.substring(firstNonZero, lastNonZero+1);
    }
    

    public static char addDigits(char firstDigit, char secondDigit, boolean carryIn) {
        char output=(char)(firstDigit+secondDigit-'0');
        if(carryIn) {
            output+=1;
        }
        if(output>DIGIT9) {
            output-=10;
        }
        return output;
    }

    public static boolean carryOut(char firstDigit, char secondDigit, boolean carryIn) {
        char output=(char)(firstDigit+secondDigit-'0');
        if(carryIn) {
            output+=1;
        }
        if(output>DIGIT9) {
            return true;
        }
        else {
            return false;
        }
    }

    public static String add(String firstNumber, String secondNumber) {
        firstNumber=stripZeros(firstNumber);    //stripping both numbers to simplify
        secondNumber=stripZeros(secondNumber);
        String firstWhole=extractWholeNumber(firstNumber);  //separating wholes from decimals
        String secondWhole=extractWholeNumber(secondNumber);
        String firstDecimal=extractDecimal(firstNumber);
        String secondDecimal=extractDecimal(secondNumber);
        String newWhole;
        String newDecimal;
        String output;

        if(firstWhole.length()!=secondWhole.length()) { //matching length with zeroes
            if(firstWhole.length()>secondWhole.length()) {
                secondWhole=prependZeros(secondWhole, firstWhole.length()-secondWhole.length());
            }
            else {
                firstWhole=prependZeros(firstWhole, secondWhole.length()-firstWhole.length());
            }
        }
        if(firstDecimal.length()!=secondDecimal.length()) {
            if(firstDecimal.length()>secondDecimal.length()) {
                secondDecimal=appendZeros(secondDecimal, firstDecimal.length()-secondDecimal.length());
            }
            else {
                firstDecimal=appendZeros(firstDecimal, secondDecimal.length()-firstDecimal.length());
            }
        }

        //adding decimal digits
        boolean carry=false;  //declared outside to carry over to the whole number
        newDecimal="" + addDigits(firstDecimal.charAt(firstDecimal.length()-1), secondDecimal.charAt(secondDecimal.length()-1), carry); 
        carry=carryOut(firstDecimal.charAt(firstDecimal.length()-1), secondDecimal.charAt(secondDecimal.length()-1), carry);
        for(int i=firstDecimal.length()-2; i>=0; i--) {
            newDecimal=addDigits(firstDecimal.charAt(i), secondDecimal.charAt(i), carry) + newDecimal;
            carry=carryOut(firstDecimal.charAt(i), secondDecimal.charAt(i), carry);
        }

        //adding whole digits, with carry over from last decimal operation
        newWhole="" + addDigits(firstWhole.charAt(firstWhole.length()-1), secondWhole.charAt(secondWhole.length()-1), carry);  
        carry=carryOut(firstWhole.charAt(firstWhole.length()-1), secondWhole.charAt(secondWhole.length()-1), carry);
        for(int i=firstWhole.length()-2; i>=0; i--) {
            newWhole=addDigits(firstWhole.charAt(i), secondWhole.charAt(i), carry) + newWhole;
            carry=carryOut(firstWhole.charAt(i), secondWhole.charAt(i), carry);
        }
        if(carry==true) {
            newWhole="1"+newWhole;
        }

        //appending decimal and whole together with a decimal in betweeen
        output=newWhole + "." + newDecimal;
        return output;
    }

    public static String multiply(String number, int numTimes) {
        if(numTimes<0) {
            return number;
        }
        String output="0";
        if(numTimes>0) {
            output=number;
            for(int i=1; i<numTimes; i++) {
                output=add(output, number);
            }
        }
        return output;
    }
}
