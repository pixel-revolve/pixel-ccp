package com.dyh.constant;

/**
 * Redis相关常量
 *
 * @author pixel-revolve
 * @date 2022/11/20
 */
public class RedisConstants {
    public static final String UNIFORM_PREFIX="pixel:";

    // 用户模块
    public static final String LOGIN_CODE_KEY = UNIFORM_PREFIX+"login:code:";
    public static final String REDIS_ID_KEY = UNIFORM_PREFIX+"icr:";
    public static final Long LOGIN_CODE_TTL = 2L;
    public static final String LOGIN_USER_KEY = UNIFORM_PREFIX+"login:token:";
    public static final Long LOGIN_USER_TTL = 1440L;
    public static final String USER_SIGN_KEY = UNIFORM_PREFIX+"sign:";
    public static final Long SIGN_USER_TTL = 1440L;
    public static final String FOLLOW_KEY = UNIFORM_PREFIX+"follows:";

    // 文章模块
    public static final Long CACHE_NULL_TTL = 2L;
    public static final Long CACHE_POST_TTL = 30L;
    public static final String CACHE_POST_KEY = UNIFORM_PREFIX+"cache:post:";
    public static final String LOCK_POST_KEY = UNIFORM_PREFIX+"lock:post:";
    public static final Long LOCK_POST_TTL = 10L;
    public static final String POST_LIKED_KEY = UNIFORM_PREFIX+"post:liked:";
    public static final String POST_COLLECTED_KEY = UNIFORM_PREFIX+"post:collected:";
    public static final String POST_LIKE_RANK_KEY=UNIFORM_PREFIX+"post:rank:like:";

    // feed流
    public static final String FEED_KEY = UNIFORM_PREFIX+"feed:";

    // 购物车
    public static final String CART_KEY =UNIFORM_PREFIX+"cart:";


}
