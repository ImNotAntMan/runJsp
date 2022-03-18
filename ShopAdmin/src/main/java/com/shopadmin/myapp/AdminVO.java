package com.shopadmin.myapp;

import java.util.Date;

import lombok.Data;

@Data
public class AdminVO {
	private String a_id;
	private String a_name;
	private String a_passwd;
	private Date a_rdate;
	private Date a_udate;
}
