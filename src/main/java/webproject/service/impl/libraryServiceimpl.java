package webproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webproject.mapper.UserMapper;
import webproject.model.User;
import webproject.service.libraryService;

/** 
* @author hts
* @version date：2017年4月13日 下午10:35:31 
* 
*/
@Service
public class libraryServiceimpl implements libraryService {
@Autowired
	UserMapper mapper;

@Override
public List findUser(User user) {
	// TODO Auto-generated method stub
	
	return mapper.findUser(user);
}
}
