package com.bolly.design.create.singleton;

/**
 * @author bolly
 */
public class EngineProcessor {
    private static EngineProcessor engineProcessor;

    public static synchronized EngineProcessor getInstance() {
        if(engineProcessor == null) {
            engineProcessor = new EngineProcessor();
        }
        return engineProcessor;
    }
}
