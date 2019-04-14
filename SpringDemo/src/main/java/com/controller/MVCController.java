package com.controller;

import com.model.User;
import org.springframework.web.bind.annotation.*;

@RestController    //@RestController=@Controller + @ResponseBody
//                    /RestController返回String+json ;    @Controller + @ResponseBody可以返回页面


public class MVCController {

    @RequestMapping(value = "boot/getUser",method = RequestMethod.GET)
  public  Object getUser(){
      User user=new User();
      user.setId(123);
      user.setName("张无忌");
      return user;
  }

    /**
     *
     * @return 只支持Get请求，@GetMapping = @RequestMapping + RequestMethod.GET
     */
    @GetMapping("boot/getUser1")
    public  Object getUser1(){
        User user=new User();
        user.setId(123);
        user.setName("张无忌");
        return user;
    }



    /**
     *
     * @return 只支持Get请求，@PostMapping = @RequestMapping + RequestMethod.POST
     */
    @PostMapping("boot/getUser2")
    public  Object getUser2(){
        User user=new User();
        user.setId(123);
        user.setName("张无忌");
        return user;
    }
}
