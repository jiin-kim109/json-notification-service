package com.jiin.httpqueue.action;

import com.jiin.httpqueue.validator.ValidAction;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class Action {

    @ValidAction
    @NotBlank
    private String actionName;

    @Builder.Default
    private Map<String, String> parameters = new HashMap<>();

    @Builder.Default
    private String urlPortion = "";
}
