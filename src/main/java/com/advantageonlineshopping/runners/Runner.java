package com.advantageonlineshopping.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions
        (
                features = "src/main/java/com/advantageonlineshopping/features",
                glue = {"com.advantageonlineshopping.steps"},
                plugin = {"pretty", "html:target/report-html"},
                monochrome = true,
                snippets = SnippetType.CAMELCASE,
                dryRun = false
        )

public class Runner {
}
