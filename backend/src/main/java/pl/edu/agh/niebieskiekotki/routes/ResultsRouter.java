package pl.edu.agh.niebieskiekotki.routes;


import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Results;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.views.AddResultView;

import java.util.List;

@CrossOrigin
@RestController
public class ResultsRouter {
    private final HibernateAdapter hibernateAdapter;

    public ResultsRouter(HibernateAdapter hibernateAdapter) {
        this.hibernateAdapter = hibernateAdapter;
    }

    @GetMapping(value = "/results")
    public List<Results> GetAll() {
        return hibernateAdapter.getAll(Results.class);
    }

    @GetMapping(value = "/results/{id}")
    public List<Results> GetOne(@PathVariable Long id) throws NotFoundException {
        List<Results> toReturn = hibernateAdapter.getWhereEq(Results.class, "questionnaire", id);
        if (toReturn.size() == 0) {
            System.out.println("Results: not found questionnaire with id " + id);
            throw new NotFoundException("Not found questionnaire with id:" + id);
        }

        return toReturn;
    }

    @PostMapping(value = "/results")
    public void Post(@RequestBody AddResultView addResultView) {
        Results newResult = new Results();
        newResult.getQuestionnaire().setId(addResultView.getQuestionnaireId());
        newResult.getTerm().setId(addResultView.getTermId());
        newResult.getStudent().setId(addResultView.getStudentId());

        hibernateAdapter.save(newResult);
    }
}
