package com.zfans.service;

import com.zfans.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Zfans
 * @date 2020/5/6 19:18
 */
public interface TypeService {
    Type saveType(Type type);

    Type getTypeById(Long id);

    Page<Type> listType(Pageable pageable);

    void deleteTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> listType();
}
