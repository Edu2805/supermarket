package br.com.amorim.supermarket.service.common.genericcrudservice;

import org.springframework.data.domain.Page;

public interface CrudServiceCommon<T, ID> {

    Page<T> getAll(int page, int size);
    T findById(ID id);
    T save (T t);
    void update (T t, ID id);
    void delete (ID id);
}
