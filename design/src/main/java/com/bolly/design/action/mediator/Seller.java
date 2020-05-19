package com.bolly.design.action.mediator;

public class Seller extends Customer {

    public Seller(String name) {
        super(name);
        createWindow(50, 100);
    }

    @Override
    protected void send(String ad) {
        receiveArea.append("我(卖方)说: " + ad + "\n");
        receiveArea.setCaretPosition(receiveArea.getText().length());
        medium.relay(name,ad);
    }

    @Override
    protected void receive(String from, String ad) {
        receiveArea.append(from + "说: " + ad + "\n");
        //使滚动条滚动到最底端
        receiveArea.setCaretPosition(receiveArea.getText().length());
    }
}
