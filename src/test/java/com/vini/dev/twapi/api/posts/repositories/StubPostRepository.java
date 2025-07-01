package com.vini.dev.twapi.api.posts.repositories;

import com.vini.dev.twapi.api.posts.domain.Post;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Repository
@Profile("test")
public class StubPostRepository implements PostRepository {
    private final Map<String, Post> store = new HashMap<>();

    @Override
    public Optional<Post> findById(final String id) {
        return Optional.ofNullable(this.store.get(id));
    }

    @Override
    public boolean existsById(final String s) {
        return false;
    }

    // Optional: For test initialization or clearing
    public void clear() {
        this.store.clear();
    }

    public void preload(final Post... posts) {
        for (final Post post : posts) {
            this.store.put(post.getId(), post);
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Post> S saveAndFlush(final S entity) {
        return null;
    }

    @Override
    public <S extends Post> List<S> saveAllAndFlush(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(final Iterable<Post> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(final Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Post getOne(final String s) {
        return null;
    }

    @Override
    public Post getById(final String s) {
        return null;
    }

    @Override
    public Post getReferenceById(final String s) {
        return null;
    }

    @Override
    public <S extends Post> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Post> List<S> findAll(final Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Post> List<S> findAll(final Example<S> example, final Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Post> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Post> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Post> boolean exists(final Example<S> example) {
        return false;
    }

    @Override
    public <S extends Post, R> R findBy(final Example<S> example, final Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Post> S save(final S entity) {
        final String size = String.valueOf(this.store.size());
        entity.setId(size);
        this.store.put(size, entity);
        return entity;
    }

    @Override
    public <S extends Post> List<S> saveAll(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<Post> findAll() {
        return List.of();
    }

    @Override
    public List<Post> findAllById(final Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(final String s) {

    }

    @Override
    public void delete(final Post entity) {

    }

    @Override
    public void deleteAllById(final Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(final Iterable<? extends Post> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Post> findAll(final Sort sort) {
        return List.of();
    }

    @Override
    public Page<Post> findAll(final Pageable pageable) {
        return null;
    }
}
