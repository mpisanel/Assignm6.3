package com.pharmacy.repository.settings;
import java.util.Set;
/**
 * Created by SONY on 2016-04-21.
 */
public interface Repository<E, ID>
{
    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}
