package my_examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Renaming files in specified folder.
 * Use the console to work with this program.
 *
 * Useful links:
 *   System.arraycopy() : http://javadevblog.com/kak-skopirovat-massiv-v-java.html
 *   Arrays.copyOf() : https://stackoverflow.com/questions/1018750/how-to-convert-object-array-to-string-array-in-java
 */
public class RenamingFilesClass {

    private static void renameFiles() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //читаемость кода стала лучше? или лучше выровнять все ссылки?
        boolean inputIsEmpty,
                listFilesIsEmpty = true;

        File folder = null,
             resultFile;

        File[] listFiles = new File[0];

        String input,
               currentExtension,
               fileToString,
               resultString;

        String[] pathAndExtensions = null,
                 extensions = null;

        int countDirectories = 0,
            countFilesWithCurrentExtension,
            countFilesWithAnyExtension = 0;

        System.out.print(
                "Переименование файлов в папке.\n" +
                "Сначала вводите путь к папке, затем ставите пробел и прописываете нужные расширения,\n" +
                "разделяя их пробелами.\n" +
                "Ввод команды: ");
        do {
            input = reader.readLine();

            inputIsEmpty = (input == null) || input.trim().equals("");
            if (inputIsEmpty) {
                System.out.print("Пустой запрос. Повторите ввод: ");
                continue;
            }

            pathAndExtensions = removeDuplicates(input.trim().split(" "));

            folder = new File(pathAndExtensions[0]);
            listFiles = folder.listFiles();

            listFilesIsEmpty = (listFiles == null) || listFiles.length == 0;
            if (listFilesIsEmpty) {
                System.out.print("Файлов в папке нет или такого пути не существует. Повторите ввод: ");
                continue;
            }

            extensions = new String[pathAndExtensions.length - 1];
            System.arraycopy(pathAndExtensions, 1, extensions, 0, pathAndExtensions.length - 1);

        } while (inputIsEmpty || listFilesIsEmpty);


        for (String extension: extensions) {
            countFilesWithCurrentExtension = 0;
            switch (extension) {
                case "folders":
                    for (File currentFile : listFiles) {
                        if (currentFile.isDirectory()) {
                            resultString = folder.toString() + "\\" + ++countDirectories;
                            resultFile = Paths.get(resultString).toFile();
                            currentFile.renameTo(resultFile);
                        }
                    }
                    break;
                case "all":
                    for (File currentFile : listFiles) {
                        fileToString = currentFile.toString();
                        currentExtension = fileToString.substring(fileToString.lastIndexOf(".") + 1);
                        resultString = folder.toString() + "\\" + ++countFilesWithAnyExtension + "." + currentExtension;
                        resultFile = Paths.get(resultString).toFile();
                        currentFile.renameTo(resultFile);
                    }
                    break;
                default:
                    for (File currentFile : listFiles) {
                        fileToString = currentFile.toString();
                        currentExtension = fileToString.substring(fileToString.lastIndexOf(".") + 1);
                        if (currentExtension.equals(extension)) {
                            resultString = folder.toString() + "\\" + ++countFilesWithCurrentExtension + "." + currentExtension;
                            resultFile = Paths.get(resultString).toFile();
                            currentFile.renameTo(resultFile);
                        }
                    }
                    break;
            }

        }

        System.out.println("Переименование завершено.");
    }

    private static String[] removeDuplicates(String[] s) {
        Object[] o = Arrays.stream(s).distinct().toArray();
        return Arrays.copyOf(o, o.length, String[].class);
    }

    /*public File convertFileToString(String s, int i) {
        return Paths.get(s).toFile();
    }*/

    public static void main(String[] args) {
        try {
            renameFiles();
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода!");
            e.printStackTrace();
        }
    }
}
