package com.geekbrains.book.store.beans;


import com.geekbrains.book.store.beans.ServiceAspect;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class Filler {

    @Autowired
    private ServiceAspect serviceAspect;

    public List fillMapForSumAspect() {
        List<Map<String, Integer>> mapsList = new ArrayList<>();
        mapsList.add(serviceAspect.getMap());
        return mapsList;
    }
}
