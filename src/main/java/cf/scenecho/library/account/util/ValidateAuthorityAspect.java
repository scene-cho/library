package cf.scenecho.library.account.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidateAuthorityAspect {
    @Around("@annotation(ValidateAuthority)")
    public String validateCategory(ProceedingJoinPoint joinPoint) throws Throwable {
        String path = (String) joinPoint.getArgs()[0];
        if (!isValid(path)) return "404";
        return (String) joinPoint.proceed();
    }

    private boolean isValid(String path) {
        if (path.equals("members")) return true;
        if (path.equals("admins")) return true;
        return false;
    }
}
