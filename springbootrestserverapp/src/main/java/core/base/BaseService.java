package core.base;

import core.Constants;
import core.RepositoryRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(Constants.Services.BASE_SERVICE_NAME)
public abstract class BaseService<T extends BaseEntity> {
    @Autowired
    public RepositoryRegistry repositories;

    public abstract Iterable<T> getAll();
    public abstract Optional<T> getById(long id);
    public abstract T save(T entity);
    public abstract void delete(T entity);

}
