package my_examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Arrays;

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

        boolean pathIsEmpty,
                commandIsEmpty = true,
                stringOfExtensionsIsEmpty = true,
                listFilesIsEmpty = true,
                noneIsEnabled = false,
                allIsEnabled  = false,
                addIsEnabled  = false;

        File folder = null,
             resultFile;

        File[] listFiles = new File[0];

        String path,
               command,
               stringOfExtensions,
               currentExtension,
               fileToString,
               resultString;

        String[] extensions = null;

        int countDirectories = 0,
            countFilesWithAnyExtension,
            countFilesWithCurrentExtension;

        System.out.print(
                "+--------------------------------+\n" +
                "| Переименование файлов в папке. |\n" +
                "+--------------------------------+\n\n" +
                "Как работать с данной программой:\n" +
                "1. Прописывайте путь к папке.\n" +
                "2. Воспользуйтесь специальной командой:\n" +
                "->  all : переименование всех файлов подряд и отдельно папок\n" +
                "->  add : добавляет к названиям файлов порядковый номер\n" +
                "-> none : преобразование по умолчанию, переименовывает по каждому " +
                          "введенному расширению (вместо ввода этой команды можно нажать Enter)\n" +
                "3. Прописывайте расширения через запятую, для переименования папок введите \"folders\"\n" +
                "\n\n" +
                "Введите путь: ");
        do {
            path = reader.readLine().trim(); //сработают таким образом тримы?

            pathIsEmpty = isEmpty(path);
            if (pathIsEmpty) {
                System.out.print("Пустой запрос. Повторите ввод заново, начиная с пути: ");
                continue;
            }

            folder = new File(path);
            listFiles = folder.listFiles();

            listFilesIsEmpty = (listFiles == null) || listFiles.length == 0;
            if (listFilesIsEmpty) {
                System.out.print("Файлов в папке нет или такого пути не существует. Повторите ввод заново, начиная с пути: ");
                continue;
            }


            System.out.print("Введите команду: ");
            command = reader.readLine().trim();

            commandIsEmpty = isEmpty(command);
            if (commandIsEmpty || command.equals("none")) {
                noneIsEnabled = true;
            } else if (command.equals("all")) {
                allIsEnabled = true;// TODO: 24.10.2017 почему-то цикл повторяется
            } else if (command.equals("add")) {
                addIsEnabled = true;
            } else {
                System.out.print("Некорректная команда. Повторите ввод заново, начиная с пути: ");
                continue;
            }


            if (!allIsEnabled) {
                System.out.print("Введите расширения: ");
                stringOfExtensions = reader.readLine().trim();

                stringOfExtensionsIsEmpty = isEmpty(stringOfExtensions);
                if (stringOfExtensionsIsEmpty) {
                    System.out.print("Пустой запрос. Повторите ввод заново, начиная с пути: ");
                    continue;
                }

                extensions = removeDuplicates(stringOfExtensions.split(","));

                if (extensions.length < 1) {
                    System.out.print("Не введены расширения. Повторите ввод заново, начиная с пути: ");
                }
            }

        } while (pathIsEmpty || listFilesIsEmpty || commandIsEmpty || stringOfExtensionsIsEmpty || extensions.length < 1);

// TODO: 24.10.2017 изменить алгоритм цикла(или метода)
        for (String extension: extensions) {
            countFilesWithCurrentExtension = 0;
            countFilesWithAnyExtension = 0;
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
                        if (currentFile.isDirectory()) {
                            resultString = folder.toString() + "\\" + ++countDirectories;
                            resultFile = Paths.get(resultString).toFile();
                            currentFile.renameTo(resultFile);
                            continue;
                        }
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

    private static boolean isEmpty(String s) {
        return (s == null) || s.equals("");
    }

    public static void main(String[] args) {
        try {
            renameFiles();
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода!");
            e.printStackTrace();
        }
    }
}
