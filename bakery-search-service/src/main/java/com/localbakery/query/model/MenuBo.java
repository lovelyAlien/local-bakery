package com.localbakery.query.model;

import com.localbakery.domain.entity.Menu;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Function;

@Builder
@Getter
public class MenuBo {
    private String name;
    private Long price;
    private Boolean signature;


    public String getName() {
        return name;
    }

    public static final Function<Menu, MenuBo> FROM = (Menu source) -> {
        if (null == source) {
            return null;
        }
        return MenuBo.builder()
                .name(source.getName())
                .price(source.getPrice())
                .signature(source.getSignature())
                .build();
    };
}
