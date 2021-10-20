package dbService;

import acccounts.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsersDao {
    private Session session;

    public UsersDao(Session session) {
        this.session = session;
    }

    public UsersDataSet get(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult());
    }

    public void insertUser(UsersDataSet user) throws HibernateException {
        session.save(user);
    }
}
