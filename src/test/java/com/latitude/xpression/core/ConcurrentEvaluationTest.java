package com.latitude.xpression.core;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.awaitility.Awaitility;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.latitude.xpression.Expression;
import com.latitude.xpression.ExpressionParser;
import com.latitude.xpression.core.context.EvaluationContext;

public class ConcurrentEvaluationTest extends ExpressionTestSupport {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrentEvaluationTest.class);

    @Test
    public void shouldEvaluateConcurrently() {

        final ExpressionParser parser = new ExpressionParser();
        final ExecutorService executor = Executors.newFixedThreadPool(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            executor.submit(
                    new EvaluationTask(parser, String.format("%s >= %s", random.nextInt(20), random.nextInt(20))));
        }
        executor.shutdown();
        Awaitility.await().until(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return executor.isTerminated();
            }
        });
    }

    @Test()
    public void shouldThrowExceptionOnConcurrentEvaluation() throws InterruptedException, ExecutionException {

        final ExecutorService executor = Executors.newFixedThreadPool(3);
        final Expression expression = newExpression(
                "(x >= y) && (1+4 >= 2) && y+2 < x && ((x >= y) && (1+4 >= 2) || y+2 < x) && (x >= y) || (1+4 >= 2) && y+2 < x && ((x >= y) && (1+4 >= 2) || y+2 < x)");
        final EvaluationContext evaluationContext = newEvaluationContext();
        final Random random = new Random();

        for (int i = 0; i < 3; i++) {
            Future<?> future = executor.submit(new Runnable() {
                @Override
                public void run() {
                    evaluationContext.putVariable("x", random.nextInt(10));
                    evaluationContext.putVariable("y", random.nextInt(10));
                    Boolean result = expression.evaluateAs(Boolean.class, evaluationContext);
                    logger.debug(String.format("%s evaluates %s with %s, result %s", Thread.currentThread().toString(),
                            expression.asText(), evaluationContext.getVariableContext(), result));
                }
            });
            future.get();
        }
        executor.shutdown();
        Awaitility.await().dontCatchUncaughtExceptions().until(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return executor.isTerminated();
            }
        });
    }

    public final static class EvaluationTask implements Runnable {

        private final ExpressionParser parser;

        private final String expression;

        public EvaluationTask(ExpressionParser parser, String expression) {
            this.parser = parser;
            this.expression = expression;
        }

        @Override
        public void run() {
            Boolean result = parser.evaluateAs(expression, Boolean.class);
            logger.debug(String.format("%s evaluates %s with result %s", Thread.currentThread().toString(), expression,
                    result));
        }

        public EvaluationTask putVariable(String name, Object value) {
            parser.putVariable(name, value);
            return this;
        }

    }

}
