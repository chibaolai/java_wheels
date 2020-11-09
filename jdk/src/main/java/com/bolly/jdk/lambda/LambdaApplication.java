package com.bolly.jdk.lambda;

import com.bolly.support.utils.JacksonUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaApplication {

    public static void main(String[] args) {
        LambdaApplication application = new LambdaApplication();
        application.listToMap();
        application.filter();
        application.typeConversion();
        application.groupingBy();
        application.sum();
        application.dd();
        application.maxMin();
    }

    /**
     * List 转Map
     */
    private void listToMap() {
        List<Item> itemList = initList();

        Map<Long, Item> itemMap = itemList.parallelStream().collect(Collectors.toMap(Item::getId, item -> item, (k1, k2) -> k1));

        System.out.printf("List转map"+JacksonUtils.marshal(itemMap.get(1)));
    }

    /**
     * 过滤
     */
    private void filter() {
        List<Item> itemList = initList();
        List<Item> itemFilter = itemList.parallelStream().filter(item -> item.getId() != null).filter(item -> item.getId() > 3).collect(Collectors.toList());
        System.out.printf("过滤"+itemFilter.get(0).getName());
    }

    /**
     * 转换
     */
    private void typeConversion() {
        List<Item> itemList = initList();
        List<Item2> longList = itemList.parallelStream().map(e-> new Item2().setName(e.name).setPrice(e.getPrice())).collect(Collectors.toList());
        System.out.printf("类型转换"+longList.get(0));
    }

    /**
     * 分组
     */
    private void groupingBy() {
        List<Item> itemList = initList();
        Map<Long, List<Item>> itemMap = itemList.parallelStream().collect(Collectors.groupingBy(Item::getId));
        System.out.printf("分组"+ JacksonUtils.marshal(itemMap.get(1)));
    }

    /**
     * 求和
     */
    private void sum() {
        List<Item> itemList = initList();
        BigDecimal totalBigDecimal = itemList.parallelStream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        Double totalNum = itemList.parallelStream().mapToDouble(Item::getId).sum();
        System.out.printf("价格求和"+totalBigDecimal);
        System.out.printf("ID求和"+totalNum);
    }


    /**
     * 去重
     */
    public void dd() {
        List<Item> itemList = initList();
        List<Item> unique = itemList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingLong(Item::getId))), ArrayList::new));
        System.out.printf("源个数"+itemList.size()+"去重后个数"+unique.size());
    }


    /**
     * 最大 最小值
     */
    public void maxMin() {
        List<Item> itemList = initList();
        Optional<Item> maxDish = itemList.stream().collect(Collectors.maxBy(Comparator.comparing(Item::getId)));
        maxDish.ifPresent(System.out::println);

        Optional<Item> minDish = itemList.stream().collect(Collectors.minBy(Comparator.comparing(Item::getId)));
        minDish.ifPresent(System.out::println);
    }


    private List<Item> initList() {
        Item item = new Item();
        item.setId(Long.valueOf(1));
        item.setName("apple");
        item.setPrice(BigDecimal.ONE);
        Item item2 = new Item();
        item2.setId(Long.valueOf(2));
        item2.setName("banana");
        item2.setPrice(BigDecimal.valueOf(2));
        Item item3 = new Item();
        item3.setId(Long.valueOf(3));
        item3.setName("orage");
        item3.setPrice(BigDecimal.valueOf(3));
        Item item4 = new Item();
        item4.setId(Long.valueOf(4));
        item4.setName("grape");
//        item4.setPrice(BigDecimal.valueOf(4));
        Item item5 = new Item();
        item5.setId(Long.valueOf(1));
        item5.setName("apple");
        item5.setPrice(BigDecimal.ONE);
        return Stream.of(item, item2, item3, item4, item5).collect(Collectors.toList());
    }

    class Item {
        private Long id;
        private String name;
        private BigDecimal price;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }

    class Item2 {
        private String name;
        private BigDecimal price;

        public String getName() {
            return name;
        }

        public Item2 setName(String name) {
            this.name = name;
            return this;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public Item2 setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }
    }
}
