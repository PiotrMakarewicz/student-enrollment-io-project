package pl.edu.agh.niebieskiekotki.routes;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.utility.FileCreator;
import pl.edu.agh.niebieskiekotki.utility.Language;
import java.io.*;
import java.util.Objects;

@CrossOrigin
@RestController
public class FileRouter {

    private final HibernateAdapter hibernateAdapter;

    public FileRouter(HibernateAdapter hibernateAdapter) {
        this.hibernateAdapter = hibernateAdapter;
    }

    @GetMapping(value="/files/preferences/{language}/{id}")
    public ResponseEntity<ByteArrayResource> downloadPreferences(@PathVariable String language, @PathVariable long id) throws Exception {
        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, id);
        if (questionnaire == null)
            throw new NotFoundException("Not found questionnaire with id " + id);
        HSSFWorkbook workbook = FileCreator.createFileWithPreferences(questionnaire, Language.fromString(language));
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ProductTemplate.xlsx");
            workbook.write(stream);
            workbook.close();
            return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
                    header, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}