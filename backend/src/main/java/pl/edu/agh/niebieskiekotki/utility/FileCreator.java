package pl.edu.agh.niebieskiekotki.utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.entitites.Vote;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.FileCreationFailedException;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.routes.VoteRouter;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireResults;

import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;

public class FileCreator {

    private static void addRow(Student student, int[] choices, HSSFSheet sheet, int rowNumber) {
        HSSFRow row = sheet.createRow((short)rowNumber);

        row.createCell(0).setCellValue(student.getIndexNumber());
        row.createCell(1).setCellValue(student.getFirstName());
        row.createCell(2).setCellValue(student.getLastName());
        row.createCell(3).setCellValue(student.getEmailAdress());
        for (int i = 0; i < choices.length; i++) {
            row.createCell(4 + i).setCellValue(choices[i]);
        }
    }

    private static void createHeaders(Language language, HSSFRow rowhead, QuestionnaireResults results){
        if (language == Language.POLISH) {
            rowhead.createCell(0).setCellValue("Indeks");
            rowhead.createCell(1).setCellValue("Imie");
            rowhead.createCell(2).setCellValue("Nazwisko");
            rowhead.createCell(3).setCellValue("e-mail");
        } else if (language == Language.ENGLISH){
            rowhead.createCell(0).setCellValue("Index");
            rowhead.createCell(1).setCellValue("Name");
            rowhead.createCell(2).setCellValue("Surname");
            rowhead.createCell(3).setCellValue("e-mail");
        }
        List<Term> terms = results.getQuestionnaireAvailableTerms();
        for (int i = 0; i < terms.size(); i++) {
            rowhead.createCell(4 + i).setCellValue(terms.get(i).getShortLabel(language));
        }
    }

    public static HSSFWorkbook createFileWithPreferences(Questionnaire questionnaire, Language language) {

        QuestionnaireResults results = new QuestionnaireResults(questionnaire.votes, questionnaire);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(questionnaire.getLabel());
        HSSFRow rowhead = sheet.createRow((short)0);

        createHeaders(language, rowhead, results);

        int i = 1;
        for (QuestionnaireResults.QuestionnaireResultsRow row : results.getRows()) {
            addRow(row.getStudent(), row.getStudentChoose(), sheet, i);
            i++;
        }

        for (int j = 0; j < results.getHeaders().size() + 4; j++) {
            sheet.autoSizeColumn(j);
        }
        return workbook;
    }

}
