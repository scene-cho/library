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
    private static final StringBuilder sb = new StringBuilder();

    @Around("execution(* cf.scenecho.library..*(..))")
    Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodInfo = extractMethodInfo(joinPoint);

        sb.setLength(0);
        for (Object arg : joinPoint.getArgs()) sb.append(arg.toString()).append(" | ");
        logger.debug("[ IN ] " + methodInfo + " [ args ] " + sb.toString());

        Object ret = joinPoint.proceed();

        sb.setLength(0);
        if (ret != null) sb.append(ret);
        logger.debug("[ OUT ] " + methodInfo + " [ return ] " + sb.toString());

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
