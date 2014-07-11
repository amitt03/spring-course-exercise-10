package springcourse.solutions.exercise10.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Amit Tal
 * @since 4/30/2014
 */
public class Pointcuts {

    @Pointcut("execution(* springcourse.solutions.exercise10..*.*(..)))")
    public void entireProject() {}

    @Pointcut("@target(org.springframework.web.bind.annotation.RestController)")
    public void restControllersOnly() {}

    @Pointcut("execution(* springcourse.solutions.exercise10.dao.api.IBookDao.*(..)))")
    public void iBookDaoImplementations() {}
}
