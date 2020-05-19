package com.bolly.design.action.mediator;

public class Buyer extends Customer {

    public Buyer(String name) {
        super(name);
        createWindow(350, 100);
    }

    @Override
    protected void send(String ad) {
        receiveArea.append("我(买方)说: " + ad + "\n");
        //使滚动条滚动到最底端
        receiveArea.setCaretPosition(receiveArea.getText().length());
        //中介转发
        medium.relay(name,ad);
    }

    @Override
    protected void receive(String from, String ad) {
        receiveArea.append(from + "说: " + ad + "\n");
        //使滚动条滚动到最底端
        receiveArea.setCaretPosition(receiveArea.getText().length());
    }
}
