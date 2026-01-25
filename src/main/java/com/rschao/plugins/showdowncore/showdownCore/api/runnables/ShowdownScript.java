package com.rschao.plugins.showdowncore.showdownCore.api.runnables;

import java.util.function.Function;

public class ShowdownScript<E> {

    private final Function<Object[], E> runnable;
    private Object[] args;
    public ShowdownScript(Function<Object[], E> f, Object... args) {
        this.runnable = f;
        this.args = args;
    }

    public ShowdownScript(Function<Object[], E> runnable) {
        this.runnable = runnable;
    }
    public Object[] getArgs() {
        return args;
    }
    public void setArgs(Object... args) {
        this.args = args;
    }

    public E run() {
        return runnable.apply(args);
    }
}
