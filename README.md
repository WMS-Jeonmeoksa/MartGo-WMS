# MatGO

## 📌 프로젝트 개요
- WMS(창고 관리 시스템)의 프로세스 학습 및 이해
- Java와 MySQL 연동 경험 (JDBC 사용)
- MVC 패턴을 활용한 구조적 프로그래밍 경험 습득
- 협업 프로세스 및 Git 활용 경험 축적

## 🛠 기술 스택
- Java (JDK 17)
- MySQL (8.0)
- JDBC

## 🚀 설치 및 실행 방법

### 1️⃣ 필수 요구사항
- Java 17 이상 설치
- MySQL 데이터베이스 설치
- MySQL connector 8.0.17 라이브러리 추가

### 2️⃣ 실행 방법
```sh
# 프로젝트 클론
git clone https://github.com/username/project-name.git

# 프로젝트 빌드 및 실행
cd MartGO
java -jar target/MartGO.jar

📝 기능
✔ 학생 및 직원 정보 등록, 수정, 삭제, 조회
✔ MySQL을 통한 데이터 저장
✔ 예외 처리 및 오류 로그 기록

📂 프로젝트 구조
📦 student-employee-management
 ┣ 📂 src
 ┃ ┣ 📂 common
 ┃ ┃ ┣ 📂 config
 ┃ ┃ ┣ 📂 constans
 ┃ ┃ ┣ 📂 exception
 ┃ ┃ ┣ 📂 utils
 ┃ ┣ 📂 controller
 ┃ ┣ 📂 model
 ┃ ┃ ┣ 📂 dao
 ┃ ┃ ┣ 📂 dto
 ┃ ┃ ┣ 📂 service
 ┃ ┣ 📂 view
 ┗ 📜 README.md

📜 라이선스
이 프로젝트는 신세계 I&C 6차수 3팀 전먹사에 의해 완성되었습니다.
