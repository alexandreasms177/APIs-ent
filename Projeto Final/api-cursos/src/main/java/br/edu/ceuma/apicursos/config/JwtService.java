package br.edu.ceuma.apicursos.config;
import io.jsonwebtoken.*;import io.jsonwebtoken.security.Keys;import javax.crypto.SecretKey;import java.nio.charset.StandardCharsets;import java.time.Instant;import java.util.Date;
import org.springframework.beans.factory.annotation.Value;import org.springframework.stereotype.Component;
@Component public class JwtService {
 private final SecretKey key; private final long ttl;
 public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.expiration-seconds}") long ttl){if(secret==null||secret.length()<32)throw new IllegalStateException("Defina JWT_SECRET com pelo menos 32 caracteres");this.key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));this.ttl=ttl;}
 public String emitir(String subject,String perfil){Instant now=Instant.now();return Jwts.builder().subject(subject).claim("perfil",perfil).issuedAt(Date.from(now)).expiration(Date.from(now.plusSeconds(ttl))).signWith(key).compact();}
 public Claims validar(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();}
}
