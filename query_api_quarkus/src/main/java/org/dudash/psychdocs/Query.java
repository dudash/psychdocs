package org.dudash.psychdocs;

// import java.util.List;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
// import io.quarkus.mongodb.panache.PanacheQuery;
// import io.quarkus.panache.common.Page;
// import io.quarkus.panache.common.Sort;

public class Query  extends PanacheMongoEntity {
    public String modelName;
    public String prompt;
    public Integer maxTokens;
    public String userId;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{" +
            "\"modelName\":\"" + modelName + "\"," +
            "\"prompt\":" + prompt + 
            "\"maxTokens\":" + maxTokens +
            "}";
    }
}
