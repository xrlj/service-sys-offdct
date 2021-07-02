FROM 120.79.2.30:8082/anapsix/alpine-java
#FROM xinxiamu/wkhtmltopdf-java:latest
VOLUME ["/tmp", "/logs"]
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.xrlj.servicesysoffdct.ServiceSysOffDctApplication"]
