package pl.edu.agh.niebieskiekotki;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        return results;
    }

    public <T> T getById(Class<T> c, Long id) {

        List<T> results = getWhereEq(c, "id", id);
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
        List<T> result = null;

        try {
            result = query.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return result;
    }

    public <T> void save(T itemToSave) {
        Session session = entityManager.unwrap(Session.class).getSession();
        session.getTransaction().begin();
        session.save(itemToSave);
        session.getTransaction().commit();
    }

}
