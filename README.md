# 📱 나 사용법 – 감정 기반 추천 앱

## 📌 프로젝트 소개

MoodyApp은 사용자가 현재 감정 상태를 선택하면, 그에 맞는 **조언 + 장소 추천 + 음악 추천**을 제공하는 안드로이드 앱입니다.  
단순한 감정 기록을 넘어, 감정에 맞는 행동을 유도해 사용자의 일상 경험과 힐링을 돕는 것이 목표입니다.

---

## 👤 담당 역할

- 안드로이드 UI 구현
- UX 디자인
- 프론트엔드 개발
- 시스템 통합

---

## ✨ 주요 기능

### 😊 1. 감정 선택
- 5가지 감정 제공: **행복 / 차분 / 보통 / 슬픔 / 스트레스**
- 버튼 클릭 시 선택 상태 UI 강조 변경
- 선택된 감정에 따라 이후 기능 활성화

### 💬 2. 감정 조언
- 감정별 랜덤 조언 출력
- 같은 감정이라도 다양한 메시지 제공

### 📍 3. 장소 추천
- 감정별 3~5개의 추천 장소 링크 구성
- 버튼 클릭 시 랜덤으로 1개 선택
- 앱 내부 **WebView**에서 바로 표시

### 🎵 4. 음악 추천
- 감정별 YouTube 링크 제공
- 랜덤 추천
- 앱 내부 **WebView**에서 바로 재생

### 📊 5. 감정 통계

#### 📅 월간 통계 화면
- 감정별 사용 횟수를 **가로형 바 차트**로 시각화
- 감정별 색상 커스터마이징 적용

#### 📌 프로필 화면 통계
- 최근 감정 데이터를 **수평 바 차트**로 표시
- UI 공간을 균형 있게 활용하도록 최적화

| 감정 | 색상 |
|------|------|
| happy | #FFE4B5 |
| calm | #E0F4FF |
| okay | #F2F2DB |
| sad | #E6E6FA |
| stressed | #E56060 |

### 👤 6. 프로필 화면
- 사용자 정보 표시
- 주간 감정 통계 요약
- 전체 통계 화면 이동 기능

### 🔐 7. 로그인 / 회원가입
- SharedPreferences 기반 로그인 유지
- 자동 로그인 & 아이디 저장 기능
- 추후 DB 연동 확장 가능 (구조 설계 완료)

---

## 🛠️ 기술 스택

| 항목 | 내용 |
|------|------|
| Language | Java |
| IDE | Android Studio |
| UI | XML Layout |
| 데이터 저장 | SharedPreferences |
| 웹 연동 | WebView |
| 구조 | Activity + Fragment 기반 |

---

## 📂 주요 구조

```
com.example.moodyapp
│
├── HomeFragment.java              # 감정 선택 + 추천 기능
├── ProfileFragment.java           # 프로필 + 통계
├── MonthlyStatsActivity.java      # 월간 통계
├── RecommandPlaceActivity.java    # 장소 추천 WebView
├── RecommandMusicActivity.java    # 음악 추천 WebView
│
├── LoginActivity.java
├── SignupActivity.java
├── MainActivity.java
│
└── res/layout
    ├── fragment_home.xml
    ├── fragment_profile.xml
    ├── activity_monthly_stats.xml
    ├── activity_recommand_place.xml
    └── activity_recommand_music.xml
```

### 앱 흐름 구조

```
HomeFragment
 ├─ 감정 선택
 ├─ 조언 표시
 ├─ 장소 추천 → RecommandPlaceActivity (WebView)
 └─ 음악 추천 → RecommandMusicActivity (WebView)

ProfileFragment
 └─ 감정 통계 (수평 바 차트)

MonthlyStatsActivity
 └─ 감정 통계 (가로형 차트)
```

---

## ⚙️ 핵심 구현 포인트

### 🎯 1. 감정 기반 랜덤 추천

```java
String[] links = placeLinks[selectedEmotionId];
String randomLink = links[random.nextInt(links.length)];
```

### 🎯 2. WebView 내부 브라우징

```java
webView.getSettings().setJavaScriptEnabled(true);
webView.setWebViewClient(new WebViewClient());
webView.loadUrl(url);
```

### 🎯 3. 동적 차트 생성

```java
LinearLayout.LayoutParams params =
    new LinearLayout.LayoutParams(count * 30, 48);
```

→ 데이터 기반으로 UI 자동 생성

---

## ⚙️ 실행 방법

1. Android Studio에서 프로젝트 열기
2. 에뮬레이터 또는 실제 기기 연결
3. 앱 실행

---

## 🔧 필수 설정

### ✔ 인터넷 권한

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

### ✔ AndroidManifest 등록

```xml
<activity android:name=".RecommandPlaceActivity" />
<activity android:name=".RecommandMusicActivity" />
```

---

## ⚠️ 주의사항

- 일부 링크 (특히 `naver.me` 단축링크)는 WebView에서 차단될 수 있음 → `https://` 포함된 실제 URL 사용 권장
- YouTube는 WebView에서 제한될 수 있음 → 필요 시 외부 브라우저 Intent 사용 고려

---

## 🚀 향후 개선 계획

- Firebase / MySQL 기반 DB 연동 (로그인 / 감정 저장)
- 감정 기록 히스토리 분석 기능
- 추천 알고리즘 고도화 (단순 랜덤 → 개인화)
- 차트 라이브러리 적용 (MPAndroidChart 등)
- UI 애니메이션 추가
- 다크모드 지원

---

## 👨‍💻 개발 목적

- Android UI 구성 능력 향상
- UX 설계 및 사용자 감정 기반 서비스 설계 경험
- 데이터 → UI 시각화 구현
- Activity / Fragment 기반 앱 구조 설계 이해

---

## 📌 한 줄 요약

> 👉 감정을 선택하면 추천 + 통계까지 이어지는, 감정 기반 힐링 UX 앱 📱
