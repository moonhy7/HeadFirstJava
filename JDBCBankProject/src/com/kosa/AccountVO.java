package com.kosa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountVO {
	private String ano;
	private String owner;
	private double balance;
	
	public AccountVO(String ano, double balance) {
		super();
		this.ano = ano;
		this.balance = balance;
	}
}
