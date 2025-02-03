import java.util.Scanner;


public class MainApp {
    public static void main(String[] args){
        Constants constants = new Constants();
        Cipher cipher = new Cipher(constants);
        FileProcessor fileProcessor = new FileProcessor();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите режим работы:");
        System.out.println("1. Шифрование текста");
        System.out.println("2. Расшифровка текста");
        System.out.println("3. Шифрование/расшифровка файла");
        System.out.println("4. Брутфорс расшифровки");

        int mode = scanner.nextInt();
        scanner.nextLine();

        switch (mode) {
            case 1:
                System.out.print("Введите текст для шифрования: ");
                String textToEncrypt = scanner.nextLine();
                System.out.print("Введите ключ (сдвиг): ");
                int encryptKey = scanner.nextInt();
                validateInput(constants, textToEncrypt, encryptKey);
                String encryptedText = cipher.encrypt(textToEncrypt, encryptKey);
                System.out.println("Зашифрованный текст: " + encryptedText);
                break;

            case 2:
                System.out.print("Введите текст для расшифровки: ");
                String textToDecrypt = scanner.nextLine();
                System.out.print("Введите ключ (сдвиг): ");
                int decryptKey = scanner.nextInt();
                validateInput(constants, textToDecrypt, decryptKey);
                String decryptedText = cipher.decrypt(textToDecrypt, decryptKey);
                System.out.println("Расшифрованный текст: " + decryptedText);
                break;

            case 3:
                System.out.print("Введите имя файла: ");
                String filename = scanner.nextLine();
                System.out.print("Введите ключ (сдвиг): ");
                int fileKey = scanner.nextInt();
                validateInput(constants, filename, fileKey);
                System.out.print("Вы хотите зашифровать (введите 'true') или расшифровать (введите 'false')? ");
                boolean encryptFile = scanner.nextBoolean();
                scanner.nextLine();
                String outputFilename;
                if (encryptFile) {
                    System.out.print("Введите имя для сохранения зашифрованного файла: ");
                    outputFilename = scanner.nextLine();
                } else {
                    System.out.print("Введите имя для сохранения расшифрованного файла: ");
                    outputFilename = scanner.nextLine();
                }

                fileProcessor.readFile(filename, cipher, fileKey, encryptFile, outputFilename);
                System.out.println("Файл обработан и сохранен под именем: " + outputFilename);
                break;

            case 4:
                System.out.print("Введите текст для брутфорса: ");
                String textForBruteForce = scanner.nextLine();
                validateInput(constants, textForBruteForce, 0);
                cipher.bruteForceDecrypt(textForBruteForce);
                break;

            default:
                System.out.println("Неверный режим работы.");
        }

        scanner.close();
    }
    public static void validateInput(Constants constants, String text, int key) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Текст не может быть пустым.");
        }
        if (key < 0 || key >= constants.getSize()) {
            throw new IllegalArgumentException("Ключ должен быть в пределах от 0 до " + (constants.getSize() - 1));
        }
    }
}

