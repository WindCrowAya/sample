package my_examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Renaming files in specified folder.
 * Use the console to work with this program.
 *
 * Useful links:
 *   System.arraycopy() : http://javadevblog.com/kak-skopirovat-massiv-v-java.html
 */
public class RenamingFilesClass {

    public static void renameFiles() throws IOException {
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

        String[] pathAndExtensions,
                 extensions = null;

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

            pathAndExtensions = input.trim().split(" ");

            folder = new File(pathAndExtensions[0]);
            listFiles = folder.listFiles();

            listFilesIsEmpty = (listFiles == null) || listFiles.length == 0;
            if (listFilesIsEmpty) {
                System.out.print("Файлов в папке нет или такого пути не существует. Повторите ввод: ");
                continue;
            }

            extensions = new String[pathAndExtensions.length];
            extensions[0] = " ";
            System.arraycopy(pathAndExtensions, 1, extensions, 1, pathAndExtensions.length - 1);

        } while (inputIsEmpty || listFilesIsEmpty);

        int countDirectories = 0;
        for (String extension: extensions) {
            int countFilesWithCurrentExtension = 0;
            for (int i = 0; i < listFiles.length; i++) {
                fileToString = listFiles[i].toString();
                if (!listFiles[i].isDirectory()) {
                    currentExtension = fileToString.substring(fileToString.lastIndexOf(".") + 1);
                    if (currentExtension.equals(extension)) {
                        resultString = folder.toString() + "\\" + ++countFilesWithCurrentExtension + "." + currentExtension;
                        resultFile = Paths.get(resultString).toFile();
                        listFiles[i].renameTo(resultFile);
                    }
                } else {
                    resultString = folder.toString() + "\\" + ++countDirectories;
                    resultFile = Paths.get(resultString).toFile();
                    listFiles[i].renameTo(resultFile);
                }
            }
        }

        //проверка на папки
        /*for (String extension: extensions) {
            for (int i = 0; i < listFiles.length; i++) {
                if (extension.equals("folders")) {
                    resultString = folder.toString() + "\\" + ++countDirectories;
                    resultFile = Paths.get(resultString).toFile();
                    listFiles[i].renameTo(resultFile);
                } else
                    break;
            }
        }*/

        System.out.println("Переименование завершено.");
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
