package pl.edu.agh.niebieskiekotki.routes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.niebieskiekotki.HibernateAdapter;
import pl.edu.agh.niebieskiekotki.entitites.Teacher;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.InvalidPasswordException;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NoProvideRequiredDataException;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.UnauthorizedException;
import pl.edu.agh.niebieskiekotki.views.TokenView;
import pl.edu.agh.niebieskiekotki.views.UserCredential;

@CrossOrigin
@RestController
public class AuthRoute {

    static String secret = "12345";//TODO: add this to env
    private final HibernateAdapter hibernateAdapter;

    public AuthRoute(HibernateAdapter hibernateAdapter) {
        this.hibernateAdapter = hibernateAdapter;
    }

    @PostMapping(value = "/auth")
    public TokenView GetAll(@RequestBody UserCredential credential) throws NoProvideRequiredDataException, NotFoundException, InvalidPasswordException {
        System.out.println("hello");
        if(credential.getEmail() == null || credential.getPassword() == null)
            throw new NoProvideRequiredDataException("Email and password is required");

        Teacher teacher = hibernateAdapter.getOneWhereEq(Teacher.class, "emailAddress", credential.getEmail());

        if(teacher == null)
            throw new NotFoundException("User with given email doesnt exist");

        String sha256hex = DigestUtils.sha256Hex(credential.getPassword());

        System.out.println(sha256hex);
        System.out.println(teacher.getPassword());

        if( !teacher.getPassword().equals(sha256hex) )
            throw new InvalidPasswordException("Teacher password and sended password are not equal");

        return new TokenView(doGenerateToken(teacher.getEmailAddress()));
    }

    static String getEmailFromToken(String token) {
        Claims claims =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    static Teacher getTeacherFromToken(String token, HibernateAdapter hibernateAdapter) throws UnauthorizedException {

        Teacher teacher = null;

        try{
            String email = getEmailFromToken(token);
            System.out.println(email);
            teacher = hibernateAdapter.getOneWhereEq(Teacher.class,"emailAddress", email);
        }catch (Exception e) {
            throw new UnauthorizedException("Invalid auth token");
        }
        return teacher;
    }


    private String doGenerateToken(String email) {
        return Jwts.builder().setSubject(email)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
