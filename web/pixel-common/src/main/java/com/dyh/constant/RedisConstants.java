package com.dyh.constant;

/**
 * Redis相关常量
 *
 * @author pixel-revolve
 * @date 2022/11/20
 */
public class RedisConstants {
    public static final String UNIFORM_PREFIX="pixel:";
    public static final String LOGIN_CODE_KEY = UNIFORM_PREFIX+"login:code:";
    public static final Long LOGIN_CODE_TTL = 2L;
    public static final String LOGIN_USER_KEY = UNIFORM_PREFIX+"login:token:";
    public static final Long LOGIN_USER_TTL = 1440L;
    public static final Long SIGN_USER_TTL = 1440L;

    public static final Long CACHE_NULL_TTL = 2L;

    public static final Long CACHE_POST_TTL = 30L;
    public static final String CACHE_POST_KEY = UNIFORM_PREFIX+"cache:post:";

    public static final String LOCK_POST_KEY = UNIFORM_PREFIX+"lock:post:";
    public static final Long LOCK_POST_TTL = 10L;

    public static final String SECKILL_STOCK_KEY = UNIFORM_PREFIX+"seckill:stock:";
    public static final String BLOG_LIKED_KEY = UNIFORM_PREFIX+"blog:liked:";
    public static final String FEED_KEY = UNIFORM_PREFIX+"feed:";
    public static final String SHOP_GEO_KEY = UNIFORM_PREFIX+"shop:geo:";
    public static final String USER_SIGN_KEY = UNIFORM_PREFIX+"sign:";
}
