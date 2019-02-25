package core.base;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public interface DataConverter<A, B> extends Function<A, B> {
    default List<B> convertToList(final List<A> source) {
        return source.stream().map(this::apply).collect(toList());
    }
}
