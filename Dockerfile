FROM azul/zulu-openjdk:11.0.10 as test
RUN apt update

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENV URL_DATABASE "jdbc:postgresql://localhost:5432/school_db"

ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.demo.MsSchoolApplication"]