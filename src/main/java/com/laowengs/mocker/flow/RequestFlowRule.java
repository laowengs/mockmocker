package com.laowengs.mocker.flow;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.laowengs.mocker.mapper.MockInterfaceDao;
import com.laowengs.mocker.po.MockInterface;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestFlowRule implements InitializingBean {

    private MockInterfaceDao mockInterfaceDao;

    @Autowired
    public RequestFlowRule(MockInterfaceDao mockInterfaceDao) {
        this.mockInterfaceDao = mockInterfaceDao;
    }

    public void addFlowRule(MockInterface mockInterface){
        if(!FlowRuleManager.hasConfig(mockInterface.getRealUri())){
            List<FlowRule> rules = FlowRuleManager.getRules();
            FlowRule rule = new FlowRule();
            rule.setResource(mockInterface.getRealUri());
            rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
            rule.setCount(1);
            rules.add(rule);
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        List<MockInterface> mockInterfaces = mockInterfaceDao.selectAll();
        List<FlowRule> rules = new ArrayList<>(mockInterfaces.size() * 120 / 100);
        for (MockInterface mockInterface : mockInterfaces) {
            FlowRule rule = new FlowRule();
            rule.setResource(mockInterface.getRealUri());
            rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
            rule.setCount(1);
            rules.add(rule);
        }
        FlowRuleManager.loadRules(rules);
    }
}
