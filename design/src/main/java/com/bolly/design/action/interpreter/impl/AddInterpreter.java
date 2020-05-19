package com.bolly.design.action.interpreter.impl;

import com.bolly.design.action.interpreter.Interpreter;

/**
 * 加法解析器
 * @author bolly
 */
public class AddInterpreter implements Interpreter {
    private Interpreter firstExpression, secondExpression;

    public AddInterpreter(Interpreter firstExpression, Interpreter secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    @Override
    public int interpret() {
        return this.firstExpression.interpret() + this.secondExpression.interpret();
    }

    @Override
    public String toString(){
        return "+";
    }
}
