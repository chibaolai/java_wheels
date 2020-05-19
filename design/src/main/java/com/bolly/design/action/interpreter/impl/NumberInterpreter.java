package com.bolly.design.action.interpreter.impl;

import com.bolly.design.action.interpreter.Interpreter;

/**
 * @author bolly
 */
public class NumberInterpreter implements Interpreter {
    private int number;

    public NumberInterpreter(int number) {
        this.number = number;
    }

    public NumberInterpreter(String number) {
        this.number = Integer.parseInt(number);
    }

    @Override
    public int interpret() {
        return this.number;
    }
}
