package common.utils;

import common.constants.ErrorCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ValidationUtil {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // 아이디: 영어(대소문자) + 숫자만 가능, 최소 4자 이상, 최대 20자
    public static boolean isValidUserId(String userId) {
        return userId != null && userId.matches("^[a-zA-Z0-9]{4,20}$");
    }

    // 이름: 한글만 가능, 최소 2자 이상, 최대 10자
    public static boolean isValidName(String name) {
        return name != null && name.matches("^[가-힣]{2,10}$");
    }

    // 비밀번호: 모든 문자(영어, 한글, 숫자 등) 가능, 최소 4자 이상
    public static boolean isValidPassword(String password) {
        return password != null && password.matches("^.{4,}$");
    }

    // 전화번호: 10자리 또는 11자리 숫자만 가능 (하이픈 허용, 예: 0101234567, 01012345678, 010-1234-5678, 010-123-4567)
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("^01[0-9]-?(\\d{3,4})-?(\\d{4})$");
    }

    // 이메일: 이메일 형식 (예: 2@2 도 허용)
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+$");
    }

    // 주소: 한글 + 숫자만 가능 (도로명 주소 또는 지번 주소 형식)
    public static boolean isValidAddress(String address) {
        return address != null && address.matches("^[가-힣0-9\\s]+$");
    }

    // 0보다 큰 숫자
    public static boolean isValidPositiveNumber(int number) {
        return number > 0;
    }

    // 숫자만 입력가능하게 확인하는 메서드
    public static int getValidPositiveNumber(String message) {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println(message);
                int value = Integer.parseInt(sc.nextLine().trim());
                if (!ValidationUtil.isValidPositiveNumber(value)) {
                    System.out.println(ErrorCode.INVALID_NUMBER.getMessage());
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println(ErrorCode.INVALID_NUMBER_FORMAT.getMessage());
            }
        }
    }

    public static Date isValidDate(String dateStr) {
        try {
            return DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            System.out.println(ErrorCode.INPUT_WRONG_DATE.getMessage());
            return null;
        }
    }
}
