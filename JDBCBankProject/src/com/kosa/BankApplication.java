package com.kosa;

import java.util.Scanner;

public class BankApplication {
	private static Scanner scanner = new Scanner(System.in);
	private static AccountDAO accountDAO = new AccountDAO();

	public static void main(String[] args) {
		boolean run = true;
		while (run) {
			System.out.println("----------------------------------------------------------");
			System.out.println("1.���»��� | 2.���¸�� | 3.���� | 4.��� | 5.����");
			System.out.println("----------------------------------------------------------");
			System.out.print("����> ");

			int selectNo = scanner.nextInt();

			if (selectNo == 1) {
				createAccount();
			} else if (selectNo == 2) {
				accountList();
			} else if (selectNo == 3) {
				deposit();
			} else if (selectNo == 4) {
				withdraw();
			} else if (selectNo == 5) {
				run = false;
			}
		}
		System.out.println("���α׷� ����");
	}

	private static void createAccount() {
		System.out.println("--------------");
		System.out.println("���»���");
		System.out.println("--------------");

		System.out.print("���¹�ȣ: ");
		String ano = scanner.next();

		System.out.print("������: ");
		String owner = scanner.next();

		System.out.print("�ʱ��Աݾ�: ");
		int balance = scanner.nextInt();

		AccountVO newAccount = new AccountVO(ano, owner, balance);
		accountDAO.accountInsert(newAccount);
	}

	private static void accountList() {
		System.out.println("--------------");
		System.out.println("���¸��");
		System.out.println("--------------");
		accountDAO.accountList();
	}

	private static void deposit() {
		System.out.println("--------------");
		System.out.println("����");
		System.out.println("--------------");
		
		System.out.print("���¹�ȣ: "); 
		String ano = scanner.next();
		
		System.out.print("���ݾ�: ");
		int money = scanner.nextInt();
		
		boolean isExist = findAccount(ano);		

		if(isExist == false) {
			System.out.println("���: ���°� �����ϴ�.");
			return;
		}
		
		AccountVO account = new AccountVO(ano, money);
		accountDAO.accountPlusUpdate(account);
	}

	private static void withdraw() {
		System.out.println("--------------");
		System.out.println("����");
		System.out.println("--------------");
		
		System.out.print("���¹�ȣ: "); 
		String ano = scanner.next();
		
		System.out.print("��ݾ�: ");
		int money = scanner.nextInt();
		
		boolean isExist = findAccount(ano);		

		if(isExist == false) {
			System.out.println("���: ���°� �����ϴ�.");
			return;
		}
		
		AccountVO account = new AccountVO(ano, money);
		accountDAO.accountMinusUpdate(account);		
	}

	private static boolean findAccount(String ano) {		
		return accountDAO.accountFindOne(ano);
	}
		
}

