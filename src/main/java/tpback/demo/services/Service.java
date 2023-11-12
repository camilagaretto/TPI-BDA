package tpback.demo.services;

import java.util.List;

public interface Service <T,ID>{

        T add(T entity);

        void update(T entity);

        void delete(ID id);

        T getById(ID id);

        List<T> getAll();
    }

