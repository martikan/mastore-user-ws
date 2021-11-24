package org.martikan.mastore.userapi.service;

import lombok.RequiredArgsConstructor;
import org.martikan.mastore.userapi.exception.ResourceNotFoundException;
import org.martikan.mastore.userapi.mapper.BaseMapper;
import org.martikan.mastore.userapi.repository.BaseRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract class for services.
 * @param <T> - Entity.
 * @param <D> - DTO.
 */
@RequiredArgsConstructor
public abstract class BaseService<T, D> {

    private final BaseRepository<T> repository;

    private final BaseMapper<T, D> mapper;

    public List<D> findAll(final Pageable pageable) {
        return repository.findAll(pageable)
                .stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public D findById(final Long id) {
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Entity", "id", id));
    }

    public D update(final Long id, final D dto) {
        return repository.findById(id)
                .map(existed -> {
                    existed = mapper.updateEntity(mapper.toEntity(dto));
                    return save(mapper.toDTO(existed));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Entity", "id", id));
    }

    public D save(final D dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public void deleteById(final Long id) {
        repository.deleteById(id);
    }

}