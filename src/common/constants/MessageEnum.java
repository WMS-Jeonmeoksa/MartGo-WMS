package common.constants;

public enum MessageEnum {
    INPUT_PRODUCT_TITLE("\n========== 제품 등록 =========="),
    INPUT_PRODUCT_ID("제품 ID를 입력하세요."),
    INPUT_PRODUCT_NAME("제품 이름을 입력하세요."),
    INPUT_PRODUCT_CATEGORY("제품 카테고리를 입력하세요."),
    INPUT_PRODUCT_HEIGHT("높이를 입력하세요: "),
    INPUT_PRODUCT_WIDTH("너비를 입력하세요: "),
    INPUT_PRODUCT_PRICE("가격을 입력하세요: "),
    INPUT_PRODUCT_MANUFACTURER("제조사를 입력하세요: "),
    PRODUCT_REGISTER_SUCCESS("제품이 성공적으로 등록되었습니다."),

    INPUT_INCOMING_TITLE("\n========== 입고신청 =========="),
    SHOW_PRODUCT_LIST(
            "+------------+-----------------------+----------------+--------+--------+------------+---------------+------------+\n" +
            "| 제품 ID     | 제품명                 | 카테고리        | 높이    | 너비    | 가격        | 제조사         | 사용자 ID   |\n" +
            "+------------+-----------------------+----------------+--------+--------+------------+---------------+------------+"),
    INPUT_INCOMING_PRODUCT_ID("\n입고할 제품 ID를 입력하세요."),
    INPUT_INCOMING_COUNT("입고할 제품 수량을 입력하세요."),
    INPUT_INCOMING_DATE("입고할 날짜를 입력하세요. (YYYY-MM-DD)"),
    INCOMING_REQUEST_SUCCESS("입고요청이 성공적으로 완료되었습니다."),
    NO_INCOMING_LIST("요청된 입고신청이 없습니다."),
    SHOW_INCOMING_LIST("""
            +---------+------------+------------+-----------------+----------+------------+
            | 입고번호  | 제품 ID    | 개수        | 입고 날짜        | 상태      | 사용자 ID   |
            +---------+------------+------------+-----------------+----------+------------+"""),
    INPUT_INCOMING_APPROVE("\n승인할 입고번호를 입력하세요."),
    INCOMING_APPROVE_SUCCESS("입고요청을 성공적으로 승인하였습니다."),


    INPUT_OUTGOING_TITLE("\n========== 출고신청 =========="),
    SHOW_STOCK_TITLE("\n========== 재고 목록 =========="),
    SHOW_STOCK_LIST(
                    "+--------+------------+--------+-------------+------------+----------+------------+\n" +
                    "| 재고번호 | 제품 ID    | 개수    | 총 가격      | 사용자 ID   | 섹터      | 창고 ID     |\n" +
                    "+--------+------------+--------+-------------+------------+----------+------------+"
    ),
    INPUT_OUTGOING_STOCK_NUM("\n출고할 재고 번호를 입력하세요."),
    INPUT_OUTGOING_COUNT("출고할 물품의 개수를 입력하세요."),
    INPUT_OUTGOING_DATE("출고할 날짜를 입력하세요. (YYYY-MM-DD)"),
    NO_OUTGOING_LIST("요청된 출고신청이 없습니다."),
    SHOW_OUTGOING_LIST(
                    "+---------+--------+-------------+----------+---------+----------+\n" +
                    "| 출고번호 | 개수    | 출고 날짜     | 상태      | 사용자   | 재고번호  |\n" +
                    "+---------+--------+-------------+----------+---------+----------+"
    ),
    INPUT_OUTGOING_APPROVE("\n승인할 출고번호를 입력하세요."),
    OUTGOING_REQUEST_SUCCESS("출고요청이 성공적으로 완료되었습니다."),
    OUTGOING_APPROVE_SUCCESS("출고요청을 성공적으로 승인하였습니다."),

    NO_STOCK_LIST("해당 재고가 없습니다."),
    NO_STOCK_HISTORY_LIST("해당 재고 이력이 없습니다."),

    MAIN_MENU("----- 메인 메뉴 -----"),
    MAIN_LOGIN("1. 로그인"),
    MAIN_SIGNUP("2. 회원가입"),
    MAIN_EXIT("3. 종료"),
    MAIN_CHOICE("선택 > "),
    MAIN_EXIT_MENU("프로그램을 종료합니다."),
    MAIN_INVALID_INPUT("잘못된 입력입니다."),

    LOGIN_SIGNUP_ID("아이디: "),
    LOGIN_SIGNUP_ID_CHECK("아이디는 영어와 숫자만 사용 가능하며, 4~20자로 입력해야 합니다."),
    LOGIN_SIGNUP_PASSWORD("비밀번호: "),
    LOGIN_SIGNUP_PASSWORD_CHECK("비밀번호는 모든 문자를 포함하며, 최소 4자 이상 입력해야 합니다."),
    LOGIN_FAILED("\n로그인 실패: 아이디 또는 비밀번호를 확인하세요."),

    SIGNUP_MENU("=== 회원가입 ==="),
    SIGNUP_NAME("이름: "),
    SIGNUP_NAME_CHECK("이름은 한글만 입력 가능하며, 2~10자로 입력해야 합니다."),
    SIGNUP_PASSWORD_CHECK("비밀번호확인 : "),
    SIGNUP_PASSWORD_CHECK_MESSAGE("비밀번호가 일치하지 않습니다. 다시 확인해주세요."),
    SIGNUP_PHONE("전화번호: "),
    SIGNUP_PHONE_CHECK("전화번호는 10~11자리 숫자로 입력해야 합니다. (예: 01012345678, 0101234567)"),
    SIGNUP_EMAIL("이메일 (입력하지 않으면 null 처리 입력 예) test@mail.com"),
    SIGNUP_ADDRESS("주소: "),
    SIGNUP_ADDRESS_CHECK("주소는 한글과 숫자만 포함해야 합니다."),
    SIGNUP_SUCCESS("회원가입 성공!"),
    SIGNUP_FAIL("회원가입 실패!"),

    USER_MAIN_MENU("\n===== 회원 메뉴 ====="),
    MY_PAGE("1. 내 정보 보기"),
    USER_RENT_APPLICATION("2. 임대신청"),
    USER_LOG_OUT("3. 로그아웃"),
    USER_DELETE("4. 회원 탈퇴"),

    USER_LOG_OUT_6("6. 로그아웃"),

    USER_CUSTOMER_MAIN_MENU("\n===== 거래처 메뉴 ====="),
    USER_CUSTOMER_PRODUCT("2. 제품 등록"),
    USER_CUSTOMER_OUTGOING("3. 입고"),
    USER_CUSTOMER_INCOMING("4. 출고"),
    USER_CUSTOMER_STOCK("5. 재고 조회"),
    USER_SHOW_INFO("\n=== 내 정보 ==="),

    DELETE_LOGIN_FIRST("먼저 로그인 해주세요."),
    DELETE_CHECK("정말로 회원 탈퇴하시겠습니까? (Y/N): "),
    DELETE_COMPLETE("회원 탈퇴가 완료되었습니다."),
    DELETE_FAIL("회원 탈퇴에 실패하였습니다."),
    DELETE_CANCEL("회원 탈퇴를 취소합니다."),

    LOG_OUT_MESSAGE("\n로그아웃 되었습니다."),

    NOW_USER_LOGIN_STATUS("현재 로그인된 사용자가 없습니다."),
    NOW_ADMIN_LOGIN_STATUS("현재 로그인된 관리자가 없습니다."),

    WAREHOUSE_ADMIN_MENU("\n===== 창고 관리자 메뉴 ====="),
    WAREHOUSE_ADMIN_WAITING_RENT("2. 대기중인 임대신청 목록"),
    WAREHOUSE_ADMIN_WAITING_OUTGOING("3. 대기중인 입고신청 목록"),
    WAREHOUSE_ADMIN_WAITING_INCOMING("4. 대기중인 출고신청 목록"),
    WAREHOUSE_ADMIN_STOCK("5. 담당한 창고의 재고 목록"),
    WAREHOUSE_ADMIN_STOCK_HISTORY("6. 담당한 창고의 재고 변경 이력"),
    ADMIN_LOG_OUT("7. 로그아웃"),
    ADMIN_PAGE("1. 관리자 정보 보기"),

    SUPER_ADMIN_MENU("\n===== 총 관리자 메뉴 ====="),
    SUPER_ADMIN_PROGRESS_RENT("2. 진행중인 임대신청 목록"),
    SUPER_ADMIN_PROGRESS_OUTGING("3. 진행중인 입고신청 목록"),
    SUPER_ADMIN_PROGRESS_INCOMING("4. 진행중인 출고신청 목록"),
    SUPER_ADMIN_STOCK("5. 담당한 창고의 재고 목록"),
    SUPER_ADMIN_STOCK_HISTORY("6. 담당한 창고의 재고 변경 이력"),

    ADMIN_PAGE_COMMON("\n=== 관리자 정보 ==="),





















    INPUT_WAREHOUSE("원하는 창고번호를 선택하세요: "),
    SHOW_WAREHOUSE_SECTOR_LIST("\n%d번 창고의 섹터 목록"),
    INPUT_SECTOR("원하는 섹터를 선택하세요: "),
    INPUT_RENT_PERIOD1("\n임대 기간 선택"),
    INPUT_RENT_PERIOD2("1개월 | 3개월 | 6개월 | 12개월"),
    INPUT_RENT_PERIOD3("원하는 임대기간을 입력하세요: "),
    INPUT_RENT_START_DATE("원하는 임대 시작일을 입력하세요: 예) 2025-03-21\n"),
    SHOW_RENT_HISTORY_LAST("\n========최종 선택 내역=========="),
    RENT_WAREHOUSE("창고 이름 : %d번 창고"),
    RENT_SECTOR("섹터 이름 : %s"),
    RENT_PERIOD("임대 기간 : %s ~ %s"),
    RENT_PRICE("임대 비용 : %d원"),
    CONFIRM_RENT("임대 신청 하시겠습니까? (1. 예 / 2. 아니오)"),
    RENT_END("임대 신청이 완료되었습니다."),
    SHOW_HOLD_RENT_HISTORY("\n===========대기중인 임대 신청 목록============="),
    SELECT_RENT_HISTORY("임대 신청을 진행시킬 임대번호를 선택하세요: ");

    private final String message;

    MessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
