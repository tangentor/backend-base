package cache;

import constant.CacheConst;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 所有缓存类的父类，封装一些基本的增删查改
 *
 * @author TangXi
 * @param <T>
 */
@RequiredArgsConstructor
public abstract class BaseCache<T> {


    protected final RedisTemplate<String, T> redisTemplate;

    protected static String ROOT_KEY = CacheConst.ROOT_KEY + ":";


    /**
     * <p>子类需要填写的该领域的key</p>
     * <p>如填 ShareFile</p>
     * 最后效果为：ROOT_KEY:ShareFile:xxx
     *
     * @return 该领域的key
     */
    protected abstract String domain();

    protected String getKey() {
        return ROOT_KEY + domain();
    }

    /**
     * 当传入的为null时代表就是领域本身，不涉及到下一级
     *
     * @param key 下一级的key
     * @return 返回是否存在该key
     */
    public boolean exist(String key) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        return Boolean.TRUE.equals(redisTemplate.hasKey(newKey));
    }

    /**
     * 当传入的为null时代表就是领域本身，不涉及到下一级
     *
     * @param key 下一级的key
     * @return 缓存中对应Key的数据
     */
    public T get(String key) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        return redisTemplate.opsForValue().get(newKey);
    }

    /**
     * 当传入的为null时代表就是领域本身，不涉及到下一级
     *
     * @param key   下一级的key
     * @param value 需要存的值
     * @return 是否存储成功
     */
    public boolean set(String key, T value) {
        return set(key, value, -1);
    }

    /**
     * 当传入的为null时代表就是领域本身，不涉及到下一级
     *
     * @param key   下一级的key
     * @param value 需要存的值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return 是否存储成功
     */
    public boolean set(String key, T value, long time) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        redisTemplate.opsForValue().set(newKey, value);
        if (time > 0) {
            redisTemplate.expire(newKey, time, TimeUnit.SECONDS);
        }
        return true;
    }

    /**
     * 当传入的为null时代表就是领域本身，不涉及到下一级
     *
     * @param key 下一级的key
     * @return 返回删除的条目数量
     */
    public boolean remove(String key) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        return Boolean.TRUE.equals(redisTemplate.delete(newKey));
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return 增加后的值
     */
    public Long increase(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        String newKey = getKey() + (key == null ? "" : ":" + key);
        return redisTemplate.opsForValue().increment(newKey, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return 减少后的值
     */
    public Long decrease(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        String newKey = getKey() + (key == null ? "" : ":" + key);
        return redisTemplate.opsForValue().increment(newKey, -delta);
    }

    /**
     * HashSet
     *
     * @param key   键 不能为null
     * @param item  项 不能为null
     * @return 值
     */
    public Object hashSet(String key, String item, Object value) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        redisTemplate.opsForHash().put(newKey, item, value);
        return value;
    }

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hashGet(String key, String item) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        return redisTemplate.opsForHash().get(newKey, item);
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hashDel(String key, Object... item) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        redisTemplate.opsForHash().delete(newKey, item);
    }

    /**
     * 获取list缓存的全部内容
     *
     * @param key 键
     * @return List<T> 对应范围内的值
     */
    public List<T> lGetAll(String key) {
        return lGet(key, 0, -1);
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return List<T> 对应范围内的值
     */
    public List<T> lGet(String key, long start, long end) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        return redisTemplate.opsForList().range(newKey, start, end);
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return 长度
     */
    public Long lGetListSize(String key) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        return redisTemplate.opsForList().size(newKey);
    }

    /**
     * 通过索引获取list中的值
     *
     * @param key   键
     * @param index 索引
     * @return 对应索引的值
     */
    public T lGetIndex(String key, long index) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        return redisTemplate.opsForList().index(newKey, index);
    }

    /**
     * 将value放入list缓存
     *
     * @param key   键
     * @param value 值
     * @return 是否成功
     */
    public boolean lSet(String key, T value) {
        return lSet(key, value, -1);
    }

    /**
     * 将value放入list缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 是否成功
     */
    public boolean lSet(String key, T value, long time) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        redisTemplate.opsForList().rightPush(newKey, value);
        if (time > 0) {
            redisTemplate.expire(newKey, time, TimeUnit.SECONDS);
        }
        return true;
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return 是否成功
     */
    public boolean lSet(String key, List<T> value) {
        return lSet(key, value, -1);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 是否成功
     */
    public boolean lSet(String key, List<T> value, long time) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        redisTemplate.opsForList().rightPushAll(newKey, value);
        if (time > 0) {
            redisTemplate.expire(newKey, time, TimeUnit.SECONDS);
        }
        return true;
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return 是否成功
     */
    public boolean lUpdateIndex(String key, long index, T value) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        redisTemplate.opsForList().set(newKey, index, value);
        return true;
    }

    /**
     * 移除N个值为value的元素
     *
     * @param key    键
     * @param count  移除个数
     * @param values 值
     * @return 移除的个数
     */
    @SafeVarargs
    public final Long lRemove(String key, long count, T... values) {
        String newKey = getKey() + (key == null ? "" : ":" + key);
        return redisTemplate.opsForList().remove(newKey, count, values);
    }
}
