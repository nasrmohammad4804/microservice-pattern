package com.nasr.core.base.mapper;

/**
 *
 * @param <D> as domain
 * @param <E> as entity
 * @param <C> as command
 */
public interface BaseMapper<D,E,C> {

    D convertEventToEntity(E event);

}
