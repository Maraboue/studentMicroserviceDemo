FROM java:8

EXPOSE 5432

ADD target/StudentDemoTesting.jar StudentDemoTesting.jar

ENTRYPOINT ["java","-jar","StudentDemoTesting.jar"]