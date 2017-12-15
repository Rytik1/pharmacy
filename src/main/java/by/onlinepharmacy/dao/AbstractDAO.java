package by.onlinepharmacy.dao;

import by.onlinepharmacy.entity.Entity;
import by.onlinepharmacy.exception.DAOException;
import by.onlinepharmacy.pool.ProxyConnection;

import java.util.List;
import java.util.Optional;


public abstract class AbstractDAO<K, T extends Entity> {

    protected ProxyConnection connection;

    AbstractDAO(ProxyConnection connection) {
        this.connection = connection;
    }

    public abstract List<T> selectAll() throws DAOException;


    public abstract Optional<T> selectEntityByKey(K key) throws DAOException;


    public abstract boolean delete(K key) throws DAOException;

    public abstract boolean update(T entity) throws DAOException;

    public abstract K insert(T entity) throws DAOException;

}