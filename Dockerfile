# 베이스 이미지 지정
FROM openjdk:17

# 애플리케이션 파일 복사
COPY ./build/libs/Showman-0.0.1-SNAPSHOT.jar app.jar

# 애플리케이션 실행
#CMD ["java", "-jar", "/app/myapp.jar"]
ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Seoul", "app.jar"]