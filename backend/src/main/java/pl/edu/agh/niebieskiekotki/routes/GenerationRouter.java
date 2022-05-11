package pl.edu.agh.niebieskiekotki.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.Results;
import pl.edu.agh.niebieskiekotki.groups.GroupGenerator;
import pl.edu.agh.niebieskiekotki.groups.ResultUploader;

@CrossOrigin
@RestController
public class GenerationRouter {
    @Autowired
    private HibernateAdapter hibernateAdapter;

    @Autowired
    private GroupGenerator generator;

    @Autowired
    private ResultUploader uploader;

    @GetMapping("/generate_results/{id}/{numGroups}")
    public ResponseEntity<Object> startQuestionnaireResultsGeneration(@PathVariable Integer id, @PathVariable Integer numGroups){
        if (hibernateAdapter.getWhereEq(Results.class, "questionnaire", id).size() > 0){
            return new ResponseEntity<>("Results for this questionnaire have already been generated.", HttpStatus.BAD_REQUEST);
        }
        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, id.longValue());
        if (questionnaire == null) {
            return new ResponseEntity<>("There is no questionnaire with provided id.", HttpStatus.NOT_FOUND);
        }

        uploader.upload(questionnaire, generator.generate(questionnaire, numGroups));

        return new ResponseEntity<>("Successfully generated results for questionnaire.", HttpStatus.ACCEPTED);
    }
}
