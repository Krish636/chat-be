package com.chat.jwt;

import com.chat.utility.AppConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.Date;

/**
 * this class will handle generation of the token for secured transactions
 * through out the microservices
 *
 */

@NoArgsConstructor
public class JwtTokenProvider {

    /**
     * This is the secret key generation which will be used for
     * issuing the self signed authentication
     */

    private static final String secretKey = Base64.getEncoder().encodeToString(AppConstants.SECRET_KEY.getBytes());

    //****************************************************************
    // Public Method(s)
    //****************************************************************

    /**
     * This method to generate jwt with sign for the secured transaction through out all microservices
     *
     * @param uid
     * @param user should be either huid or uuid
     * @return jwt
     */
    public static String generateToken(final int uid, final String user) {
        return Jwts.builder()
                .setSubject(user)
                .claim(user, uid)
                .setIssuer(AppConstants.VALUE_USER)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
