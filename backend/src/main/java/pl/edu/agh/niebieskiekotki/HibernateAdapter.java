package pl.edu.agh.niebieskiekotki;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateAdapter {
    @PersistenceContext
    public static EntityManager entityManager;


    static public <T> List<T> getAll(Class<T> c){
        System.out.println(entityManager);
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(c);

        Root<T> root = criteriaQuery.from(c);
        criteriaQuery.select(root);
        Query<T> query = session.createQuery(criteriaQuery);
        List<T> results = query.getResultList();
        System.out.println("Query results: " + results);
        return  results;
    }

    static public <T> T getById(Class<T> c, Long id){

        List<T> results = HibernateAdapter.getWhereEq(c, "id", id);

        if(results == null || results.size() == 0 ) return null;
        return results.get(0);
    }

    static public <T, V> List<T> getWhereEq(Class<T> c, String fieldName , V value ){

        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(c);
        Root<T> root = criteriaQuery.from(c);

        criteriaQuery.where( builder.equal(root.get(fieldName),value));
        criteriaQuery.select(root);
        Query<T> query = session.createQuery(criteriaQuery);
        List<T> result = null;

        try{
            result = query.getResultList();

        }catch (NoResultException e){}

        return  result;
    }

    static public <T> T save(T itemToSave){

        Session session =  entityManager.unwrap(Session.class).getSession();
        session.getTransaction().begin();
        session.save(itemToSave);
        session.getTransaction().commit();

        return itemToSave;
    }

}
