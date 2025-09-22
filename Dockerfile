# --- 1단계: Maven으로 프로젝트 빌드하기 ---
FROM maven:3.8-jdk-8 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# --- 2단계: 빌드된 결과물로 최종 서버 이미지 만들기 ---
FROM tomcat:9.0-jdk8-openjdk
RUN rm -rf /usr/local/tomcat/webapps/*

# ★★★★★ 수정된 부분 ★★★★★
# pom.xml에서 finalName을 ROOT로 변경했으므로, 복사할 파일 이름도 ROOT.war로 수정합니다.
COPY --from=builder /app/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]