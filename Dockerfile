FROM jboss/wildfly:10.0.0.Final
COPY ./target/cdi-transactional.war /opt/jboss/wildfly/standalone/deployments/