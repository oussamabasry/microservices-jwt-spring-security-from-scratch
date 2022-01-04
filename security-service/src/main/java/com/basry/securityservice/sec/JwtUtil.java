package com.basry.securityservice.sec;



public class JwtUtil {
    public static final String SECRET_KEY = "9@AK$kT9KEtdqTT$8iT35I%jaYH4d3%Xl19cCSVQ3QjbqsV8x#R3wt$6XnCFk76Ib7mfdQ1h8Mq$Mr3zbbZNryZ1M4a1n@fBB#YA";
    public static final String AUTH_HEADER = "Authorization";
    public static final long EXPIRE_ACCESS_TOKEN = 120000; // 2 min
    public static final long EXPIRE_REFRESH_TOKEN = 300000; // 5 min
    public static final String PREFIX = "Bearer ";
    public static final String REFRESH_TOKEN_ROUTE = "/refreshToken";
    public static final String USER_NAME_FIELD = "username";
    public static final String PASSWORD_FIELD = "password";

}
