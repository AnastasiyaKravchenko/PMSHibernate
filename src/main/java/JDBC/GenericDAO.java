package main.java.JDBC;

/**
 * Created by Mala on 3/29/2017.
 */
public interface GenericDAO<T,ID> {

    T getByID(ID id);
    void save (T entity);
    void update(T entity);
    void remove(T entity);
}
