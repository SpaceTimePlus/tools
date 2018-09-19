package fun.dodo.tools.help;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RedisService<I, K, T> {

    // 获取标识
    long id();

    // 添加
    void add(final I id, final T obj);

    // 添加Map
    void addMap(final I id, final Map<K, T> map);

    // 添加List
    void addList(final I id, final List<T> map);

    // 添加List
    void addToList(final I id, final T obj);

    // 读取
    Optional<T> get(final I id, final K key);

    // 更新
    void update(final I id, final K key, final T obj);

    // 删除
    void delete(final I id, final K key);

}