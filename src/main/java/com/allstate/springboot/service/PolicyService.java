package com.allstate.springboot.service;

import com.allstate.springboot.domain.Policy;

import java.util.Collection;

public interface PolicyService {

    Collection<Policy> getPolicies();

    Policy getPolicy(int policyNumber);

    Policy createPolicy(Policy policy);
}
