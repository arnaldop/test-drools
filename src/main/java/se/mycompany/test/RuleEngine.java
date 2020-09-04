package se.mycompany.test;

import java.time.LocalDateTime;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;

@Component
public class RuleEngine {

    private static final String SESSION_NAME = "session-rules"; // Name in kmodule
    private final KieContainer kieContainer;

    public RuleEngine() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.getKieClasspathContainer();
    }

    public void triggerRule(String ruleFlowGroup) {
        KieSession session = createSession(ruleFlowGroup);
        session.insert(LocalDateTime.now());

        try {
            session.fireAllRules();
        } finally {
            session.dispose();
        }
    }

    public KieSession createSession(String groupName) {
        var session = kieContainer.newKieSession(SESSION_NAME);
        var agendaGroup = session.getAgenda().getAgendaGroup(groupName);
        agendaGroup.setFocus();
        return session;
    }
}
