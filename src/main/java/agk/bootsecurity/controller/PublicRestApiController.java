package agk.bootsecurity.controller;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import agk.bootsecurity.db.UserRepository;
import agk.bootsecurity.exceptions.CustomException;
import agk.bootsecurity.model.User;
import antlr.StringUtils;

import java.util.List;
import java.util.Optional;
/*
 * Author - Anand K
 * This class provides rest API end point api/public
 * @CrossOrigin annotation is used as API can be called from outside the host
 * */
@RestController
@RequestMapping("api/public")
@CrossOrigin
public class PublicRestApiController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public PublicRestApiController(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder=new BCryptPasswordEncoder();
    }

    // Available to all authenticated users
    @GetMapping("test")
    public String test1(){
        return "API Test";
    }

    // Available to managers
    @GetMapping("management/reports")
    public String reports(){
        return "Some report data";
    }

    // Available to ROLE_ADMIN
    @GetMapping("admin/users")
    public List<User> users(){
        return this.userRepository.findAll();
    }
    
    //Add a user (Customer Registration etc)
    @PostMapping("/register")
    @ResponseBody
    public String Register(@RequestBody User user) {
     //User user1=new User(username,passwordEncoder.encode(password),role,permission)  ; 
    	if(user.getUsername().equals(""))
    		throw new CustomException("UserName is require field");
    	
  	user.setPassword(passwordEncoder.encode(user.getPassword()));
  	user.setActive(1);
    	userRepository.save(user);
    	return user.getUsername();
    }
    @PutMapping("/ChangePassword")
    @ResponseBody
    public String ChangePassword(@RequestBody User user)
    {
      //	user.setPassword(passwordEncoder.encode(user.getPassword()));
    User user1=userRepository.findByUsername(user.getUsername());
    user1.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user1);
    //	userRepository.setUserInfoById((int)user.getId(),user.getUsername(),user.getPassword());
    	return "Password changed";
    }
}
