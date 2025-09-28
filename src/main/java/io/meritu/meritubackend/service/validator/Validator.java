package io.meritu.meritubackend.service.validator;

public interface Validator<T> {

    void validatePersist(T entity);
    void validateUpdate(T entity);
}
