package org.martikan.mastore.userapi.controller;

import lombok.RequiredArgsConstructor;
import org.martikan.mastore.userapi.service.BaseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Abstract class for controllers.
 * @param <T> - Entity.
 * @param <D> - DTO.
 */
@RequiredArgsConstructor
public class BaseController<T, D> {

    private final BaseService<T, D> service;

    @GetMapping
    public ResponseEntity<List<D>> findAll(@RequestParam(name = "page", defaultValue = "0") final int page,
                                           @RequestParam(name = "size", defaultValue = "20") final int size,
                                           @RequestParam(name = "sort", defaultValue = "id") final String sort) {

        final var pageable = PageRequest.of(page, size, Sort.by(sort));

        final var allItem = service.findAll(pageable);

        if (allItem.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(allItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable("id") final Long id, @Valid @RequestBody final D dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PostMapping
    public ResponseEntity<D> save(@Valid @RequestBody final D dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable final Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
