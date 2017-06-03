package com.allstate.springboot.controller;

import com.allstate.springboot.domain.Policy;
import com.allstate.springboot.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("policies")
public class PolicyController {

    private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);

    @Inject
    PolicyService policyService;

    @RequestMapping(method = RequestMethod.GET)
    public Policy[] getPolicies() {
        logger.info("Executing getPolicies method.");
        return policyService.getPolicies().stream().toArray(size -> new Policy[size]);
    }

    @RequestMapping(value = "{policyNumber}", method = RequestMethod.GET)
    public Policy getPolicy(@PathVariable int policyNumber) {
        logger.info("Executing getPolicy method.");
        return policyService.getPolicy(policyNumber);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Policy createPolicy(@RequestBody Policy policy) {
        logger.info("Executing createPolicy method.");
        return policyService.createPolicy(policy);
    }
}
