package com.allstate.springboot.service.impl;

import com.allstate.springboot.domain.Policy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PolicyServiceImpl.class})
public class PolicyServiceImplTest {

    @Autowired
    PolicyServiceImpl policyService;

    @Test
    public void testCreatePolicy() {
        Policy policyToCreate = mock(Policy.class);
        when(policyToCreate.getFirstName()).thenReturn("John");

        Policy policy = policyService.createPolicy(policyToCreate);
        assertNotNull(policy);
        assertEquals("John", policy.getFirstName());
    }
}
