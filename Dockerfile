# 베이스 이미지 지정
FROM openjdk:17

# 작업 디렉토리 설정
WORKDIR /app

# 애플리케이션 파일 복사
COPY ./build/libs/Showman-0.0.1-SNAPSHOT.jar ./app.jar

# 애플리케이션 실행
#CMD ["java", "-jar", "/app/myapp.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]