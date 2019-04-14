package dubbo.springboot.service;

import dubbo.springboot.model.User;
//Springboot集成dubbo项目接口
public interface UserService {
    public String sayHi(String name);

    public User getUser(String id);
}
