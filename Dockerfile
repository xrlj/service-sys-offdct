FROM anapsix/alpine-java
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/BOOT-INF/classes/wkpdf/simsun.ttc /usr/share/fonts
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

RUN yum install zlib fontconfig freetype libX11 libXext libXrender libjpeg xorg-x11-fonts-75dpi xorg-x11-fonts-Type1 \
&& cd /tmp \
&& wget https://downloads.wkhtmltopdf.org/0.12/0.12.5/wkhtmltox-0.12.5-1.centos7.x86_64.rpm \
&& rpm -i wkhtmltox-0.12.5-1.centos7.x86_64.rpm \
&& rm -rf wkhtmltox-0.12.5-1.centos7.x86_64.rpm

VOLUME /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.xrlj.configserver.ConfigServerApplication"]