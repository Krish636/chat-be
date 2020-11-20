package com.chat.jwt;



import com.chat.utility.AppConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.util.Base64;

/**
 * This class will be used to verify the self signed token for secured transactions
 * through all microservices
 *
 */
public class JwtTokenChecker {

    //****************************************************************
    // Class Attributes
    //***************************************************************
    private static final String secretKey = Base64.getEncoder().encodeToString(AppConstants.SECRET_KEY.getBytes());

    //****************************************************************
    // Public Method(s)
    //****************************************************************

    /**
     * this method will consume the jwt and return respective uid after extraction
     *
     * @param token
     * @param user  should be either huid or uuid
     * @return uid
     */
    public static String authenticate(final String token, final String user) {
        final Jws<Claims> jwsClaims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        return Integer.toString((Integer) jwsClaims.getBody().get(user));
    }
}
