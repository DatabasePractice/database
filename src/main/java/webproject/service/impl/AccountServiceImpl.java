package webproject.service.impl;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webproject.mapper.BillMapper;
import webproject.mapper.ChargeMapper;
import webproject.mapper.CustomMapper;
import webproject.mapper.PaymentMapper;
import webproject.model.PageData;
import webproject.service.AccountService;
import webproject.service.BillService;
import webproject.service.UserService;
import webproject.utils.Const;
import webproject.utils.ObjectExcelRead;
@Transactional
@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	BillMapper billmapper;
	@Autowired
	CustomMapper custommapper;
	@Autowired
	ChargeMapper chargemapper;
	@Autowired
	PaymentMapper paymentmapper;
	@Autowired
	UserService userService;
	String tel;
	double total;
	double balance;
	int customerid;

	public void Updateamount(PageData pd) throws Exception
	{   
		
		
		
		
		double cost = pd.getAsDouble("cost");
		tel= pd.getString("TEL");
		PageData person = null;
		person = custommapper.findByTel(tel);
		customerid= person.getAsInt("CUSTOM_ID");
		//payment表插入
		pd.put("CUSTOM_ID", customerid);
		Calendar calander=Calendar.getInstance();
		pd.put("TIME",calander.getTime() );
		
		int EMPLOYEE_ID=userService.findUserIDByAccount(pd);
		pd.put("EMPLOYEE_ID", EMPLOYEE_ID);
		pd.put("AMOUNT", cost);
		paymentmapper.save(pd);
		balance=person.getAsDouble("BALANCE");
		total=balance+cost;
		
		for (PageData minusdata:billmapper.findByCustomId(person)) {
			 double chargeamount = 0 ;
			 double haspaid=minusdata.getAsDouble("HASPAID_AMOUNT");
			 int id= minusdata.getAsInt("BILL_ID");
			 double TOTAL_AMOUNT=minusdata.getAsDouble("TOTAL_AMOUNT");
			 double needToPay=TOTAL_AMOUNT-haspaid;
			boolean status ;      
			if (total==0) break;     //已经没有余额了，退出
			if (needToPay==0) continue;     //已缴清，跳过
			if(total<needToPay)
			{
			chargeamount=total ;
			haspaid=haspaid+total;
			total=0;
			status= false;
		
			}
			else {
			chargeamount = needToPay;    //未交清金额
			total = total-needToPay;
			status= true;
			haspaid=minusdata.getAsDouble("TOTAL_AMOUNT");    //已付清账单，账单haspaid 为账单总额
			}
			
		//插入一条记录到charge表 chargeamount 即是应该插入的值，其他字段（custom id,billid）也要插
			PageData pd_charge=new PageData();
			pd_charge.put("CUSTUM_ID", customerid);
			pd_charge.put("CHARGE_AMOUNT", chargeamount);
			pd_charge.put("BILL_ID", id);
			chargemapper.insert(pd_charge);
			
		//更新bill表对应的行 （status,haspaid）  //一行代表一个bill,即for循环当前bill对应的那列
			PageData paybill=new PageData();
			paybill.put("HASPAID_AMOUNT",haspaid);
			paybill.put("STATUS",status);
			paybill.put("BILL_ID", id);
		
			billmapper.updateBill(paybill);
		}
		
		person.put("BALANCE", total);
		custommapper.edit(person);
		// 修改custom表余额为total 
		}
	}	
