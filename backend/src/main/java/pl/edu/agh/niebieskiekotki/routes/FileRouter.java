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
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.entitites.Vote;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.FileCreationFailedException;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.utility.FileCreator;
import pl.edu.agh.niebieskiekotki.utility.Language;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireResults;
import pl.edu.agh.niebieskiekotki.views.VoteView;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.List;

@CrossOrigin
@RestController
public class FileRouter {

    private final HibernateAdapter hibernateAdapter;

    public FileRouter(HibernateAdapter hibernateAdapter) {
        this.hibernateAdapter = hibernateAdapter;
    }

//    @GetMapping(value = "/files/preferences/{id}")
//    public ResponseEntity<byte[]> GetPreferencesFile(@PathVariable Long id)
//            throws NotFoundException, FileCreationFailedException {
//
//        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, id);
//        if (questionnaire == null)
//            throw new NotFoundException("Not found questionnaire with id " + id);
//
//        HSSFWorkbook workbook = FileCreator.createFileWithPreferences(questionnaire, Language.POLISH);
//
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        try {
//            workbook.write(bos);
//            bos.close();
//        } catch (IOException e) {
//            throw new FileCreationFailedException("Download failed, cause by error when converting file to bytes.");
//        }
//        byte[] bytes = bos.toByteArray();
//
//
//        HttpHeaders headers = new HttpHeaders();
//
//        // Here you have to set the actual filename of your pdf
//        String filename = "output.pdf";
//        headers.setContentDispositionFormData(filename, filename);
//        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
//    }

    @GetMapping(value="/files/preferences/{id}")
    public ResponseEntity<ByteArrayResource> downloadTemplate(@PathVariable long id) throws Exception {
        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, id);
        System.out.println(hibernateAdapter.getAll(Questionnaire.class));
        if (questionnaire == null)
            throw new NotFoundException("Not found questionnaire with id " + id);

        System.out.println("glosy: " + questionnaire.votes);

        HSSFWorkbook workbook = FileCreator.createFileWithPreferences(questionnaire, Language.POLISH);
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
