package springcourse.solutions.exercise10.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Amit Tal
 * @since 4/30/2014
 */
@Aspect
public class MethodStartAspect {

    private Logger logger = LoggerFactory.getLogger(MethodStartAspect.class);

    @Before("myPointcut()")
    public void logMethodStart(JoinPoint jp) {
        String targetClassName = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        Object[] methodArguments = jp.getArgs();
        logger.info("===> Starting {}.{}() with arguments: [{}]", targetClassName, methodName, methodArguments);
    }

    @Pointcut("Pointcuts.entireProject() && !Pointcuts.restControllersOnly()")
    public void myPointcut() {}
}
