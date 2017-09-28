# The library for LegalDocML - AkomaNtoso

[![Build Status](https://travis-ci.org/jacquesmilitello/legaldocml.svg?branch=master)](https://travis-ci.org/jacquesmilitello/legaldocml)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=io.legaldocml%3Alegaldocml)](https://sonarcloud.io/dashboard?id=io.legaldocml%3Alegaldocml)

## Domain Model
All the XSD is transform to different kind of Object : 

### AttributeGroup
```xml
<xsd:attributeGroup name="name">
   <xsd:attribute name="name" type="xsd:string" use="required"/>
</xsd:attributeGroup>
```

This XML fragment will be transform to :
```java
public interface Name extends AknObject {

    String getName();

    void setName(String name);

}
```

### ComplexType

## IO
All AknObject implements the Externalizable Interface.
```java
public interface Externalizable {
    void write(XmlWriter writer) throws IOException;
    void read(XmlReader reader);
}
```
### XmlReader

### XmlWriter

## Factory

### Read
```java
XmlReaderFactory factory = XmlProvider.readerFactory();
AkomaNtoso<?> akn = factory.read(<<MappedByteBuffer>>>);
```

### Write
```java
XmlWriterFactory factory = XmlProvider.writerFactory(3);
factory.write(<<<WritableByteChannel>>>, <<<AkomaNtoso>>>);
```

## Business Provider

### Buisiness Builder
TODO
### Business Reader
TODO