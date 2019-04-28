# set the base image
#FROM openjdk:8-jdk-alpine
FROM gradle:4.8-jdk8-alpine

# author
MAINTAINER Nazar Khimin
# extra metadata
LABEL version="1.0"
LABEL description="First image with Dockerfile."
WORKDIR ./
#RUN mkdir -p /project
COPY * ./

RUN gradle clean runAllSuite