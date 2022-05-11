package pl.edu.agh.niebieskiekotki;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.QuestionnaireAccess;
import pl.edu.agh.niebieskiekotki.entitites.QuestionnaireTerm;
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

    public <T> List<T> getAll(Class<T> c) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(c);

        Root<T> root = criteriaQuery.from(c);
        criteriaQuery.select(root);
        Query<T> query = session.createQuery(criteriaQuery);
        List<T> results = query.getResultList();
        //session.close();
        session.disconnect();


        return results;
    }

    public void clearDatabase() {

        Session session = entityManager.unwrap(Session.class);
        session.getTransaction().begin();

        for(QuestionnaireTerm qt : getAll(QuestionnaireTerm.class))
            session.delete(qt);

        for(QuestionnaireAccess qa : getAll(QuestionnaireAccess.class))
            session.delete(qa);

        for(QuestionnaireResults qr : getAll(QuestionnaireResults.class))
            session.delete(qr);

        for(Questionnaire q : getAll(Questionnaire.class))
            session.delete(q);

        session.getTransaction().commit();
        session.disconnect();

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

        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(c);
        Root<T> root = criteriaQuery.from(c);

        criteriaQuery.where(builder.equal(root.get(fieldName), value));
        criteriaQuery.select(root);
        Query<T> query = session.createQuery(criteriaQuery);
        List<T> result = query.getResultList();
        //session.close();
        session.disconnect();

        return result;
    }

    public <T> void save(T itemToSave) {
        Session session = entityManager.unwrap(Session.class).getSession();
        session.getTransaction().begin();
        session.save(itemToSave);
        session.getTransaction().commit();
        //session.close();
        session.disconnect();
    }

}
