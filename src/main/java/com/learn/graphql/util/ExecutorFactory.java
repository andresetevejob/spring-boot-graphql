package com.learn.graphql.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;

public final class ExecutorFactory {

  private ExecutorFactory(){}

  public static Executor newExecutor() {
    var realExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    var securityDelegatingExecutor = new DelegatingSecurityContextExecutorService(realExecutor);
    return CorrelationIdPropagationExecutor.wrap(securityDelegatingExecutor);
  }

}
