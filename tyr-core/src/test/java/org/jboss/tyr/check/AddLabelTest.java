package org.jboss.tyr.check;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.jboss.tyr.InvalidPayloadException;
import org.jboss.tyr.TestUtils;
import org.jboss.tyr.github.GitHubService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import javax.inject.Inject;

import static org.mockito.ArgumentMatchers.*;


@QuarkusTest
public class AddLabelTest {

    @Inject
    AddLabel addLabel;

    @InjectMock
    GitHubService gitHubService;

    @Test
    public void testAddLabel() throws InvalidPayloadException {


        Assertions.assertNull(addLabel.check(TestUtils.TEST_PAYLOAD), "Null expected");
    }
}
