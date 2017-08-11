package io.legaldocml.xpath;

import io.legaldocml.xpath.query.AbsoluteXpathQueryBuilder;
import io.legaldocml.xpath.query.XpathQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class XpathBuilder {

    private List<XpathQueryBuilder> builders = new ArrayList<>();

    XpathQueryBuilder newAbsoluteQuery() {
        AbsoluteXpathQueryBuilder builder = new AbsoluteXpathQueryBuilder();
        this.builders.add(builder);
        return builder;
    }

    public Xpath build() {

       return  builders.get(0).build();
    }
}
