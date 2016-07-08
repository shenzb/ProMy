package com.hrricane.guava.function.pojo;

/**
 * Created by zhenbiao.shen on 2016/7/8.
 */
public class City {

        private String name;
        private String zipCode;
        private Integer population;
        private String climate;
        private double averageRainfall;

        public City(){}
        public City(String name){
            this(name,null,null);
        }

        public City(String name,String zipCode,Integer population){
            this.name=name;
            this.zipCode=zipCode;
            this.population=population;
        }
}
