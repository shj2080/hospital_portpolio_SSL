package util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Date;

//--단방향 암호화(SHA 256)로 암호화하면 복호화가 불가능(양방향 암호화(RSA) : 암호화->복호화)
public class SHA256 {
	//'Salt + 해싱'을 이용하여 비밀번호를 암호화(단, 비밀번호 찾기를 하면 신규로 8자리 랜덤비번을 보여줌)
	private static final String salt = "대구광역시"; //임시 소금
	
	public static String encodeSHA256(String password) { //password : 암호화되기 전 비번
		
		//암호화된 최종 비밀번호가 담길 변수
		String result = "";
		
		try {
			byte[] saltByte = salt.getBytes(); //salt를 바이트로 변경
			byte[] passwordByte = password.getBytes("utf-8"); //암호화되기 전 비번을 바이트로 변경
			
			/*
			 * 기본값으로 채워진 byte배열객체에 바이트로 변경된 소금값과 비번으로 채움
			 */
			byte[] saltPassword = new byte[saltByte.length + passwordByte.length];
			
			//소금값을 암호 앞, 뒤 어느쪽에 붙여넣어도 상관없다. 비밀번호 앞에 사용하는 게 더 일반적
			//void System.arraycopy(복사할 원본, 복사할 원본의 시작 index, 붙여넣을 도착지, 붙여넣을 도착지 시작index, 복사할 개수)
			System.arraycopy(saltByte, 0, saltPassword, 0, saltByte.length);
			System.arraycopy(passwordByte, 0, saltPassword, saltByte.length, passwordByte.length);
			//=> saltPassword [앞] 바이트로 변경된 salt값 [뒤] 바이트로 변경된 암호화하기 전 "비번"
			
			/* 'SHA-256알고리즘'을 사용하기 위해 먼저, import java.security.MessageDigest;
			 * new 생성자로 객체 생성하지 않고 아래 메서드로 객체 생성함
			 */			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			//매개값을 해싱하는 함수다. 이때 매개값은 바이트배열이어야 한다.
			md.update(saltPassword);
			
			byte[] saltPasswordDigest = md.digest();
			
			/* [StringBuffer/StringBuilder 클래스의 차이점]
			 * 1. 문자열 연산이 많고 '멀티쓰레드' 환경일 경우 : StringBuffer
			 * 2. 문자열 연산이 많고 '싱글쓰레드' 환경일 경우 : StringBuilder
			 * 
			 * 3. 문자열 연산이 적고 '싱글쓰레드' 환경일 경우 : String
			 */
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < saltPasswordDigest.length; i++) {
				//1.saltPasswordDigest의 하나하나의 byte를 16진수로 변경 ("16진수") -> 2.(문자열 "16진수") -> 3.StringBuffer에 추가
				/* byte를 16진수로 변경 이유?
				 * 기본적으로 데이터는 binary data로 전달된다.
				 * 16진수 배열로 만들 경우 데이터의 손실없이 유지할 수 있는 장점이 있다.
				 * 꼭 16진수로 변환하지 않고 Base64 같은 64진수로도 표현되기도 한다.
				 */
				sb.append(Integer.toString((saltPasswordDigest[i]&0xFF)+256, 16).substring(1));
			}

			//이 단계에서 StringBuffer의 문자열로 String객체가 만들어짐
			result = sb.toString();
			
		}catch(Exception e) {
			System.out.println("encodeSHA256() 처리 중 예외 : " + e);
		}
		
		//System.out.println("[debug]encodeSHA256() 암호화된 비밀번호:" + result);
		return result;
	}
	
	
	public static String getRandomPassword(int size) { //size가 8이면 8글자의 임시비밀번호가 생성
		//1. '영어대소문자+숫자+특수문자'가 결합된 8글자의 임시비밀번호
		/*
		char[] charSet = new char[] { 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
				'!', '@', '#', '$', '%', '^', '&' }; 
		*/
		
		//2. '영어대소문자+숫자'가 결합된 8글자의 임시비밀번호
		char[] charSet = new char[] { 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
				}; //특수문자 제외
		
		StringBuffer sb = new StringBuffer();
		SecureRandom sr = new SecureRandom();
		sr.setSeed(new Date().getTime());
		
		int len = charSet.length;
		//int idx = 0;
		for (int idx = 0; idx < size; idx++) {
			idx = sr.nextInt(len); //강력한 난수를 발생시키기 위해 SecureRandom 사용
			sb.append(charSet[idx]);
		}
		
		String randomPassword = sb.toString();
		System.out.println("getRandomPassword() 랜덤 비번 : " + randomPassword);
		return randomPassword;
		//return sb.toString(); //toString() 호출 하는 순간 String 객체 만들어짐
	}
}
