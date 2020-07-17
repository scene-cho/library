package cf.scenecho.library.board.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidateCategoryAspect {
    @Around("@annotation(cf.scenecho.library.board.util.ValidateCategory)")
    public String validateCategory(ProceedingJoinPoint joinPoint) throws Throwable {
        String path = (String) joinPoint.getArgs()[0];
        if (!isValid(path)) return "404";
        return (String) joinPoint.proceed();
    }

    private boolean isValid(String path) {
        if (path.equals("notices")) return true;
        if (path.equals("opinions")) return true;
        return false;
    }
}
