package controller;

public interface LoginSignupController {
        // 프로그램 시작 (메인 메뉴)
        void start();


        // 현재 로그인한 회원의 ID 반환
        String userIdReturn();

        // 현재 로그인한 관리자의 ID 반환
        String adminIdReturn();


    }


