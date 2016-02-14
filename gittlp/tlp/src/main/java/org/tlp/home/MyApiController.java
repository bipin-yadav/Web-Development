package org.tlp.home;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * MyApiController class for all Rest-API services.
 *
 * @author      Bipin Yadav
 * @version     0.1
 * @see
 */
@Controller
public class MyApiController {
	
	
	/** 
     * Convert process method
     *
     * By url /post json object send to company, having the same structure.
	 * By @ResponseBody returning the json to angular.
	 * By @RequestBosy  accepting the json object.
     * @param 			convert    
     * @return          true if all conversion process completed successfully. 
     *               	false if any type of error or exception occurred during
     *               	the conversion process.
     * @see             MyViewController
     * @version         0.1
     */	
	@RequestMapping(value = "/getDashboard", method = RequestMethod.POST ,produces="application/json" )	
	public @ResponseBody JSONObject getDashboardByPostCall()   {				 			 
	 
		//db call
		JSONObject jobj = new JSONObject();
		jobj.put("user","port");
		String data = jobj.toString();		
		System.out.println("in MyViewController method.");		
		
		return jobj;
	}

	
}
