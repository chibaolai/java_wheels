package com.bolly.design.action.interpreter.impl;

import com.bolly.design.action.interpreter.Interpreter;

/**
 * 乘法解析器
 * @author bolly
 */
public class MultiInterpreter implements Interpreter {
    private Interpreter firstExpression, secondExpression;

    public MultiInterpreter(Interpreter firstExpression, Interpreter secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    @Override
    public int interpret() {
        return this.firstExpression.interpret() * this.secondExpression.interpret();
    }

    @Override
    public String toString(){
        return "*";
    }

}
