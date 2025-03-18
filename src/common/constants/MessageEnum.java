package common.constants;

public enum MessageEnum {
    INPUT_PRODUCT_TITLE("\n====== 제품 등록 ======"),
    INPUT_PRODUCT_ID("제품 ID를 입력하세요."),
    INPUT_PRODUCT_NAME("제품 이름을 입력하세요."),
    INPUT_PRODUCT_CATEGORY("제품 카테고리를 입력하세요."),
    INPUT_PRODUCT_HEIGHT("높이를 입력하세요: "),
    INPUT_PRODUCT_WIDTH("너비를 입력하세요: "),
    INPUT_PRODUCT_PRICE("가격을 입력하세요: "),
    INPUT_PRODUCT_MANUFACTURER("제조사를 입력하세요: "),
    PRODUCT_REGISTER_SUCCESS("제품이 성공적으로 등록되었습니다.");

    private final String message;

    MessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
