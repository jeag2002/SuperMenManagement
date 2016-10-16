package com.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/PayworkExam/rest/superhero")
public class MicroServiceController {
	
	private static List<SuperHeroBean> superHeroArray = new ArrayList<SuperHeroBean>();

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody SuperHeroData hello() throws Exception {
    	SuperHeroData sHD = new SuperHeroData("trufaman","detect trufas");
        return sHD;
    }
    
    @RequestMapping(value = "/{index}", method=RequestMethod.GET)
    public @ResponseBody SuperHeroData hellobyindex(@PathVariable int index) throws Exception {
    	SuperHeroData sHD = new SuperHeroData("trufaman","detect trufas");
        return sHD;
    }
    
    
    
    @RequestMapping(value= "/list", method=RequestMethod.GET)
	public @ResponseBody List<SuperHeroBean> getListOfSuperHeros() throws Exception{
		return superHeroArray;
	}
	
    @RequestMapping(value = "/query/{name}", method=RequestMethod.GET)
	public @ResponseBody SuperHeroBean getSuperHeroData(@PathVariable String name) throws Exception {
		
		SuperHeroBean res = new SuperHeroBean();
		res.setName(name);
		
		int index = superHeroArray.indexOf(res);
		if (index != -1){
			res = superHeroArray.get(index);
		}
		
		return res;
    }	
    
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public @ResponseBody String setSuperHeroData(@RequestBody SuperHeroBean sHB) throws Exception {
		String res = "";
		try{	
			int index = superHeroArray.indexOf(sHB);
			if (index != -1){
				SuperHeroBean sHBMain = superHeroArray.get(index);
				sHBMain.setSuperHeroBean(sHB);
				superHeroArray.set(index, sHBMain);
				res = "Superhero (" + sHB.getName() + ") Modified";
			}else{
				superHeroArray.add(sHB);
				res = "Superhero (" + sHB.getName() + ") Added";
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			res = "Cannot Insert (" + sHB.getName() + ")";
		}
		
		return res;
	}
    


}
