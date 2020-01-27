package uk.financial.reporting.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //can be as well annotated with @TestConfiguration
@ComponentScan(basePackages= {"uk.financial.reporting"})
public class TestConfiguration {
}
