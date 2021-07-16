package org.jboss.tyr.check;

import org.jboss.tyr.Check;
import org.jboss.tyr.InvalidPayloadException;
import org.jboss.tyr.github.GitHubService;
import org.jboss.tyr.model.Utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;

@ApplicationScoped
public class AddLabel implements Check {

    @Inject
    GitHubService gitHubService;

    @Override
    public String check(JsonObject payload) throws InvalidPayloadException {
        gitHubService.AddLabelToPullRequest(
                payload.getJsonObject(Utils.REPOSITORY).getString(Utils.FULL_NAME),
                payload.getInt(Utils.NUMBER),
                payload.getJsonObject(Utils.PULL_REQUEST).getJsonObject(Utils.BASE).getString(Utils.REF));
        return null;
    }
}
