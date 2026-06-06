package edu.ap.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import edu.ap.spring.service.WalletService;
import edu.ap.spring.transaction.Transaction;

@Component
@Aspect
public class BalanceAspect {
    @Around("@annotation(BalanceInterceptor) && execution(public * sendFunds(..))")
    public Transaction checkBalace(ProceedingJoinPoint joinPoint) throws Throwable {

        Transaction result = null;
        WalletService wallet = (WalletService) joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        float value = (float) args[1];
        float balance = wallet.getBalance();

        if (balance < value) {
            System.out.println("# Not Enough funds to send transaction. Transaction Discarded.");
        } else {
            result = (Transaction) joinPoint.proceed();
        }
        
        return result;
    }
}
