package cf.scenecho.library.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DebugLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(DebugLogAspect.class);

    @Around("execution(* cf.scenecho.library..*(..))")
    Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodInfo = extractMethodInfo(joinPoint);

        logger.debug("[ I N ] " + methodInfo);
        for (Object arg : joinPoint.getArgs()) logger.debug("  [ arg ] " + arg.toString());

        Object ret = joinPoint.proceed();

        if (ret != null) logger.debug("  [ ret ] " + ret);
        logger.debug("[ OUT ] " + methodInfo);

        return ret;
    }

    private String extractMethodInfo(ProceedingJoinPoint joinPoint) {
        String[] tokenizedSignature = joinPoint.getSignature().toString().split("[^a-zA-Z0-9_(),]");
        String returnType = tokenizedSignature[0];
        String className = tokenizedSignature[tokenizedSignature.length - 2];
        String methodNameAndParams = tokenizedSignature[tokenizedSignature.length - 1];
        return returnType + " " + className + "." + methodNameAndParams;
    }
}
