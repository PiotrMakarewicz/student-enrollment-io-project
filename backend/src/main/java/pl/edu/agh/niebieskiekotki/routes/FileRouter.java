package pl.edu.agh.niebieskiekotki.routes;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.utility.FileCreator;
import pl.edu.agh.niebieskiekotki.utility.FileWithLinksCreator;
import pl.edu.agh.niebieskiekotki.utility.Language;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.File;

@CrossOrigin
@RestController
public class FileRouter {

    private final HibernateAdapter hibernateAdapter;

    public FileRouter(HibernateAdapter hibernateAdapter) {
        this.hibernateAdapter = hibernateAdapter;
    }


    private ResponseEntity<ByteArrayResource> createResponse(HSSFWorkbook workbook, String filename){
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+filename);
            workbook.write(stream);
            workbook.close();
            return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
                    header, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @GetMapping(value = "/files/results/{language}/{id}")
    public ResponseEntity<ByteArrayResource> downloadResults(@PathVariable String language, @PathVariable long id) throws Exception {
        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, id);
        if (questionnaire == null)
            throw new NotFoundException("Not found results for questionnaire with id " + id);
        if (questionnaire.results.size() == 0)
            throw new NotFoundException("No results for this questionnaire " + id);
        File file = FileCreator.createFileGroups(questionnaire.results, Language.fromString(language),
                "Groups"
                        + questionnaire.getLabel().substring(0, 1).toUpperCase() + questionnaire.getLabel().substring(1)
                        + language.substring(0, 1).toUpperCase() + language.substring(1)
                        + ".txt");
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
            stream.write(FileUtils.readFileToByteArray(file));
            return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
                    header, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping(value="/files/preferences/{language}/{id}")
    public ResponseEntity<ByteArrayResource> downloadPreferences(@PathVariable String language, @PathVariable long id) throws Exception {
        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, id);
        if (questionnaire == null)
            throw new NotFoundException("Not found questionnaire with id " + id);
        HSSFWorkbook workbook = FileCreator.createFileWithPreferences(questionnaire, Language.fromString(language));
        return createResponse(workbook,"preferences.xlsx");
    }
    @Transactional
    @GetMapping(value="/files/links/{id}")
    public ResponseEntity<ByteArrayResource> downloadLinks( @PathVariable long id) throws Exception {
        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, id);
        if (questionnaire == null)
            throw new NotFoundException("Not found questionnaire with id " + id);
        HSSFWorkbook workbook = FileWithLinksCreator.createFileWithLinks(questionnaire, Language.ENGLISH);
        return createResponse(workbook,questionnaire.getLabel()+"Links.xlsx");
    }
}
