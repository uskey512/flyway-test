FROM  store/oracle/serverjre:8

ADD ./.gradlecache .gradle
WORKDIR /usr/src/app
ADD . /usr/src/app

CMD ./gradlew bootRun
