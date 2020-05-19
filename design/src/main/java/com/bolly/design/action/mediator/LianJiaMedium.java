package com.bolly.design.action.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 链家中介
 * @author bolly
 */
public class LianJiaMedium implements Medium {
    private List<Customer> members = new ArrayList<Customer>();


    @Override
    public void register(Customer member) {
        if (!members.contains(member)) {
            members.add(member);
            member.setMedium(this);
        }
    }

    @Override
    public void relay(String from, String ad) {
        for (Customer member : members) {
            if(!member.getName().equals(from)) {
                member.receive(from,ad);
            }
        }
    }

    public static void main(String[] args) {
        Medium medium = new LianJiaMedium();
        Customer seller = new Seller("张三(卖方)");
        Customer buyer = new Buyer("李四(买方)");
        medium.register(seller);
        medium.register(buyer);
    }
}
