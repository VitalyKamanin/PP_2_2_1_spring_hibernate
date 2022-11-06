package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.sql.Select;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
@PersistenceContext
   private Session session;

   @Override
   public void add(User user) {
      session.save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      return session.createQuery("from User").getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCar(int series, String model) {
      User user;
      try {
         Query query = session.createQuery("from User where car.model= :model and car.series = :series");
         query.setParameter("series", series);
         query.setParameter("model", model);
         user = (User) query.getSingleResult();
      } catch (NoResultException e) {
         user = null;
      }
      return user;
   }

}
