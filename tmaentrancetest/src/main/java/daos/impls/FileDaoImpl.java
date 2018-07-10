package daos.impls;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import daos.FileDao;
import models.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.io.*;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.Math.toIntExact;

import services.CategoryService;
import services.InterviewService;
import services.KindService;
import services.QuestionService;

@Repository
@Transactional
public class FileDaoImpl implements FileDao {
    private static final String FONT = "/fonts/times.ttf";
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private KindService kindService;
    @Autowired
    private InterviewService interviewService;
    @Autowired
    private QuestionService questionService;

    @Autowired
    private HibernateTransactionManager manager;

    public static final String[][] DATA = {
            {"Name:.............................................................................", "Date (dd-mm-yyyy):....................................."},
            {"Email:.............................................................................", "Start time:....................................................."},
            {"Phone:.............................................................................", "End time:......................................................"}
    };

    public FileDaoImpl() throws IOException, DocumentException {
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

        return new String[0][];
    }

    @Override
    public Question convertToQuestion(int id, String categoryId,  String kindId, String QuestionText, String CorrectAnswer,String Level){
        Category cat = this.categoryService.getCategoryById(Integer.parseInt(categoryId));
        Kind kind = this.kindService.getKindById(Integer.parseInt(kindId));

        Question q = new Question();
        q.setQuestionId(id);
        q.setCategory(cat);
        q.setKind(kind);
        q.setQuestionText(QuestionText);
        q.setCorrectAnswer(CorrectAnswer);
        q.setLevel(Integer.parseInt(Level));
        return q;
    }

    @Override
    public Answer convertToAnswer(int id, int QuestionId, String Answer, Question Question){
        Answer a = new Answer();
        a.setAnswerId(id);
        a.setQuestionId(QuestionId);
        a.setAnswerList(Answer);
        return a;
    }

    @Override
    public String exportRandomExamination(String technical, String interviewName, String description){
        String fileName = "";
        try {
            int count = 1;
            String FILE = "src/main/resources";
            File filePDF = new File(FILE);
            String temp = "temp/"+generateUniqueFileName()+".pdf";
            Path path = filePDF.toPath();
            path = Paths.get(path.toString(), temp);
            File file = new File(path.toString());
            file.getParentFile().mkdirs();
            while(file.exists()){
                temp = "temp/"+generateUniqueFileName()+String.valueOf(count)+".pdf";
                path = filePDF.toPath();
                path = Paths.get(path.toString(), temp);
                file = new File(path.toString());
                file.getParentFile().mkdirs();
                count++;
            }
            fileName = path.toString();
            Document document = new Document(PageSize.A4,30, 25, 28, 27);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            addMetaData(document);
            Paragraph profile = addProfileInformation(technical);

            List <Question> question = getRanDom(technical, 10);
            Paragraph interview = new Paragraph();
            interview = addInterview(question, writer, interviewName, technical, description);
            Paragraph questionList = addQuestion(question, writer);
            interview.setAlignment(Element.ALIGN_RIGHT);
            document.add(interview);
            document.add(profile);
            document.add(questionList);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public String exportExamByInterviewCode(String technical, String interviewCode, String IdList){
        String fileName = "";
        try {
            int count = 1;
            String FILE = "src/main/resources";
            File filePDF = new File(FILE);
            String temp = "temp/"+generateUniqueFileName()+".pdf";
            Path path = filePDF.toPath();
            path = Paths.get(path.toString(), temp);
            File file = new File(path.toString());
            file.getParentFile().mkdirs();
            while(file.exists()){
                temp = "temp/"+generateUniqueFileName()+String.valueOf(count)+".pdf";
                path = filePDF.toPath();
                path = Paths.get(path.toString(), temp);
                file = new File(path.toString());
                file.getParentFile().mkdirs();
                count++;
            }
            fileName = path.toString();
            Document document = new Document(PageSize.A4,30, 25, 28, 27);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            addMetaData(document);
            Paragraph profile = addProfileInformation(technical);

            String[] questionIdList = IdList.split(";");
            List <Question> questions = getQuestionsByIdList(questionIdList);
            Paragraph interview = new Paragraph();
            interview = addInterviewCode(interviewCode);
            Paragraph question = addQuestion(questions, writer);
            interview.setAlignment(Element.ALIGN_RIGHT);
            document.add(interview);
            document.add(profile);
            document.add(question);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public String exportListAnswer(String interviewCode){
        String fileName = "";
        try {
            int count = 1;
            String FILE = "src/main/resources";
            File filePDF = new File(FILE);
            String temp = "temp/"+generateUniqueFileName()+".pdf";
            Path path = filePDF.toPath();
            path = Paths.get(path.toString(), temp);
            File file = new File(path.toString());
            file.getParentFile().mkdirs();
            while(file.exists()){
                temp = "temp/"+generateUniqueFileName()+String.valueOf(count)+".pdf";
                path = filePDF.toPath();
                path = Paths.get(path.toString(), temp);
                file = new File(path.toString());
                file.getParentFile().mkdirs();
                count++;
            }
            fileName = path.toString();
            Document document = new Document(PageSize.A4,30, 25, 28, 27);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font catFont = new Font(bf, 18, Font.BOLD);
            Font normalFont = new Font(bf, 12, Font.NORMAL);
            Paragraph preface = new Paragraph();
            // We add one empty line
            addEmptyLine(preface, 1);
            // Lets write a big header
            preface.setAlignment(Element.ALIGN_RIGHT);
            preface.add(new Paragraph("Answer List " + interviewCode, catFont));
            //Lets write profile information
            addEmptyLine(preface, 1);
            document.add(preface);
            PdfPTable listAnswer = addAnswerList(interviewCode);
            document.add(listAnswer);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private List<Question> getQuestionsByIdList(String[] questionList){
        Session session = this.manager.getSessionFactory().getCurrentSession();
        List<Question> listQuestions = new ArrayList<Question>();
        for(String questionId : questionList) {
            int id;
            if(!StringUtils.isEmpty(questionId.trim())){
                id = Integer.parseInt(questionId.trim());
                listQuestions.add(this.questionService.getQuestionById(id));
            }
        }
        return listQuestions;
    }

    private PdfPTable addAnswerList(String interviewCode) throws DocumentException, IOException{

        Session session = this.manager.getSessionFactory().getCurrentSession();
        Criteria interviewCriteria = session.createCriteria(Interview.class);
        interviewCriteria.add(Restrictions.eq("interviewCode", interviewCode));
        List<Interview> interview = interviewCriteria.list();
        PdfPTable table = new PdfPTable(2);
        if(interview.size() > 0){
            String answers = interview.get(0).getAnswerList();
            String[] questionList = answers.split(";");
            table.setTotalWidth(288);
            table.setLockedWidth(true);
            table.setWidths(new float[]{1, 1});
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Answer table"));
            cell.setColspan(2);
            table.addCell(cell);
            int index = 0;
            for(String w: questionList){
                if(!w.trim().equals("")){
                    Criteria questionCriteria = session.createCriteria(Interview.class);
                    table.addCell(String.valueOf(++index));
                    table.addCell(w.trim());
                }
            }
        }
        return table;
    }

    private  static String generateUniqueFileName() {
        String filename = "";
        String datetime = new Date().toGMTString();
        datetime = datetime.replace(" ", "");
        datetime = datetime.replace(":", "");
        filename = datetime;
        return filename;
    }
    private static void addMetaData(Document document) {
        document.addTitle("Technical Test");
        document.addSubject("Technical Test");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Phuc Pham");
        document.addCreator("Phuc Pham");
    }

    private static Paragraph addProfileInformation(String technical)
            throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font catFont = new Font(bf, 18, Font.BOLD);
        Font normalFont = new Font(bf, 12, Font.NORMAL);
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.setAlignment(Element.ALIGN_RIGHT);
        preface.add(new Paragraph(technical + " Technical Test", catFont));

        //Lets write profile information
        addEmptyLine(preface, 1);
        preface.add(createParagraphWithTab(DATA[0][0],  DATA[0][1]));
        preface.add(createParagraphWithTab(DATA[1][0],  DATA[1][1]));
        preface.add(createParagraphWithTab( DATA[2][0], DATA[2][1]));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                "University:..................................................................................................................................................................",
                normalFont));
        preface.add(new Paragraph(
                "Faculty (Khoa):..........................................................................................................................................................",
                normalFont));
        preface.add(new Paragraph(
                "Major (Chuyên ngành):..............................................................................................................................................",
                normalFont));
        preface.add(new Paragraph(
                "GPA (Điểm trung bình tích lũy):...............................................................................................................................",
                normalFont));
        preface.add(new Paragraph(
                "Undergraduate thesis (Đề tài luận văn tốt nghiệp nếu có):........................................................................................",
                normalFont));
        preface.add(new Paragraph(
                "Time of graduation (mm-yyyy, thời điểm hoàn thành khóa học):.............................................................................",
                normalFont));
        preface.add(new Paragraph(
                "When are you available to start working (dd-mm-yyyy):..........................................................................................",
                normalFont));
        preface.add(new Paragraph(
                "Your expected Salary (Mức lương mong muốn):......................................................................................................",
                normalFont));
        preface.add(new Paragraph(
                "TOIEC score (if available):.......................................................................................................................................",
                normalFont));
        preface.add(new Paragraph(
                "Other certificates you have (CCNA, CCNP etc):......................................................................................................",
                normalFont));
        return preface;
    }

    private static Paragraph createParagraphWithTab(String key, String value1) throws IOException, DocumentException {
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

    public Paragraph addQuestion(List<Question> questions, PdfWriter writer) throws DocumentException, IOException{
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font catFont = new Font(bf, 18, Font.BOLD);
        Font normalFont = new Font(bf, 12, Font.NORMAL);
        StringBuilder questionIdList = new StringBuilder();
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.setAlignment(Element.ALIGN_RIGHT);
        preface.add(new Paragraph("Test Questions", catFont));
        int i = 0;
        while(questions.size() > 0){
            int rand = new Random().nextInt(questions.size());
            switch(questions.get(rand).getKind().getKindId()){
                case 1:
                    addSingleAnswerQuestion(preface, i+1, questions.get(rand).getQuestionText(), questions.get(rand).getAnswer());
                    break;
                case 2:
//                    addMultipleAnswerQuestion(preface, i+1, writer, questions.get(rand).getQuestionText(), questions.get(rand).getAnswer());
                    addSingleAnswerQuestion(preface, i+1, questions.get(rand).getQuestionText(), questions.get(rand).getAnswer());
                    break;
                case 3:
                    addShortQuestion(preface, i+1, questions.get(rand).getQuestionText(), questions.get(rand).getAnswer());
                    break;
                default :
            }
            i++;
            questions.remove(rand);
        }
        return preface;
    }

    private Paragraph addInterviewCode(String interviewCode) throws DocumentException, IOException{
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font normalFont = new Font(bf, 12, Font.BOLD);
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.setAlignment(Element.ALIGN_RIGHT);
        preface.add(new Paragraph(interviewCode, normalFont));

        return preface;
    }
    private Paragraph addInterview(List<Question> questions, PdfWriter writer ,String interviewName, String categoryName, String description) throws DocumentException, IOException{
        StringBuilder questionIdList = new StringBuilder();
        StringBuilder answerList = new StringBuilder();
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.setAlignment(Element.ALIGN_RIGHT);
        for(int i = 0; i < questions.size(); i++  ){
            questionIdList = questionIdList
                    .append(questions.get(i).getQuestionId())
                    .append("; ");
            answerList = answerList
                    .append(questions.get(i).getCorrectAnswer())
                    .append("; ");
        }

        Session session = this.manager.getSessionFactory().getCurrentSession();
        int numberInterview = 0;
        Criteria criteria = session.createCriteria(Interview.class)
                .setProjection(Projections.rowCount());

        List result = criteria.list();
        if (!result.isEmpty()) {
            numberInterview = toIntExact((Long) result.get(0));
        }
        numberInterview++;
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        String interviewCode = "VQIP" + datetime + "N" + numberInterview;
        Interview interview = new Interview();
        interview.setInterviewCode(interviewCode);
        interview.setInterviewName(interviewName);
        interview.setQuestionList(questionIdList.toString());
        interview.setAnswerList(answerList.toString());
        interview.setCategoryName(categoryName);
        interview.setDescription(description);
        this.interviewService.add(interview);
        preface = this.addInterviewCode(interviewCode);
        return preface;
    }
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static void addMultipleAnswerQuestion (Paragraph preface, int number, PdfWriter writer, String question, Answer answer) throws DocumentException, IOException{
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font catFont = new Font(bf, 18, Font.BOLD);
        Font normalFont = new Font(bf, 12, Font.NORMAL);
        preface.setAlignment(Element.ALIGN_RIGHT);
        preface.add(new Paragraph(String.valueOf(number) + ". " + question, normalFont));
        String[] words=answer.getAnswerList().split(";");
        char numberAnswer = 'A';
        for(String w:words){
            PdfFormField checkbox1 = PdfFormField.createCheckBox(writer);
            checkbox1.setWidget(new Rectangle(524, 600, 540, 616),
                    PdfAnnotation.HIGHLIGHT_NONE);
            checkbox1.setValueAsName("Off");
            checkbox1.setAppearanceState("Off");
            checkbox1.setFieldName("UsersNo");
            writer.addAnnotation(checkbox1);
            preface.add(new Paragraph( numberAnswer + ". " + w.trim(), normalFont));
            numberAnswer++;
        }
    }

    private static void addSingleAnswerQuestion (Paragraph preface, int number, String question, Answer answer) throws DocumentException, IOException{
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font catFont = new Font(bf, 18, Font.BOLD);
        Font normalFont = new Font(bf, 12, Font.NORMAL);
        preface.setAlignment(Element.ALIGN_RIGHT);
        preface.add(new Paragraph(String.valueOf(number) + ". " + question, normalFont));
        String[] words=answer.getAnswerList().split(";");
        char numberAnswer = 'A';
        for(String w:words){
            preface.add(new Paragraph( numberAnswer + ". " + w.trim(), normalFont));
            numberAnswer++;
        }
    }

    private static void addShortQuestion (Paragraph preface, int number, String question, Answer answer) throws DocumentException, IOException{
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font catFont = new Font(bf, 18, Font.BOLD);
        Font normalFont = new Font(bf, 12, Font.NORMAL);
        preface.setAlignment(Element.ALIGN_RIGHT);
        preface.add(new Paragraph(String.valueOf(number) + ". " + question, normalFont));
        for(int i =0; i < 5; i++){
            preface.add(new Paragraph(  "..................................................................................................................................................................................." , normalFont));
        }
    }

    private List getRanDom(String technical, int number){
        Session session = this.manager.getSessionFactory().getCurrentSession();
        List<Question> listQuestions = new ArrayList<Question>();
        for(int i = 1; i < 4; i++){
            Criteria questionCriteria1 = session.createCriteria(Question.class);
            questionCriteria1.add(Restrictions.eq("hardLevel", i));
            questionCriteria1.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            Criteria categoryCriteria1 = questionCriteria1.createCriteria("categoryId");
            Criteria answerCriteria1 = questionCriteria1.createCriteria("answerId");
            categoryCriteria1.add(Restrictions.eq("categoryName", technical));
            questionCriteria1.setProjection(Projections.rowCount());
            int count = ((Number) questionCriteria1.uniqueResult()).intValue();
            if (0 != count) {
                int index = new Random().nextInt(count - 5);
                questionCriteria1 = session.createCriteria(Question.class);
                questionCriteria1.add(Restrictions.eq("hardLevel", i));
                categoryCriteria1 = questionCriteria1.createCriteria("categoryId");
                answerCriteria1 = questionCriteria1.createCriteria("answerId");
                categoryCriteria1.add(Restrictions.eq("categoryName", technical));
                questionCriteria1.setFirstResult(index).setMaxResults(5);
                List a = questionCriteria1.list();
                listQuestions.addAll(a);
            }
        }

        return listQuestions;
    }
}

