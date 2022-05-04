package pl.edu.agh.niebieskiekotki.utility;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.QuestionnaireAccess;
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireResults;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileOutputStream;
import java.util.List;

public class FileWithLinksCreator {
    private static void addRow(Student student, String link, HSSFSheet sheet, int rowNumber) {
        HSSFRow row = sheet.createRow((short)rowNumber);

        row.createCell(0).setCellValue(student.getIndexNumber());
        row.createCell(1).setCellValue(student.getFirstName());
        row.createCell(2).setCellValue(student.getLastName());
        row.createCell(3).setCellValue(student.getEmailAdress());
        row.createCell(4).setCellValue(link);

    }

    private static void createHeaders(Language language, HSSFRow rowhead){
        if (language == Language.POLISH) {
            rowhead.createCell(0).setCellValue("Indeks");
            rowhead.createCell(1).setCellValue("Imie");
            rowhead.createCell(2).setCellValue("Nazwisko");
        } else if (language == Language.ENGLISH){
            rowhead.createCell(0).setCellValue("Index");
            rowhead.createCell(1).setCellValue("Name");
            rowhead.createCell(2).setCellValue("Surname");
        }
        rowhead.createCell(3).setCellValue("e-mail");
        rowhead.createCell(4).setCellValue("link");
    }

    public static void createFileWithLinks(Questionnaire questionnaire, Language language)
            throws ParserConfigurationException, TransformerException {

        List<QuestionnaireAccess> questionnaireAccessesList = questionnaire.questionnaireAccesses;
        String filename = "src/main/resources/links"
                + questionnaire.getId()+ ".xlsx";

        try
        {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("fileWithLinks"+questionnaire.getId());
            HSSFRow rowhead = sheet.createRow((short)0);

            createHeaders(language, rowhead);

            int i = 1;
            for (QuestionnaireAccess questionnaireAccess : questionnaireAccessesList) {
                addRow(questionnaireAccess.getStudent(), "localhost:3000/vote/"+questionnaireAccess.getLinkPath(), sheet, i);
                i++;
            }

            /*for (int j = 0; j < 5; j++) {
                sheet.autoSizeColumn(j);
            }*/

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Excel file has been generated successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
