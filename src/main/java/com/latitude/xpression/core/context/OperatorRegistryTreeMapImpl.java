package com.latitude.xpression.core.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.latitude.xpression.core.el.ast.Operator;
import com.latitude.xpression.core.el.ast.UnaryOperator;
import com.latitude.xpression.support.ToStringBuilder;

public class OperatorRegistryTreeMapImpl extends TreeMap<String, Operator> implements OperatorRegistry {

    private static final long serialVersionUID = -7384222198162010623L;

    private Map<String, String> aliases;

    public OperatorRegistryTreeMapImpl() {
        super(String.CASE_INSENSITIVE_ORDER);
        aliases = new HashMap<String, String>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.latitude.governor.expression.impl.OperatorRegistry#register(com.
     * latitude.governor.expression.core.evaluation.Operator)
     */
    @Override
    public AddOperation register(Operator operator) {
        return new AddOperationImpl(operator);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.latitude.governor.expression.impl.OperatorRegistry#tryResolveOperator (java.lang.String)
     */
    @Override
    public Optional<Operator> tryResolveOperator(String key) {
        return Optional.ofNullable(get(key));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.latitude.governor.expression.impl.OperatorRegistry#containsKey(java. lang.Object)
     */
    @Override
    public boolean containsKey(Object key) {
        if (super.containsKey(key)) {
            return true;
        }
        else {
            return aliases.containsKey(key);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.latitude.governor.expression.impl.OperatorRegistry#get(java.lang. Object)
     */
    @Override
    public Operator get(Object key) {
        Operator operator = super.get(key);
        if (operator == null) {
            if (aliases.containsKey(key)) {
                String operatorName = aliases.get(key);
                return super.get(operatorName);
            }
            return null;
        }
        else {
            return operator;
        }
    }

    public class AddOperationImpl implements AddOperation {

        private final String operatorKey;

        public AddOperationImpl(Operator operator) {
            String operatorKey = operator.getName();
            if (operator instanceof UnaryOperator) {
                operatorKey = String.format("u%s", operator.getName());
            }
            this.operatorKey = operatorKey;
            put(this.operatorKey, operator);
        }

        public AddOperationImpl withAlias(String alias) {
            aliases.put(alias, operatorKey);
            return this;
        }

    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, false);
        for (Map.Entry<String, Operator> entry : this.entrySet()) {
            builder.append(entry.getKey(), entry.getValue());
        }
        return builder.toString();
    }

}
