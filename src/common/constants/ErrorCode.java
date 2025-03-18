package common.constants;

public enum ErrorCode {
    USER_UNAUTHORIZED("거래처 회원만 제품을 등록할 수 있습니다."),
    DATABASE_ERROR("데이터베이스 오류가 발생했습니다."),

    INPUT_WRONG_DATE("날짜 형식이 잘못되었습니다. YYYY-MM-DD 형식으로 입력하세요.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
