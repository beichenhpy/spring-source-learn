package cn.beichenhpy.annotations;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Profiles;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EvenNumberCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Profiles profiles = activeProfiles -> activeProfiles.test("even");
        return context.getEnvironment().acceptsProfiles(profiles);
    }
}
