# LexCurious
- 법제처 Open API를 활용, 대한민국 법령 중 흥미롭거나 특이한 제목/조항을 찾아 추천하는 Java 애플리케이션

## 구조(임시)
```
LexQurious/
├── .gradle/                        ← Gradle 내부 파일 (IntelliJ가 자동 생성)
├── .idea/                          ← IntelliJ IDEA 프로젝트 설정 파일 (IntelliJ가 자동 생성)
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/lexqurious/        ← 최상위 패키지 (여기에 모든 소스 코드 배치)
│   │   │   │   ├── api/
│   │   │   │   │   ├── LawApiClient.java           // 법률 API 통신 (HTTP 요청)
│   │   │   │   │   └── ApiResponseParser.java      // API 응답 데이터 파싱
│   │   │   │   ├── model/
│   │   │   │   │   ├── Law.java                    // 법률 기본 정보 모델
│   │   │   │   │   └── LawDetail.java              // 법률 상세 정보 모델
│   │   │   │   ├── recommender/
│   │   │   │   │   └── WeirdLawSelector.java       // 특이한 법률 선정 로직
│   │   │   │   ├── ui/
│   │   │   │   │   ├── MainFrame.java              // 메인 GUI 창 (JFrame)
│   │   │   │   │   ├── LawPanel.java               // 법률 목록/상세 표시 패널 (JPanel)
│   │   │   │   │   ├── BookmarkPanel.java          // 북마크 목록 표시 패널 (JPanel)
│   │   │   │   │   └── components/                 // 재사용 가능한 UI 컴포넌트들
│   │   │   │   │       ├── SearchBar.java
│   │   │   │   │       └── CustomButton.java
│   │   │   │   ├── storage/
│   │   │   │   │   └── BookmarkManager.java        // 북마크 데이터 관리 (파일, DB 등)
│   │   │   │   └── LexQuriousApp.java              // 애플리케이션 진입점 (main 메서드)
│   │   │   └── resources/
│   │   │       ├── application.properties          // 설정 파일 (API 키, 기본값 등)
│   │   │       ├── images/                         // UI 아이콘, 이미지 등
│   └─  └─      └── styles/                         // UI 스타일 관련 (선택 사항, Swing LookAndFeel)
│
├── .gitignore                      ← Git 추적 제외 파일 목록
├── build.gradle                    ← Gradle 빌드 스크립트
├── settings.gradle                 ← Gradle 프로젝트 설정
└── README.md                       ← 프로젝트 설명 문서
```