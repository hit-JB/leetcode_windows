package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class generateCharacters {
    public static void generateCharacters(OutputStream outputStream)
    throws IOException{
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;
        int start = firstPrintableCharacter;
        while (true){
            for(int i=start;i<start+numberOfCharactersPerLine;i++){
                outputStream.write(((i-firstPrintableCharacter)%numberOfPrintableCharacters)+firstPrintableCharacter);

            }
            outputStream.write('\r');
            outputStream.write('\n');
            start = ((start+1) - firstPrintableCharacter) %numberOfPrintableCharacters+firstPrintableCharacter;
        }
    }
    public void ioTest() throws FileNotFoundException {
        try(OutputStream out = new FileOutputStream("/tmp/data.txyt")) {

        } catch (IOException e) {
            e.printStackTrace();
        } ;

    }
}
