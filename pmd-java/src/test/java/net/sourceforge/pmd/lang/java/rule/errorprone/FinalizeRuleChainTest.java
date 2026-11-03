/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.rule.errorprone;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import net.sourceforge.pmd.lang.rule.xpath.XPathRule;
import net.sourceforge.pmd.lang.rule.xpath.XPathVersion;
import net.sourceforge.pmd.lang.rule.xpath.impl.XPathHandler;
import net.sourceforge.pmd.lang.rule.xpath.internal.DeprecatedAttrLogger;
import net.sourceforge.pmd.lang.rule.xpath.internal.SaxonXPathRuleQuery;
import net.sourceforge.pmd.test.RuleTst;

class FinalizeRuleChainTest {

    @Test
    void finalizeDoesNotCallSuperFinalizeUsesRuleChain() {
        assertMethodRuleChain("FinalizeDoesNotCallSuperFinalize");
    }

    @Test
    void finalizeOnlyCallsSuperFinalizeUsesRuleChain() {
        assertMethodRuleChain("FinalizeOnlyCallsSuperFinalize");
    }

    private static void assertMethodRuleChain(String ruleName) {
        XPathRule rule = (XPathRule) RuleTst.findRule("category/java/errorprone.xml", ruleName);
        SaxonXPathRuleQuery query = new SaxonXPathRuleQuery(
            rule.getXPathExpression(), XPathVersion.DEFAULT, Collections.emptyMap(),
            XPathHandler.noFunctionDefinitions(), DeprecatedAttrLogger.noop());
        assertEquals(Collections.singletonList("MethodDeclaration"), query.getRuleChainVisits(), ruleName);
    }
}
