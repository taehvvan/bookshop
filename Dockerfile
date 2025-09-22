# Java 8과 Tomcat 9 서버 환경을 사용합니다.
FROM tomcat:9.0-jdk8-openjdk

# 기존에 포함된 기본 웹페이지들을 삭제합니다.
RUN rm -rf /usr/local/tomcat/webapps/*

# 프로젝트 빌드 결과물(.war 파일)을 서버의 webapps 폴더에 ROOT.war 이름으로 복사합니다.
# 이렇게 하면 URL의 맨 뒤에 특정 경로를 붙이지 않아도 애플리케이션에 바로 접속할 수 있습니다.
COPY target/book-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# 8888 포트를 외부에 개방합니다.
EXPOSE 8888

# Tomcat 서버를 실행합니다.
CMD ["catalina.sh", "run"]
