package fun.dodo.tools.help;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RedisService<I, K, T> {
    String SERIAL_NUMBER_SUFFIX = "serial:number";

    // BASE64 编码器
    Encoder ENCODER = Base64.getEncoder();
    // BASE64 解码器
    Decoder DECODER = Base64.getDecoder();

    Gson GSON = new GsonBuilder()
            .enableComplexMapKeySerialization() // 支持Map的key为复杂对象的形式
            .serializeNulls()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setPrettyPrinting()
            .setVersion(1.0)
            .create();

    // ----------------------------------------------------------------------------------
    // 注意事项:
    //
    // id               获取标识
    // add              保存实体
    // get              语义上与 getById 相同
    // update           更新实体的定义/属性
    // delete       使用 ID 去删除实体
    // ----------------------------------------------------------------------------------

    // 获取标识
    long id();

    // 添加
    void add(final I id, final T obj);

    // 添加Map
    void addMap(final I id, final Map<K, T> map);

    // 添加List
    void addList(final I id, final List<T> map);

    // 读取
    Optional<T> get(final I id, final K key);

    // 更新
    void update(final I id, final K key, final T obj);

    // 删除
    void delete(final I id, final K key);

}