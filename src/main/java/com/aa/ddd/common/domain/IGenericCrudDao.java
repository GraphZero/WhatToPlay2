package com.aa.ddd.common.domain;

import java.util.List;

public interface IGenericCrudDao<T extends AbstractEntity> {

    T findById(final Long id);

    List<T> findAll();

    void save(final T entity);

    void update(final T entity);

    void delete(final T entity);

    void deleteById(final Long entityId);

    void setClazz(Class< T > clazzToSet );

}
