package com.jobby.getajob.controller;

import com.jobby.getajob.data.GifRepository;
import com.jobby.getajob.model.Gif;
import groovy.transform.AnnotationCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HomepageController {

    @Autowired //Tells Spring to automatically construct this object. Possible because the class type is marked with '@Component' and recognized by the framework
    private GifRepository gifRepository;

    @RequestMapping(value = "/")
    public String displayStuff(ModelMap modelMap) {
        List<Gif> allGifs = gifRepository.getAllGifs();
        modelMap.put("gifs", allGifs);
        return "home";
    }

    @RequestMapping(value = "/gif/{name}")
    public String gifDetails(@PathVariable String name, ModelMap modelMap) {
        Gif gif = gifRepository.findByName(name);
        modelMap.put("gif", gif);
        return "gif-details";
    }

}
