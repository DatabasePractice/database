package webproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import webproject.model.PageData;


@Service
public interface BillQueryService {
	
	PageData queryProject(PageData pd);
}
