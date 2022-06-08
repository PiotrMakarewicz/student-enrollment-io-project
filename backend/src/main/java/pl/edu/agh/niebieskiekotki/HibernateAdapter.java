package pl.edu.agh.niebieskiekotki;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import pl.edu.agh.niebieskiekotki.entitites.*;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireResults;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class HibernateAdapter {
    @PersistenceContext
    public EntityManager entityManager;


    public  <T> List<T> getAll(Class<T> c) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(c);

        Root<T> root = criteriaQuery.from(c);
        criteriaQuery.select(root);
        Query<T> query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }


    public <T> void save(T itemToSave) {
        Session session = getSession();
        session.save(itemToSave);
    }

    public <T> void delete(T item) {
        Session session = getSession();
        session.delete(item);
    }

    public void clearDatabase() {
        Session session = getSession();
        for (QuestionnaireTerm qt : getAll(QuestionnaireTerm.class))
            session.delete(qt);

        for (QuestionnaireAccess qa : getAll(QuestionnaireAccess.class))
            session.delete(qa);

        for (QuestionnaireResults qr : getAll(QuestionnaireResults.class))
            session.delete(qr);

        for (Questionnaire q : getAll(Questionnaire.class))
            session.delete(q);


    }

    public void clearResultsWhere(long questionnaireId) {
        Session session = getSession();
        List<Results> allResults = getWhereEq(Results.class, "questionnaire", questionnaireId);
        for (Results qr : allResults) {
            session.delete(qr);
        }
    }

    public void clearVotesWhere(long questionnaireId, long studentIndex) {
        Session session = getSession();
        List<Vote> allVotes = getWhereEq(Vote.class, "questionnaire", questionnaireId);
        for (Vote qr : allVotes) {
            if (qr.getStudent().getIndexNumber() == studentIndex) {
                session.delete(qr);
            }
        }
    }
    public void clearVoteById(long voteId){
        Session session = getSession();
        Vote vote = getOneWhereEq(Vote.class,"id",voteId);
        session.delete(vote);
    }

    public <T> T getById(Class<T> c, Long id) {
        List<T> results = getWhereEq(c, "id", id);
        if (results == null || results.size() == 0) return null;
        return results.get(0);
    }

    public <T, V> T getOneWhereEq(Class<T> c, String fieldName, V value) {
        List<T> results = getWhereEq(c, fieldName, value);
        if (results == null || results.size() == 0) return null;
        return results.get(0);
    }

    public <T, V> List<T> getWhereEq(Class<T> c, String fieldName, V value) {
        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(c);
        Root<T> root = criteriaQuery.from(c);

        criteriaQuery.where(builder.equal(root.get(fieldName), value));
        criteriaQuery.select(root);
        Query<T> query = session.createQuery(criteriaQuery);
        var resultList = query.getResultList();


        return resultList;
    }

    protected Session getSession() {
        return entityManager.unwrap(Session.class).getSession();
    }


}
