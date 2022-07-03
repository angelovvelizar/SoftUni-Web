package com.example.battleships.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class FireBindingModel {

    @NotNull
    @Positive
    private Long attackerId;

    @NotNull
    @Positive
    private Long defenderId;

    public Long getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(Long attackerId) {
        this.attackerId = attackerId;
    }

    public Long getDefenderId() {
        return defenderId;
    }

    public void setDefenderId(Long defenderId) {
        this.defenderId = defenderId;
    }
}
