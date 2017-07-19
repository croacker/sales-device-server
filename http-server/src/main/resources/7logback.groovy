import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import java.nio.charset.Charset

import static ch.qos.logback.classic.Level.DEBUG
//Only for Java 1.7

appender("STDOUT", ConsoleAppender) {
    layout(PatternLayout) {
        pattern = "%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n"
    }
}
appender("FILE", RollingFileAppender) {
    file = "logs/application.log"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "logs/application_%d{yyyy-MM-dd}.%i.log"
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = "5MB"
        }
        maxHistory = 30
    }
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName("UTF-8")
        pattern = "%d %-4relative [%thread] %-5level %logger{35} - %msg%n"
    }
}
root(DEBUG, ["STDOUT", "FILE"])