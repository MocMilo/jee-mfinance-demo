FROM anapsix/alpine-java:jdk8

ARG WILDFLY_VERSION=10.1.0.Final

ENV JBOSS_HOME /opt/jboss/wildfly

RUN apk update && apk upgrade && apk add curl tar bash


RUN curl https://download.jboss.org/wildfly/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz -o wildfly-$WILDFLY_VERSION.tar.gz
RUN tar xf wildfly-$WILDFLY_VERSION.tar.gz \
    && mkdir -p $JBOSS_HOME \
    && mv wildfly-$WILDFLY_VERSION/* $JBOSS_HOME \
    && rm -rf wildfly-$WILDFLY_VERSION \
    && rm -f wildfly-$WILDFLY_VERSION.tar.gz

RUN apk del curl tar

ADD mgmt-users.properties $JBOSS_HOME/standalone/configuration/mgmt-users.properties
ADD standalone.xml $JBOSS_HOME/standalone/configuration/standalone.xml
ADD standalone.conf $JBOSS_HOME/bin/standalone.conf

ENV JBOSS_MODULES=$JBOSS_HOME/modules/system/layers/base
ENV MYSQL_MODULE=$JBOSS_MODULES/com/mysql/driver/main

ADD http://central.maven.org/maven2/mysql/mysql-connector-java/6.0.6/mysql-connector-java-6.0.6.jar /mysql-connector-java.jar
ADD add-mysql.sh /add-mysql.sh

RUN chmod a+x /add-mysql.sh
RUN source /add-mysql.sh

RUN rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history

WORKDIR $JBOSS_HOME

EXPOSE 8080
EXPOSE 9990

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

