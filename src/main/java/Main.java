import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        GameProgress save1 = new GameProgress(100, 100, 1, 0.0);

        saveGame("C://Games//savegames//save1.dat", save1);

        GameProgress save2 = new GameProgress(100, 100, 20, 200.0);

        saveGame("C://Games//savegames//save2.dat", save2);

        GameProgress save3 = new GameProgress(10, 0, 100, 2000.0);

        saveGame("C://Games//savegames//save3.dat", save3);

        zipFiles("C://Games//savegames//zip.zip", List.of("C://Games//savegames//save1.dat",
                "C://Games//savegames//save2.dat", "C://Games//savegames//save3.dat"
        ));

    }

    private static void saveGame(String path, GameProgress gameProgress) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    private static void zipFiles(String zipFile, List<String> files) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFile))) {

            for (var fileName : files) {

                File file = new File(fileName);

                try (FileInputStream fis = new FileInputStream(file)) {

                    ZipEntry entry1 = new ZipEntry(file.getName());

                    zout.putNextEntry(entry1);

                    byte[] buffer = new byte[fis.available()];

                    fis.read(buffer);

                    zout.write(buffer);

                    zout.closeEntry();
                }
            }
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }
}
