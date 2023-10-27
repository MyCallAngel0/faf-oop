package org.oop.lab.two.filetype;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramFile extends FileType {
    private int nrOfLines;
    private int nrOfClasses;
    private int nrOfMethods;

    public ProgramFile(String filename, String filePath) {
        super(filename, filePath);
        getAdditionalFileInfo();
    }
    @Override
    protected void getAdditionalFileInfo() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            Pattern classPattern = Pattern.compile("\\s*class\\s+");
            Pattern methodPattern = Pattern.compile("\\s*(public|private|protected)\\s+(static\\s+)?[\\w\\<\\>\\[\\]]+\\s+[a-zA-Z_][a-zA-Z0-9_]*\\s*\\(");

            int numberOfLines = 0;
            int numberOfClasses = 0;
            int numberOfMethods = 0;

            for (String line : lines) {
                numberOfLines++;
                Matcher classMatcher = classPattern.matcher(line);
                Matcher methodMatcher = methodPattern.matcher(line);

                if (classMatcher.find()) {
                    numberOfClasses++;
                }
                if (methodMatcher.find() || line.contains("def")) {
                    numberOfMethods++;
                }
            }
            this.nrOfLines = numberOfLines;
            this.nrOfClasses = numberOfClasses;
            this.nrOfMethods = numberOfMethods;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void getInfo() {
        System.out.println("Number of lines: " + this.nrOfLines);
        System.out.println("Number of classes: " + this.nrOfClasses);
        System.out.println("Number of methods: " + this.nrOfMethods);
    }
}
