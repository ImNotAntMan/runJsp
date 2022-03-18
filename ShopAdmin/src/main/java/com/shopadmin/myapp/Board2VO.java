package com.shopadmin.myapp;

import java.util.Date;

import lombok.Data;

@Data
public class Board2VO {
	private int b_num;
	private String b_subject;
	private String b_name;
	private String b_contents;
	private String b_file;
	private String b_filehash;
	private Date b_date;

}
