package webproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import webproject.mapper.BillMapper;
import webproject.model.PageData;

public interface AccountService {
	void Updateamount(PageData pd) throws Exception;
}
