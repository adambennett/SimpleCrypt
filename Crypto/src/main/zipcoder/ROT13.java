package main.zipcoder;

import sun.security.tools.policytool.Resources;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

public class ROT13  {

    private int shiftAmt = 0;

    public ROT13(Character cs, Character cf) {
        int amt = cf - cs;
        if (amt < 0) { amt = -amt; }
        this.shiftAmt = amt;
    }

    public ROT13() {}


    public String crypt(String text) throws UnsupportedOperationException {
        String toRet = "";
        boolean upperCase = false;
        ArrayList<Character> newChars = new ArrayList<>();
        for (char c : text.toCharArray()) {
            Character ref = c;
            if (Character.isAlphabetic(ref)) {
                upperCase = Character.isUpperCase(ref);
                int shifting = this.shiftAmt;
                int newC = c;
                while (shifting > 0) {
                    newC++;
                    shifting--;
                    if (upperCase && newC > 90) {
                        newC = 65;
                    } else if (newC > 122) {
                        newC = 97;
                    }
                }
                char newChar = (char)newC;
                newChars.add(newChar);
            } else {
                newChars.add(c);
            }
        }
        for (Character ch : newChars) {
            toRet += ch;
        }
        return toRet;
    }

    public String encrypt(String text) {
        return crypt(text);
    }

    public String decrypt(String text) {
        return crypt(text);
    }

    public static String rotate(String s, Character c) {
        String toRet = "";
        int index = 0;
        ArrayList<Character> chars = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            chars.add(ch);
        }
        boolean hasFound = false;
        int foundIndex = 0;
        for (;index < chars.size(); index++) {
            if (chars.get(index).equals(c) || hasFound) {
                toRet += chars.get(index);
                if (!hasFound) { foundIndex = index; }
                hasFound = true;
            }
        }
        for (int i = 0; i < foundIndex; i++) {
            if (i < chars.size()) {
                toRet += chars.get(i);
            }
        }
        return toRet;
    }

    public String readIn(String fileName) {
        String fullInput = "";
        Class clazz = ROT13.class;
        InputStream stream = clazz.getResourceAsStream("/" + fileName);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = br.readLine()) != null) {
                fullInput += line + "\n";
            }
        } catch (IOException e) {}
        return fullInput;
    }

    public void writeTo(String fileName, String toWrite) {

        try {
            PrintWriter writer =
                    new PrintWriter(
                            new File(fileName));
            writer.write(toWrite);
            writer.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

}
