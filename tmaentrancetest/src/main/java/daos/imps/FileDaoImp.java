package daos.imps;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import daos.FileDao;
import models.Answer;
import models.Question;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;

import java.io.FileOutputStream;

import com.itextpdf.text.pdf.PdfWriter;

@Repository
@Transactional
public class FileDaoImp implements FileDao {

    private static String FILE = "C:\\temp\\a.pdf";
    private static final String FONT = "D:\\Project\\TMA-Entrance_Test\\tmaentrancetest\\src\\main\\resources\\fonts\\times.ttf";


    public static final String[][] DATA = {
            {"Name:.............................................................................", "Date (dd-mm-yyyy):......................"},
            {"Email:............................................................................", "Start time:..............................."},
            {"Phone:...........................................................................", "End time:..............................."}
    };

    public FileDaoImp() throws IOException, DocumentException {
    }

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
    public Answer convertToAnswer(int id, int QuestionId, String Answer){
        Answer a = new Answer();
        a.setId(id);
        a.setQuestionid(QuestionId);
        a.setAnswer(Answer);
        return a;
    }

    @Override
    public void exportPDF(String technical){
        try {
            File file = new File(FILE);
            file.getParentFile().mkdirs();
            Document document = new Document(PageSize.A4,30, 25, 28, 27);
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            addMetaData(document);
            addProfileInformation(document, technical);
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("Technical Test");
        document.addSubject("Technical Test");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Phuc Pham");
        document.addCreator("Phuc Pham");
    }

    private static void addProfileInformation(Document document, String technical)
            throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font catFont = new Font(bf, 18, Font.BOLD);
        Font normalFont = new Font(bf, 12, Font.NORMAL);
        Paragraph title = new Paragraph();
        // We add one empty line
        addEmptyLine(title, 1);
        // Lets write a big header
        title.setAlignment(Element.ALIGN_RIGHT);
        title.add(new Paragraph(technical + "Technical Test", catFont));
        document.add(title);

        //Lets write profile information
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(createParagraphWithTab(DATA[0][0],  DATA[0][1]));
        preface.add(createParagraphWithTab(DATA[1][0],  DATA[1][1]));
        preface.add(createParagraphWithTab( DATA[2][0], DATA[2][1]));

        preface.add(new Paragraph(
                "University:......................................................................................................................................................",
                normalFont));
        preface.add(new Paragraph(
                "Faculty (Khoa):...........................................................................................................................................",
                normalFont));
        preface.add(new Paragraph(
                "Major (Chuyên ngành):...................................................................................................................................",
                normalFont));
        preface.add(new Paragraph(
                "GPA (Điểm trung bình tích lũy):.............................................................................................................................",
                normalFont));
        preface.add(new Paragraph(
                "Undergraduate thesis (Đề tài luận văn tốt nghiệp nếu có):.................................................................",
                normalFont));
        preface.add(new Paragraph(
                "Time of graduation (mm-yyyy, thời điểm hoàn thành khóa học):....................................................",
                normalFont));
        preface.add(new Paragraph(
                "When are you available to start working (dd-mm-yyyy):...............................................................................",
                normalFont));
        preface.add(new Paragraph(
                "Your expected Salary (Mức lương mong muốn):........................................................",
                normalFont));
        preface.add(new Paragraph(
                "TOIEC score (if available):.............................................................................................................................",
                normalFont));
        preface.add(new Paragraph(
                "Other certificates you have (CCNA, CCNP etc):.........................................................................................",
                normalFont));
        document.add(preface);
    }

    public static Paragraph createParagraphWithTab(String key, String value1) throws IOException, DocumentException {
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font subFont = new Font(bf, 16, Font.BOLD);
        Font smallBold = new Font(bf, 12, Font.BOLD);
        Paragraph p = new Paragraph();
        p.setFont(smallBold);
        p.setTabSettings(new TabSettings(80f));
        p.add(key);
        p.add(Chunk.TABBING);
        p.add(value1);
        return p;
    }

    private static void addContent(Document document) throws DocumentException {


    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}

