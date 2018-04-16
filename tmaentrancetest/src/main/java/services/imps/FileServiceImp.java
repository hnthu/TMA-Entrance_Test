package services.imps;

import daos.FileDao;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import services.FileService;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImp implements FileService {
    @Autowired
    private FileDao fileDao;

    @Autowired
    public void setQuestionDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    @Override
    @Transactional
    public String[][] importData(MultipartFile file) throws IOException, InvalidFormatException {
        try {
            return this.fileDao.importData(file) ;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return new String[0][];
    }
}
