package pl.edu.agh.niebieskiekotki.utility;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.Results;
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireResults;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

public class FileCreator {

    private static void addRow(Student student, int[] choices, HSSFSheet sheet, int rowNumber) {
        HSSFRow row = sheet.createRow((short) rowNumber);

        row.createCell(0).setCellValue(student.getIndexNumber());
        row.createCell(1).setCellValue(student.getFirstName());
        row.createCell(2).setCellValue(student.getLastName());
        row.createCell(3).setCellValue(student.getEmailAddress());
        for (int i = 0; i < choices.length; i++) {
            row.createCell(4 + i).setCellValue(choices[i]);
        }
    }

    private static void createHeaders(Language language, HSSFRow rowhead, QuestionnaireResults results) {
        if (language == Language.POLISH) {
            rowhead.createCell(0).setCellValue("Indeks");
            rowhead.createCell(1).setCellValue("Imie");
            rowhead.createCell(2).setCellValue("Nazwisko");
            rowhead.createCell(3).setCellValue("e-mail");
        } else if (language == Language.ENGLISH) {
            rowhead.createCell(0).setCellValue("Index");
            rowhead.createCell(1).setCellValue("Name");
            rowhead.createCell(2).setCellValue("Surname");
            rowhead.createCell(3).setCellValue("e-mail");
        }
        List<Term> terms = results.getAvailableTerms();
        for (int i = 0; i < terms.size(); i++) {
            rowhead.createCell(4 + i).setCellValue(terms.get(i).getShortLabel(language));
        }
    }

    public static HSSFWorkbook createFileWithPreferences(Questionnaire questionnaire, Language language) {

        QuestionnaireResults results = new QuestionnaireResults(questionnaire.votes, questionnaire);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(questionnaire.getLabel());
        HSSFRow rowhead = sheet.createRow((short) 0);

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

    public static File createFileGroups(List<Results> results, Language language, String fileName) throws IOException {
        StringBuilder builder = new StringBuilder();
        results.sort(Comparator.comparing(Results::getTerm));
        Term currentTerm = results.get(0).getTerm();
        builder.append(currentTerm.getShortLabel(language))
                .append("\n");

        for (Results result : results) {
            if (!result.getTerm().toString().equals(currentTerm.toString())) {
                currentTerm = result.getTerm();
                builder.append("\n\n")
                        .append(currentTerm.getShortLabel(language))
                        .append("\n");
            }
            builder.append(result.getStudent().getFirstName())
                    .append(" ")
                    .append(result.getStudent().getLastName())
                    .append("\n");
        }
        File file = new File(fileName);
        FileWriter writer = new FileWriter(fileName);
        writer.write(builder.toString());
        writer.close();
        return file;
    }

}
