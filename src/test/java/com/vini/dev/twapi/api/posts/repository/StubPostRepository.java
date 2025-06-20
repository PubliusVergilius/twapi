package com.vini.dev.twapi.api.posts.repository;

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
    public Optional<Post> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    // Optional: For test initialization or clearing
    public void clear() {
        store.clear();
    }

    public void preload(Post... posts) {
        for (Post post : posts) {
            store.put(post.getId(), post);
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Post> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Post> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Post> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Post getOne(String s) {
        return null;
    }

    @Override
    public Post getById(String s) {
        return null;
    }

    @Override
    public Post getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends Post> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Post> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Post> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Post> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Post> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Post> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Post, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Post> S save(S entity) {
        String size = String.valueOf(store.size());
        entity.setId(size);
        store.put(size, entity);
        return entity;
    }

    @Override
    public <S extends Post> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<Post> findAll() {
        return List.of();
    }

    @Override
    public List<Post> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Post entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Post> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Post> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return null;
    }
}
