package rise.cc.dto;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

class NoticeAddDtoTest {

    @Test
    public void test() throws IOException {
        String content = "Hello File!";
        String path = "/tmp/a.txt";
        Files.write(Paths.get(path), content.getBytes(StandardCharsets.UTF_8));
        File file = new File(path);
        String fileContent = "";
        try (FileReader fr = new FileReader(file)) {
            char[] chars = new char[(int) file.length()];
            fr.read(chars);

            fileContent = new String(chars);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fileContent);
    }
}
