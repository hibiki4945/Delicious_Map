package com.example.delicious_map.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EvaluateGreaterThan2Req {

    @JsonProperty("evaluate")
    private int evaluate;

    @JsonProperty("dish_evaluate")
    private int dishEvaluate;

    public int getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(int evaluate) {
        this.evaluate = evaluate;
    }

    public int getDishEvaluate() {
        return dishEvaluate;
    }

    public void setDishEvaluate(int dishEvaluate) {
        this.dishEvaluate = dishEvaluate;
    }
    
}
