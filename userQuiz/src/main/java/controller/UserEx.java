package controller;

import java.util.List;
import java.util.Scanner;

import dao.face.UserDao;
import dao.impl.UserDaoImpl;
import dto.User;

public class UserEx {

	private static UserDao userDao = new UserDaoImpl(); // 동작되도록 바꾸세요!!
	
	public static void main(String[] args) {
		
		///////////////////////
		// --- 전체 출력 --- //
		///////////////////////
		List<User> list = userDao.selectAll();
		
		System.out.println("----- 전체 출력 -----");
		for(User u : list) {
			System.out.println(u);
		}
		System.out.println("---------------------");
		
		
		
		/////////////////////
		//--- 유저 삽입 ---//
		/////////////////////

		// 유저 객체를 생성하고
		// 유저 정보를 객체에 입력하고
		//DB에 삽입되도록 구현하세요!!
		
		//	userId : A123
		//	name : Alice
		
		User insertUser = new User();
		Scanner sc = new Scanner(System.in);

		System.out.println("사용자 아이디?");
		insertUser.setUserid( sc.nextLine() );
		System.out.println("사용자 이름?");
		insertUser.setName( sc.nextLine() );
		
		int insert_User = userDao.insertUser(insertUser);
		System.out.println("사용자 이름");
		
		if (insert_User > 0) {
			System.out.println("데이터 삽입 완료");
		} else {
			System.out.println("데이터 삽입 실패");
		}
		
		
		/////////////////////
		//--- 유저 조회 ---//
		/////////////////////
		System.out.println("조회하실 유저의 idx를 입력해주세요");
		User selectUser = userDao.selectByIdx( sc.nextInt() );
		if (selectUser.equals(null) ) {
			System.out.println("조회하실 유저가 존재하지 않습니다");
		} else {
			System.out.println(selectUser);
		}

		
		/////////////////////
		//--- 유저 삭제 ---//
		/////////////////////
		
		System.out.println("삭제하실 idx를 입력해주세요");
		int delete_User = userDao.deleteByIdx( sc.nextInt() );
		
		if (delete_User == 0) {
			System.out.println("데이터 삭제 완료");
		} else {
			System.out.println("데이터 삭제 실패");
		}
		
		//////////////////////////////////////////////////////
		// 유저 조회 - 삭제됐는지 확인하는 용도(null나와야함)
		//////////////////////////////////////////////////////
		User resultUser = userDao.selectByIdx(20);
		System.out.println(resultUser);
		
		
		
		
		
	}
}
