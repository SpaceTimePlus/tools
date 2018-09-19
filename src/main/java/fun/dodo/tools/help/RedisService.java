package fun.dodo.tools.help;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RedisService<I, K, T> {
    String SERIAL_NUMBER_SUFFIX = "serial:number";

    long id();

    void add(final I id, final T obj);

    void addMap(final I id, final Map<K, T> map);

    void addList(final I id, final List<T> map);

    void addObj(final I id, final T obj);

    void addZset(final I id, final T obj);

    Optional<T> get(final I id, final K key);

    Optional<List<byte[]>> getList(final Long id, long index, long size);

    Optional<Long> getCount(final Long id);

    void update(final I id, final K key, final T obj);

    void delete(final I id, final K key);

}