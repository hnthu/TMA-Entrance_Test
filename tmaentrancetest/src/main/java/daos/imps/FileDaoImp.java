package daos.imps;

import daos.FileDao;
import models.Answer;
import models.Question;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;

@Repository
@Transactional
public class FileDaoImp implements FileDao {

    @Override
    public String[][] importData(MultipartFile file) throws IOException, InvalidFormatException {

        String[][] data;

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        InputStream fileIn = new BufferedInputStream(file.getInputStream());

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook wb = WorkbookFactory.create(fileIn);

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + wb.getNumberOfSheets() + " Sheets : ");
        for(int curSheet = 0; curSheet < wb.getNumberOfSheets(); curSheet++){
            Sheet sheet = wb.getSheetAt(curSheet);

            //Define Data & Row Array and adjust from Zero Base Numer
            data = new String[sheet.getLastRowNum()+1][];
            Row[] row = new Row[sheet.getLastRowNum()+1];
            Cell[][] cell = new Cell[row.length][];

            //Transfer Cell Data to Local Variable
            for(int i = 0; i < row.length; i++)
            {
                row[i] = sheet.getRow(i);

                //Note that cell number is not Zero Based
                cell[i] = new Cell[row[i].getLastCellNum()];
                data[i] = new String[row[i].getLastCellNum()];

                for(int j = 0; j < cell[i].length; j++)
                {
                    cell[i][j] = row[i].getCell(j);
                    data[i][j] = dataFormatter.formatCellValue(cell[i][j]);
                }
            }
            fileIn.close();
            System.out.println(data);
            return data;
        }
       ;
        return new String[0][];
    }

    @Override
    public Question convertToQuestion(int id, String CategoryId,  String QuestionTypeId, String QuestionText, String CorrectAnswer){
        Question q = new Question();
        q.setId(id);
        q.setCategoryid(Integer.parseInt(CategoryId));
        q.setQuestiontypeid(Integer.parseInt(QuestionTypeId));
        q.setQuestiontext(QuestionText);
        q.setCorrectanswer(Integer.parseInt(CorrectAnswer));
        return q;
    }

    @Override
    public Answer convertToAnswer(int id, int QuestionId, String Answer1, String Answer2, String Answer3, String Answer4){
        Answer a = new Answer();
        a.setId(id);
        a.setQuestionid(QuestionId);
        a.setAnswer1(Answer1);
        a.setAnswer2(Answer2);
        a.setAnswer3(Answer3);
        a.setAnswer4(Answer4);
        return a;
    }
}
