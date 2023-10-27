package org.oop.lab.two.filetype;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFile extends FileType {
    private int lines;
    private int words;

    private int characters;
    public TextFile(String filename, String filePath) {
        super(filename, filePath);
        getAdditionalFileInfo();
    }

    @Override
    protected void getAdditionalFileInfo() {
        int lineCount = 0;
        int charCount = 0;
        int wordCount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length();

                String[] words = line.split("\\s+");
                wordCount += words.length;

                this.lines = lineCount;
                this.words = wordCount;
                this.characters = charCount;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void getInfo() {
        getAdditionalFileInfo();
        System.out.println("Line count: " + this.lines);
        System.out.println("Character count: " + this.characters);
        System.out.println("Word count: " + this.words);
    }
}
