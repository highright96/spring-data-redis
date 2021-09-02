package dev.highright96.springdataredis.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

  @Pointcut("execution(* dev.highright96.springdataredis..*.*(..))") // aop 패키지 하위 모두 적용
  private void cut() {
  }

  @Pointcut("@annotation(dev.highright96.springdataredis.aop.Timer)") // @Timer 어노테이션이 적용된 모든 메서드
  private void enableTimer() {
  }

  @Around("cut() && enableTimer()")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    //시작 전
    StopWatch stopWatch = new StopWatch();
    String methodName = pjp.getSignature().getName();

    stopWatch.start();
    Object result = pjp.proceed(); // 시작

    //종료 후
    stopWatch.stop();
    log.info("Perform : [{}] {}ns", methodName, stopWatch.getLastTaskTimeNanos());

    return result;
  }
}
