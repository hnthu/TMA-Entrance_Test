package services;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {
    public String[][] importData(MultipartFile file) throws IOException, InvalidFormatException;
}
