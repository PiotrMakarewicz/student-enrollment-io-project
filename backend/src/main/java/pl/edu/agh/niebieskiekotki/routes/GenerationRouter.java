package pl.edu.agh.niebieskiekotki.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.Results;
import pl.edu.agh.niebieskiekotki.entitites.Teacher;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.UnauthorizedException;
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
    public ResponseEntity<Object> startQuestionnaireResultsGeneration(@RequestHeader("Auth-Token") String token, @PathVariable Integer id, @PathVariable Integer numGroups) throws UnauthorizedException, NotFoundException {

        Teacher teacher = AuthRoute.getTeacherFromToken(token, hibernateAdapter);

        if (hibernateAdapter.getWhereEq(Results.class, "questionnaire", id).size() > 0){
            hibernateAdapter.clearResultsWhere(id);
            //return new ResponseEntity<>("Results for this questionnaire have already been generated.", HttpStatus.BAD_REQUEST);
        }
        Questionnaire questionnaire = hibernateAdapter.getById(Questionnaire.class, id.longValue());

        if (questionnaire == null)
            throw new NotFoundException("There is no questionnaire with provided id.");

        if(questionnaire.getTeacher() == null || !questionnaire.getTeacher().equals(teacher))
            throw new UnauthorizedException("This questionare isn't yours");

        uploader.upload(questionnaire, generator.generate(questionnaire, numGroups));

        return new ResponseEntity<>("Successfully generated results for questionnaire.", HttpStatus.ACCEPTED);
    }
}
