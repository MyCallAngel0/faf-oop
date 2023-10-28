package org.oop.lab.two.behavior;

import org.oop.lab.two.filetype.FileType;
import org.oop.lab.two.filetype.ImageFile;
import org.oop.lab.two.filetype.ProgramFile;
import org.oop.lab.two.filetype.TextFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private final String directoryPath = ".\\src\\main\\java\\org\\oop\\lab\\two\\git";
    private final File directory = new File(directoryPath);

    protected File[] files;

    protected List<FileType> fileArray = new ArrayList<>();

    public FileManager() {
        this.files = directory.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);

            if (isImageFile(fileExtension)) {
                this.fileArray.add(new ImageFile(fileName, file.getPath()));
            } else if (isTextFile(fileExtension)) {
                this.fileArray.add(new TextFile(fileName, file.getPath()));
            } else if (isProgramFile(fileExtension)) {
                this.fileArray.add(new ProgramFile(fileName, file.getPath()));
            } else {
                System.out.println("File type not recognized.");
            }
        }
    }

    public void getFileInformation(String filename) {
        fileArray.stream().filter(fileType -> fileType.getFileName().equalsIgnoreCase(filename))
                .findFirst().ifPresentOrElse(FileType::getInfo, () -> System.out.println("File not found"));
    }

    private static boolean isImageFile(String extension) {
        List<String> extensions = List.of("jpg", "png", "jpeg", "gif");
        return extensions.contains(extension);
    }

    private static boolean isTextFile(String extension) {
        return extension.equalsIgnoreCase("txt");
    }

    private static boolean isProgramFile(String extension) {
        List<String> extensions = List.of("java", "py", "cpp", "cs");
        return extensions.contains(extension);
    }

    public File[] getFiles() {
        return files;
    }

}
