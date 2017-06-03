package com.allstate.springboot.service.impl;

import com.allstate.springboot.domain.Policy;
import com.allstate.springboot.service.PolicyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class PolicyServiceImpl implements PolicyService {

    private static Collection<Policy> policies;

    private static int policyNumberIncrementer = 1;

    static {
        policies = new ArrayList<>();
        policies.add(new Policy(policyNumberIncrementer++, "Frank", "Zmuda", 32));
        policies.add(new Policy(policyNumberIncrementer++, "Chris", "Ramsey", 33));
        policies.add(new Policy(policyNumberIncrementer++, "Siva", "Raj", 26));
    }

    @Override
    public Collection<Policy> getPolicies() {
        return policies;
    }

    @Override
    public Policy getPolicy(int policyNumber) {
        return policies.stream().filter(
                policy -> policy.getPolicyNumber() == policyNumber).findFirst().get();
    }

    @Override
    public Policy createPolicy(Policy policy) {
        policy.setPolicyNumber(policyNumberIncrementer++);
        policies.add(policy);
        return policy;
    }
}
