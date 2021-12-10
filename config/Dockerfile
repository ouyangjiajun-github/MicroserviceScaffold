FROM java:8-jre
MAINTAINER ouyangjiajun <ouyangjiajun@qq.com>

# jar包是可以直接执行的可执行jar包，所以只需要将jar包用-jar命令执行即可在docker中启动
# 配置了健康检查actuator health
ADD ./target/config.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8888/actuator/health || exit 1

EXPOSE 8888