package com.latitude.xpression;

import com.latitude.xpression.core.context.EvaluationContext;

public interface Expression {

    public String asText();

    public boolean isBoolean(EvaluationContext evaluationContext);

    public Object evaluate(EvaluationContext evaluationContext);

    public <T> T evaluateAs(Class<T> type, EvaluationContext evaluationContext);

    public String evaluateAsText(EvaluationContext evaluationContext);

}
