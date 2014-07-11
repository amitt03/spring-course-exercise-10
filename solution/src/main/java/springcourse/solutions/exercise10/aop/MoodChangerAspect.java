package springcourse.solutions.exercise10.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springcourse.solutions.exercise10.dao.api.IBookDao;
import springcourse.solutions.exercise10.dao.impl.BookUsuallyInMemoryDao2;

/**
 * @author Amit Tal
 * @since 4/30/2014
 */
@Aspect
public class MoodChangerAspect {

    private Logger logger = LoggerFactory.getLogger(MoodChangerAspect.class);

    private short maxRetries = 5;

    public MoodChangerAspect() {
    }

    public MoodChangerAspect(short maxRetries) {
        this.maxRetries = maxRetries;
    }

    @Around("Pointcuts.iBookDaoImplementations()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        short retryCounter = 0;
        IBookDao.MoodyException lastMoodyError = null;
        while (retryCounter++ < maxRetries) {
            try {
                Object result = pjp.proceed();
                return result;
            } catch (IBookDao.MoodyException ex) {
                logger.info("Caught a moody exception", ex.getMessage());
                lastMoodyError = ex;
            }
        }
        logger.info("Mood changer reties exhausted");
        return lastMoodyError;
    }
}