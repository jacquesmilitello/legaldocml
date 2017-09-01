package io.legaldocml.business.builder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DefaultHierachyStrategy {

    public static final HierarchyStrategy HIGHER_DIVISION;
    public static final HierarchyStrategy BASIC_UNIT;
    public static final HierarchyStrategy SUB_DIVISION;

    public static final HierarchyStrategy COMPLETE;

    static {
        HIGHER_DIVISION = new HierarchyStrategyBuilder().tome().part().book().title().chapter().section().subSection().build();
        BASIC_UNIT = new HierarchyStrategyBuilder().article().section().rule().build();
        SUB_DIVISION = new HierarchyStrategyBuilder().subSection().alinea().clause().paragraph().provisio().subParagraph().division().point().build();

        COMPLETE = new HierarchyStrategyBuilder()
                .tome()
                .part()
                .book()
                .title()
                .chapter()
                .section()
                .subSection()
                .article()
                .section()
                .rule()
                .subSection()
                .alinea()
                .clause()
                .paragraph()
                .provisio()
                .subParagraph()
                .division()
                .point()
                .build();
    }

    private DefaultHierachyStrategy() {
    }

}
