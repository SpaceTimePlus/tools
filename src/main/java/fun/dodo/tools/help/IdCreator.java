package fun.dodo.tools.help;

import org.hashids.Hashids;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;


public final class IdCreator {

  private static final long START_DOT = 1484194326700000L;
  // 随机数生成器
  private static final Random RANDOM = new Random();
  private static final int RANDOM_MAX = 9999;
  private static final int RANDOM_LENGTH = 4;

  private static final Hashids HASHIDS = new Hashids("api");

  // 本机的地址段
  private static long hostSector;
  // 当前使用序号
  private static long speedPoint;
  // 当前作用区段
  private static long lastSector;

  // 持有唯一实例
  private static final IdCreator INSTANCE;

  // 获取单例实现
  public static IdCreator getInstance() {
    return INSTANCE;
  }

  // 静态初始化一个实例
  static {
    hostSector = 0L;
    speedPoint = 0L;
    lastSector = 0L;
    INSTANCE = new IdCreator();
  }

  // 默认构造函数
  private IdCreator() {
    try {
      String ip = InetAddress.getLocalHost().getHostAddress();
      ip = ip.replace(".", "");

      // 截取后 RANDOM_LENGTH 位
      final int length = ip.length();
      if (RANDOM_LENGTH < length) {
        ip = ip.substring(ip.length() - RANDOM_LENGTH);
      }
      hostSector = Long.parseLong(ip);
    } catch (final Exception ex) {
      hostSector = RANDOM.nextInt(RANDOM_MAX);
      System.out.println("读取本机 IP 地址失败, 使用随机数据生成器");
    }
  }

  /**
   * 根据 [时间戳], [IP地址], [序号池], 生成 ID
   * 1. X = 流逝的秒, 通常是10位
   * 2. Y = IP 地址段, 通常取3位
   * 3. Z = 在秒内的序号, 通常取3位
   */
  public static Long newId() {
    final long sector = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    if (lastSector == sector) {
      speedPoint++;
    } else {
      lastSector = sector;
      speedPoint = 1L;
    }

    // 1. 毫秒内产生的不超过 100 个的随机数
    // 2. 代表本机独立性的 RANDOM_LENGTH 位数字
    // 3. 当前时间的毫秒表达
    return speedPoint + (hostSector * 100L) + (sector * 1_000_000L) - START_DOT;
  }

  public static String encodeId() {
    return HASHIDS.encode(newId());
  }

  public static String encodeId(final long intId) {
    return HASHIDS.encode(intId);
  }

  public static long decodeId(final String strId) {
    return HASHIDS.decode(strId)[0];
  }

}
