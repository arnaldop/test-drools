package se.mycompany.test;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class TestRunner {

    private final RuleEngine ruleEngine;

    public TestRunner(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    @PostConstruct
    public void triggerRule() {
        ruleEngine.triggerRule("DEBUG_TIME");
    }
}
