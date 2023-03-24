package dao.face;

import java.util.List;

import dto.User;

public interface UserDao {
	
	/**
	 * 유저 전제 조회
	 * 
	 * @return - 조회된 테이블의 전체 행 목록
	 */
	List<User> selectAll();

	/**
	 * 새로운 유저 데이터 추가
	 * 
	 * @param insertUser - 추가할 userid, name을 입력
	 * @return int - 반환타입
	 * 		1 - insert 성공
	 * 		0 - insert 실패
	 */
	int insertUser(User insertUser);

	/**
	 * 
	 * 
	 * @param idx
	 * @return
	 */
	User selectByIdx(int idx);

	/**
	 * 입력받은 idx를 이용하여 유저 정보를 삭제
	 * 
	 * @param idx - 삭제할 idx의 번호
	 * @return 영향받은 행의 수
	 */
	int deleteByIdx(int idx);


}
