FROM openjdk:18-alpine

ARG jar_name
ARG jar_path
ENV JAR_NAME=${jar_name}
ARG jar_port
COPY ./out/artifacts/services/${jar_path}/* /app/
CMD ["sh", "-c", "java -Xmx256m -jar /app/$JAR_NAME.jar"]

EXPOSE ${jar_port}