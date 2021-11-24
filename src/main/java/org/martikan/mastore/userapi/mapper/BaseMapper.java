package org.martikan.mastore.userapi.mapper;

/**
 * Base interface for mappers.
 * @param <T> - Entity.
 * @param <D> - DTO.
 */
public interface BaseMapper<T, D> {

    D toDTO(T source);

    T toEntity(D source);

    T updateEntity(T source);

}
