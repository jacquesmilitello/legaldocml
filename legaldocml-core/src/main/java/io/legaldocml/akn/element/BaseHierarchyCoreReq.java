package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.HEADING;
import static io.legaldocml.akn.AknElements.NUM;
import static io.legaldocml.akn.AknElements.SUB_HEADING;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BaseHierarchyCoreReq extends CoreReqImpl implements BaseHierarchy {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseHierarchyCoreReq.class);

    /**
     * Num for this element.
     */
    private Num num;

    /**
     * Heading for this element.
     */
    private Heading heading;

    /**
     * Subheading for this element.
     */
    private Subheading subheading;

    public final Num getNum() {
        return this.num;
    }

    public final void setNum(Num num) {
        this.num = num;
    }

    public final Heading getHeading() {
        return this.heading;
    }

    public final void setHeading(Heading heading) {
        this.heading = heading;
    }

    public final Subheading getSubheading() {
        return this.subheading;
    }

    public final void setSubheading(Subheading subheading) {
        this.subheading = subheading;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        if (this.num != null) {
            this.num.write(writer);
        }
        if (this.heading != null) {
            this.heading.write(writer);
        }
        if (this.subheading != null) {
            this.subheading.write(writer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(NUM)) {
            this.num = new Num();
            this.num.read(reader);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("[{}] -> [{}]", getClass().getSimpleName(), this.num);
            }
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(HEADING)) {
            this.heading = new Heading();
            this.heading.read(reader);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("[{}] -> [{}]", getClass().getSimpleName(), this.heading);
            }
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(SUB_HEADING)) {
            this.subheading = new Subheading();
            this.subheading.read(reader);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("[{}] -> [{}]", getClass().getSimpleName(), this.subheading);
            }
            reader.nextStartOrEndElement();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (this.num != null) {
            this.num.accept(visitor);
        }
        if (this.heading != null) {
            this.heading.accept(visitor);
        }
        if (this.subheading != null) {
            this.subheading.accept(visitor);
        }
    }

}