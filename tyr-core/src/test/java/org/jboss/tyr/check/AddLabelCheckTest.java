package org.jboss.tyr.check;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.jboss.tyr.InvalidPayloadException;
import org.jboss.tyr.TestUtils;
import org.jboss.tyr.github.GitHubService;
import org.jboss.tyr.model.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.inject.Inject;


@QuarkusTest
public class AddLabelCheckTest {

    @Inject
    AddLabelCheck addLabelCheck;

    @InjectSpy
    GitHubService gitHubService;

    @Test
    public void testAddLabel() throws InvalidPayloadException {
        ArgumentCaptor<String> targetBranchCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> pullRequestNumberCaptor = ArgumentCaptor.forClass(int.class);
        ArgumentCaptor<String> testRepositoryCaptor = ArgumentCaptor.forClass(String.class);

        String targetBranch =
                TestUtils.TEST_PAYLOAD.getJsonObject(Utils.PULL_REQUEST).getJsonObject(Utils.BASE).getString(Utils.REF);
        int pullRequestNumber = TestUtils.TEST_PAYLOAD.getInt(Utils.NUMBER);
        String testRepository = TestUtils.TEST_PAYLOAD.getJsonObject(Utils.REPOSITORY).getString(Utils.FULL_NAME);

        addLabelCheck.check(TestUtils.TEST_PAYLOAD);
        Mockito.verify(gitHubService).addLabelToPullRequest
                (testRepositoryCaptor.capture(), pullRequestNumberCaptor.capture(), targetBranchCaptor.capture());

        Assertions.assertEquals(targetBranch, targetBranchCaptor.getValue());
        Assertions.assertEquals(pullRequestNumber, pullRequestNumberCaptor.getValue());
        Assertions.assertEquals(testRepository, testRepositoryCaptor.getValue());
        Assertions.assertNull(addLabelCheck.check(TestUtils.TEST_PAYLOAD), "Null expected");
    }
}
