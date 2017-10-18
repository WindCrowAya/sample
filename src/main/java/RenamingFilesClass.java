import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Renaming files in specified folder
 */
public class RenamingFilesClass { // TODO: 17.10.2017 сделать так, чтобы в случае ошибочного пути создавался повторный ввод другого пути

    public static void renameFiles() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean pathIsEmpty, listFilesIsEmpty = true;
        String path;
        File folder = null;             //костыль?
        File[] listFiles = new File[0]; //костыль?
        File resultFile;
        String fileToString, resultString;

        System.out.print("Переименование файлов в папке.\nВведите путь к папке: ");
        do {
            path = reader.readLine();
            pathIsEmpty = (path == null) || path.trim().equals("");
            if (pathIsEmpty) {
                System.out.print("Пустой запрос. Повторите попытку: ");
                continue;
            }
            folder = new File(path);
            listFiles = folder.listFiles();
            listFilesIsEmpty = (listFiles == null) || listFiles.length == 0;
            if (listFilesIsEmpty) {
                System.out.print("Файлов в папке нет или такого пути не существует. Повторите попытку: ");
            }
        } while (pathIsEmpty || listFilesIsEmpty);

        for (int i = 0; i < listFiles.length; i++) {
            fileToString = listFiles[i].toString();
//            index = fileToString.lastIndexOf(".");
            resultString = folder.toString() + "\\" + i + fileToString.substring(fileToString.lastIndexOf("."));
            resultFile = Paths.get(resultString).toFile();
            listFiles[i].renameTo(resultFile);
        }

        System.out.println("Переименование завершено.");
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
