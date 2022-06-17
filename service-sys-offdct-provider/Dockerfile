FROM xinxiamu/alpine-java:11
#FROM xinxiamu/wkhtmltopdf-java:latest
VOLUME ["/tmp", "/logs"]

#captcher 字体包,注意必须加上‘--allow-untrusted’允许不可信
RUN set -xe \
&& apk --no-cache add ttf-dejavu fontconfig --allow-untrusted

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-Djava.awt.headless=true","-cp","app:app/lib/*","com.xrlj.servicesysoffdct.ServiceSysOffDctApplication"]
