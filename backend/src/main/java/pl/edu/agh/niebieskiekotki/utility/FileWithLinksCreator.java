package pl.edu.agh.niebieskiekotki.utility;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.QuestionnaireAccess;
import pl.edu.agh.niebieskiekotki.entitites.Student;

import java.util.List;

public class FileWithLinksCreator {


    private static void addRow(Student student, String link, HSSFSheet sheet, int rowNumber) {

        HSSFRow row = sheet.createRow((short) rowNumber);

        row.createCell(0).setCellValue(student.getIndexNumber());
        row.createCell(1).setCellValue(student.getFirstName());
        row.createCell(2).setCellValue(student.getLastName());
        row.createCell(3).setCellValue(student.getEmailAddress());
        row.createCell(4).setCellValue(link);
    }

    private static void createHeaders(Language language, HSSFRow rowhead) {

        if (language == Language.POLISH) {
            rowhead.createCell(0).setCellValue("Indeks");
            rowhead.createCell(1).setCellValue("Imie");
            rowhead.createCell(2).setCellValue("Nazwisko");
        } else if (language == Language.ENGLISH) {
            rowhead.createCell(0).setCellValue("Index");
            rowhead.createCell(1).setCellValue("Name");
            rowhead.createCell(2).setCellValue("Surname");
        }
        rowhead.createCell(3).setCellValue("e-mail");
        rowhead.createCell(4).setCellValue("link");
    }

    public static HSSFWorkbook createFileWithLinks(Questionnaire questionnaire, Language language, String serverURl) {

        List<QuestionnaireAccess> questionnaireAccessesList = questionnaire.questionnaireAccesses;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("fileWithLinks" + questionnaire.getId());
        HSSFRow rowhead = sheet.createRow((short) 0);

        createHeaders(language, rowhead);

        int i = 1;
        for (QuestionnaireAccess questionnaireAccess : questionnaireAccessesList) {
            addRow(questionnaireAccess.getStudent(), serverURl + "/vote/" + questionnaireAccess.getLinkPath(), sheet, i);
            i++;
        }

        for (int j = 0; j < 5; j++) {
            sheet.autoSizeColumn(j);
        }

        return workbook;
    }
}

