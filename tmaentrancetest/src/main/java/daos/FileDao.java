package daos;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileDao {
    public String[][] importData(MultipartFile file) throws IOException, InvalidFormatException;
}
