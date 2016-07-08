package com.hrricane.guava.function.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhenbiao.shen on 2016/7/8.
 */
public class State {

        private String name;
        private String code;
        private String region;
        private Set<City> mainCities=new HashSet<City>();

        public State(){}

        public State(String name){
            this.name=name;
        }

    public Set<City> getMainCities() {
        return mainCities;
    }

    public void addCity(String  city) {
       this.mainCities.add(new City(city));

    }
}
