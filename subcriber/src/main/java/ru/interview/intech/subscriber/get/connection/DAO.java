package ru.interview.intech.subscriber.get.connection;

import org.hibernate.Session;

public class DAO<T>  {
    protected final Class<T> typeParameterClass;

    public DAO(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public void add(T object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }
}
