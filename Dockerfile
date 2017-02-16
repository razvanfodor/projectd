FROM airhacks/payara
MAINTAINER Razvan Fodor, razvan.fodor@gmail.com

COPY ./target/app.war ${DEPLOYMENT_DIR}