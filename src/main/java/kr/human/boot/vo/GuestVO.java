package kr.human.boot.vo;

import java.util.Date;

import lombok.Data;

@Data
public class GuestVO {
	private int idx;
	private String name;
	private String password;
	private String content;
	private Date regDate;
	private String ip ;
	
}
