package com.example.demo.jwt;

import java.io.InputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.util.Date;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Exception.CustomTokenExpiredException;
import com.example.demo.Exception.SpringBlogException;
import com.example.demo.model.User;
import java.security.cert.CertificateException;

 //import com.google.api.client.util.Value;

@Component
public class JwtHelper {

	static final String issuer = "myApp";
//	
	private JWTVerifier accessTokenVerifier, refreshTokenVerifier;
//
	@Value("${jwt.access.token.expiration.in.ms}")
	private String accessTokenExpiration;
//
	@Value("${jwt.refresh.token.expiration.in.ms}")
	private String refreshTokenExpiration;

	private KeyStore keyStore;
	
	@PostConstruct
	public void init() {
		
		try {
			keyStore = KeyStore.getInstance("JKS");
			InputStream resourceAsStream = getClass().getResourceAsStream("/rms.jks");
			keyStore.load(resourceAsStream, "password".toCharArray());

			accessTokenVerifier = JWT.require(Algorithm.HMAC512(getPrivateKey().toString())).withIssuer(issuer).build();
			refreshTokenVerifier = JWT.require(Algorithm.HMAC512(getPrivateKey().toString())).withIssuer(issuer)
					.build();
		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
			throw new SpringBlogException("Exception occured while loading keystore");
		}
	}

	public String generateAccessToken(User user) {
		final Date createdDate = new Date();
		final Date expirationDate = calculateAccessTokenExpirationDate(createdDate);
		return JWT.create().withIssuer(issuer).withSubject(user.getEmail()).withIssuedAt(createdDate)
				.withExpiresAt(expirationDate).sign(Algorithm.HMAC512(getPrivateKey().toString()));
	}
//
	public String generateRefreshToken(User user, Long tokenId) {
		final Date createdDate = new Date();
		final Date expirationDate = calculateRefreshTokenExpirationDate(createdDate);
		return JWT.create().withIssuer(issuer).withSubject(user.getEmail()).withClaim("tokenId", tokenId)
				.withIssuedAt(createdDate).withExpiresAt(expirationDate)
				.sign(Algorithm.HMAC512(getPrivateKey().toString()));
	}
//
	
	private Optional<DecodedJWT> decodeAccessToken(String token) {
		try {
			return Optional.of(accessTokenVerifier.verify(token));
		} catch (JWTVerificationException e) {
			System.out.println("Exception occured in JwtHelper: decodeAccessToken: " + e.getClass().getName());
			throw new CustomTokenExpiredException("ooppss");
		}
//		return Optional.empty();
	}
	
	private Optional<DecodedJWT> decodeRefreshToken(String token) {
		try {
			System.out.println("===decodeRefreshToken refresh token: " + token);
			return Optional.of(refreshTokenVerifier.verify(token));
		} catch (JWTVerificationException e) {
			System.out.println("Exception Occureddd: " + e.getClass().getName());
		}
		return Optional.empty();
	}
	
	
	public boolean validateAccessToken(String token) {
		return decodeAccessToken(token).isPresent();
	}
//
	public boolean validateRefreshToken(String token) {
		System.out.println("===Validating refresh token: " + token);
		return decodeRefreshToken(token).isPresent();
	}
//
	public String getEmailFromAccessToken(String token) {
		return decodeAccessToken(token).get().getSubject();
	}
//
	public String getEmailFromRefreshToken(String token) {
		System.out.println("the token from getUsernameFromRefreshToken: " + token);
		return decodeRefreshToken(token).get().getSubject();
	}
//
	public Long getTokenIdFromRefreshToken(String token) {
		return decodeRefreshToken(token).get().getClaim("tokenId").asLong();
	}
//
	private Date calculateAccessTokenExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() + Long.parseLong(accessTokenExpiration));
//		return new Date(createdDate.getTime() + Integer.parseInt(accessTokenExpiration));
	}
//
	private Date calculateRefreshTokenExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() + Long.parseLong(refreshTokenExpiration));
		//return new Date(createdDate.getTime() + Integer.parseInt(refreshTokenExpiration));
	}
	
	private PrivateKey getPrivateKey() {
		try {
			return (PrivateKey) keyStore.getKey("springblog", "password".toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
			throw new SpringBlogException("Exception occured while retrieving private key from keystore");
		}
	}
}
