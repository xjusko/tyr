package org.jboss.tyr.check;

import org.jboss.tyr.Check;
import org.jboss.tyr.InvalidPayloadException;
import org.jboss.tyr.github.GitHubService;
import org.jboss.tyr.model.Utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

@ApplicationScoped
public class AddLabelCheck implements Check {

    @Inject
    GitHubService gitHubService;

    @Override
    public String check(JsonObject payload) throws InvalidPayloadException {
        JsonArray labels = payload.getJsonObject(Utils.PULL_REQUEST).getJsonArray(Utils.LABELS);
        String targetBranch = payload.getJsonObject(Utils.PULL_REQUEST).getJsonObject(Utils.BASE).getString(Utils.REF);
        if (labels != null) {
            for (JsonValue label : labels) {
                if (label.asJsonObject().getString(Utils.NAME).equals(targetBranch)) {
                    return null;
                }
            }
        }


        gitHubService.addLabelToPullRequest(
                payload.getJsonObject(Utils.REPOSITORY).getString(Utils.FULL_NAME),
                payload.getInt(Utils.NUMBER),
                targetBranch);
        return null;
    }
}
