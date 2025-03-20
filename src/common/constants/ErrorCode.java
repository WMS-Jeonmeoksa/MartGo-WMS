package common.constants;

public enum ErrorCode {
    USER_UNAUTHORIZED("거래처 회원만 제품을 등록할 수 있습니다."),
    DATABASE_ERROR("데이터베이스 오류가 발생했습니다."),

    INPUT_WRONG_DATE("날짜 형식이 잘못되었습니다. YYYY-MM-DD 형식으로 입력하세요."),
    NO_PRODUCT_INCOMING("입고할 제품의 ID가 존재하지 않습니다. "),

    NO_INCOMINGNUM_APPROVE("승인할 입고번호를 잘못입력하셨습니다."),
    NO_OUTGOINGNUM_APPROVE("승인할 출고번호를 잘못입력하셨습니다."),

    INVALID_NUMBER("유효하지 않은 숫자입니다. 1이상의 숫자를 입력하세요."),
    INVALID_NUMBER_FORMAT("숫자 형식이 잘못되었습니다. 다시 입력하세요.");



    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
