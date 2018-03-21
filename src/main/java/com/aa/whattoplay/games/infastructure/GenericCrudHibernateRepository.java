package com.aa.whattoplay.games.infastructure;

import com.aa.ddd.common.domain.AbstractEntity;
import com.aa.ddd.common.domain.AbstractHibernateDao;
import com.aa.ddd.common.domain.IGenericCrudDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericCrudHibernateRepository<T extends AbstractEntity>  extends AbstractHibernateDao<T> implements IGenericCrudDao<T> {

}
