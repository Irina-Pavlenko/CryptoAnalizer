import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileProcessor {
    private static final StandardOpenOption[] FILE_WRITE_OPTIONS = {StandardOpenOption.CREATE, StandardOpenOption.APPEND};

    public void readFile(String filename, Cipher cipher, int key, boolean encrypt, String outputFilename) {
        ClassLoader classLoader = FileProcessor.class.getClassLoader();
        var resource = classLoader.getResource(filename);
        if (resource == null) {
            throw new RuntimeException("Файл не найден: " + filename);
        }

        StringBuilder processedContent = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(resource.toURI()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String processedLine = encrypt ? cipher.encrypt(line, key) : cipher.decrypt(line, key);
                processedContent.append(processedLine).append(System.lineSeparator());
            }
        } catch (IOException | URISyntaxException ex) {
            throw new RuntimeException("Ошибка обработки файла: " + ex.getMessage(), ex);
        }
        // Записываем зашифрованный текст в новый файл
        appendToFile(outputFilename, processedContent.toString());
    }

    public void appendToFile(String filename, String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filename), FILE_WRITE_OPTIONS)) {
            writer.write(content);
            writer.newLine();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}



