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
    PRODUCT_REGISTER_SUCCESS("제품이 성공적으로 등록되었습니다."),

    INPUT_INCOMING_TITLE("\n====== 입고신청 ======"),
    INPUT_INCOMING_PRODUCT_ID("입고할 제품 ID를 입력하세요."),
    INPUT_INCOMING_COUNT("입고할 제품 수량을 입력하세요."),
    INPUT_INCOMING_DATE("입고할 날짜를 입력하세요. (YYYY-MM-DD)"),
    INCOMING_REQUEST_SUCCESS("입고요청이 성공적으로 완료되었습니다."),
    NO_INCOMING_LIST("요청된 입고신청이 없습니다."),
    INPUT_INCOMING_APPROVE("승인할 입고번호를 입력하세요."),
    INCOMING_APPROVE_SUCCESS("입고요청을 성공적으로 승인하였습니다."),

    INPUT_OUTGOING_TITLE("\n====== 출고신청 ======"),
    SHOW_STOCK_TITLE("\n========== 재고 목록 =========="),
    INPUT_OUTGOING_STOCK_NUM("출고할 재고 번호를 입력하세요."),
    INPUT_OUTGOING_COUNT("출고할 물품의 개수를 입력하세요."),
    INPUT_OUTGOING_DATE("출고할 날짜를 입력하세요. (YYYY-MM-DD)"),
    NO_OUTGOING_LIST("요청된 출고신청이 없습니다."),
    INPUT_OUTGOING_APPROVE("승인할 출고번호를 입력하세요."),
    OUTGOING_REQUEST_SUCCESS("출고요청이 성공적으로 완료되었습니다."),
    OUTGOING_APPROVE_SUCCESS("출고요청을 성공적으로 승인하였습니다.");

   /* INPUT_RENT_HISTORY_TITLE("\n====== 임대 신청 ======"),
    INPUT_WAREHOUSE("\"원하는 창고번호를 선택하세요: "),
    INPUT_SECTOR("원하는 섹터를 선택하세요: "),
    INPUT_RENT_PERIOD1("\n======임대 기간 선택======"),
    INPUT_RENT_PERIOD2("1개월 | 3개월 | 6개월 | 12개월"),
    INPUT_RENT_PERIOD3("원하는 임대기간을 입력하세요: "),
    INPUT_RENT_START_DATE("원하는 임대 시작일을 입력하세요: 예) 2025-03-21"),
    SHOW_RENT_HISTORY_LAST("\n========최종 선택 내역=========="),
    더 만들어야됨


   */

    private final String message;

    MessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
