package pl.edu.agh.niebieskiekotki;

import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public <T> List<T> getAll(Class<T> c) {
        closeTransaction();
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        List<T> resultList = getAll(c, session);

        transaction.commit();

        return resultList;
    }

    private <T> List<T> getAll(Class<T> c, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(c);

        Root<T> root = criteriaQuery.from(c);
        criteriaQuery.select(root);
        Query<T> query = getSession().createQuery(criteriaQuery);

        return query.getResultList();
    }


    public <T> void save(T itemToSave) {
        closeTransaction();
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        session.save(itemToSave);

        transaction.commit();

    }

    public void clearDatabase() {
        closeTransaction();
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        for (QuestionnaireTerm qt : getAll(QuestionnaireTerm.class))
            session.delete(qt);

        for (QuestionnaireAccess qa : getAll(QuestionnaireAccess.class))
            session.delete(qa);

        for (QuestionnaireResults qr : getAll(QuestionnaireResults.class))
            session.delete(qr);

        for (Questionnaire q : getAll(Questionnaire.class))
            session.delete(q);

        transaction.commit();

    }

    public void clearResultsWhere(long questionnaireId) {
        closeTransaction();
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        List<Results> allResults = getWhereEq(Results.class, "questionnaire", questionnaireId, session);
        for (Results qr : allResults) {
            session.delete(qr);
        }
        transaction.commit();
    }

    public void clearVotesWhere(long questionnaireId, long studentIndex) {
        closeTransaction();
        Session session = getSession();
        Transaction transaction = getTransaction(session);
        List<Vote> allVotes = getWhereEq(Vote.class, "questionnaire", questionnaireId, session);
        for (Vote qr : allVotes) {
            if (qr.getStudent().getIndexNumber() == studentIndex) {
                session.delete(qr);
            }
        }
        transaction.commit();
    }

    public <T> T getById(Class<T> c, Long id) {
        closeTransaction();
        List<T> results = getWhereEq(c, "id", id);
        if (results == null || results.size() == 0) return null;
        return results.get(0);
    }

    public <T, V> T getOneWhereEq(Class<T> c, String fieldName, V value) {
        closeTransaction();
        List<T> results = getWhereEq(c, fieldName, value);
        if (results == null || results.size() == 0) return null;
        return results.get(0);
    }

    public <T, V> List<T> getWhereEq(Class<T> c, String fieldName, V value) {
        closeTransaction();
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        var resultList = getWhereEq(c, fieldName, value, session);

        //transaction.commit();

        return resultList;
    }

    public <T, V> List<T> getWhereEq(Class<T> c, String fieldName, V value, Session session) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(c);
        Root<T> root = criteriaQuery.from(c);

        criteriaQuery.where(builder.equal(root.get(fieldName), value));
        criteriaQuery.select(root);
        Query<T> query = session.createQuery(criteriaQuery);
        var resultList = query.getResultList();

        return resultList;
    }

    protected Transaction getTransaction(Session session) {
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
        } catch (IllegalStateException e) {
            transaction = session.getTransaction();
        }
        return transaction;
    }

    public void closeTransaction(){
        Session session =getSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
        } catch (IllegalStateException e) {
            transaction = session.getTransaction();
        }
        transaction.commit();
    }
    protected Session getSession() {
        return entityManager.unwrap(Session.class).getSession();
    }

}
