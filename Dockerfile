# --- 1단계: Maven으로 프로젝트 빌드하기 ---
# Maven과 Java 8이 설치된 이미지를 'builder'라는 별명으로 사용합니다.
FROM maven:3.8-jdk-8 AS builder

# 작업 디렉토리를 /app으로 설정합니다.
WORKDIR /app

# 먼저 pom.xml을 복사하여 의존성을 다운로드합니다. (Docker 캐시 효율 증가)
COPY pom.xml .
RUN mvn dependency:go-offline

# 나머지 소스 코드를 복사합니다.
COPY src ./src

# Maven으로 프로젝트를 빌드합니다. (테스트는 건너뛰어 빌드 속도를 높입니다.)
# 이 단계가 성공하면 /app/target/book-0.0.1-SNAPSHOT.war 파일이 생성됩니다.
RUN mvn package -DskipTests


# --- 2단계: 빌드된 결과물로 최종 서버 이미지 만들기 ---
# Tomcat과 Java 8이 설치된 이미지를 최종 실행 환경으로 사용합니다.
FROM tomcat:9.0-jdk8-openjdk

# 기본 웹페이지들을 삭제합니다.
RUN rm -rf /usr/local/tomcat/webapps/*

# 1단계(builder)에서 생성된 .war 파일을 Tomcat의 webapps 폴더에 ROOT.war로 복사합니다.
COPY --from=builder /app/target/book-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# 8080 포트를 개방합니다.
EXPOSE 8080

# Tomcat 서버를 실행합니다.
CMD ["catalina.sh", "run"]