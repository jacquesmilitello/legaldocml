/*******************************************************************************
 Grammar based on https://www.w3.org/TR/xpath20/
 *******************************************************************************/
parser grammar XPath2;

options {
	tokenVocab = XPath2Lexer;
}

//	[1]     XPath	   ::=   	Expr
xPath
    :	expr EOF;

//  [2]   	Expr	   ::=   	ExprSingle ("," ExprSingle)*
expr
    :	exprSingle (COMMA exprSingle)* ;

//[3]   	ExprSingle	   ::=   	ForExpr
//                                  | QuantifiedExpr
//                                  | IfExpr
//                                  | OrExpr
exprSingle
	:	forExpr
	|	quantifiedExpr
	|	ifExpr
	|	orExpr
	;

//  [4]   	ForExpr	   ::=   	SimpleForClause "return" ExprSingle
forExpr
	:	simpleForClause RETURN exprSingle
	;

//  [5]   	SimpleForClause	   ::=   	"for" "$" VarName "in" ExprSingle ("," "$" VarName "in" ExprSingle)*
simpleForClause
	:	FOR DOLLAR varName IN exprSingle
	|	simpleForClause COMMA DOLLAR varName IN exprSingle
	;

//  [6]   	QuantifiedExpr	   ::=   	("some" | "every") "$" VarName "in" ExprSingle ("," "$" VarName "in" ExprSingle)* "satisfies" ExprSingle
quantifiedExpr
	:	SOME DOLLAR quantifiedExprMiddle SATISFIES exprSingle
	|	EVERY DOLLAR quantifiedExprMiddle SATISFIES exprSingle
	;

quantifiedExprMiddle
   	:	varName IN exprSingle
   	|	quantifiedExprMiddle COMMA DOLLAR varName IN exprSingle
   	;

//  [7]   	IfExpr	   ::=   	"if" "(" Expr ")" "then" ExprSingle "else" ExprSingle
ifExpr
	:	IF LPAR expr RPAR THEN exprSingle ELSE exprSingle
	;

//  [8]   	OrExpr	   ::=   	AndExpr ( "or" AndExpr )*
orExpr
	:	andExpr
	|	orExpr OR andExpr
	;

//  [9]   	AndExpr	   ::=   	ComparisonExpr ( "and" ComparisonExpr )*
andExpr
	:	comparisonExpr
	|	andExpr AND comparisonExpr
	;

//  [10]   	ComparisonExpr	   ::=   	RangeExpr ( (ValueComp
//                                  | GeneralComp
//                                  | NodeComp) RangeExpr )?
comparisonExpr
	:	rangeExpr
	|	rangeExpr valueComp rangeExpr
	|	rangeExpr generalComp rangeExpr
	|	rangeExpr nodeComp rangeExpr
	;

//  [11]   	RangeExpr	   ::=   	AdditiveExpr ( "to" AdditiveExpr )?
rangeExpr
	:	additiveExpr
	|	additiveExpr TO additiveExpr
	;

//  [12]   	AdditiveExpr	   ::=   	MultiplicativeExpr ( ("+" | "-") MultiplicativeExpr )*
additiveExpr
	:	multiplicativeExpr
	|	additiveExpr PLUS multiplicativeExpr
	|	additiveExpr MINUS multiplicativeExpr
	;

//  [13]   	MultiplicativeExpr	   ::=   	UnionExpr ( ("*" | "div" | "idiv" | "mod") UnionExpr )*
multiplicativeExpr
	:	unionExpr
	|	multiplicativeExpr STAR unionExpr
	|	multiplicativeExpr DIV unionExpr
	|	multiplicativeExpr IDIV unionExpr
	|	multiplicativeExpr MOD unionExpr
	;

//  [14]   	UnionExpr	   ::=   	IntersectExceptExpr ( ("union" | "|") IntersectExceptExpr )*
unionExpr
	:	intersectExceptExpr
	|	unionExpr UNION intersectExceptExpr
	|	unionExpr PIPE intersectExceptExpr
	;

//  [15]   	IntersectExceptExpr	   ::=   	InstanceofExpr ( ("intersect" | "except") InstanceofExpr )*
intersectExceptExpr
	:	instanceOfExpr
	|	intersectExceptExpr INTERSECT instanceOfExpr
	|	intersectExceptExpr EXCEPT instanceOfExpr
	;

//  [16]   	InstanceofExpr	   ::=   	TreatExpr ( "instance" "of" SequenceType )?
instanceOfExpr
	:	treatExpr
	|	treatExpr INSTANCE OF sequenceType
	;

//  [17]   	TreatExpr	   ::=   	CastableExpr ( "treat" "as" SequenceType )?
treatExpr
	:	castableExpr
	|	castableExpr TREAT AS sequenceType
	;

//  [18]   	CastableExpr	   ::=   	CastExpr ( "castable" "as" SingleType )?
castableExpr
	:	castExpr
	|	castExpr CASTABLE AS singleType
	;

//  [19]   	CastExpr	   ::=   	UnaryExpr ( "cast" "as" SingleType )?
castExpr
	:	unaryExpr
	|	unaryExpr CAST AS singleType
	;

//  [20]   	UnaryExpr	   ::=   	("-" | "+")* ValueExpr
unaryExpr
	:	valueExpr
	|	MINUS unaryExpr
	|	PLUS unaryExpr
	;

//  [21]   	ValueExpr	   ::=   	PathExpr
valueExpr
	:	pathExpr
	;

//  [22]   	GeneralComp	   ::=   	"=" | "!=" | "<" | "<=" | ">" | ">="
generalComp
	:	EQUALS
	|	NOTEQUALS
	|	LESSTHAN
	|	LESSEQUAL
	|	GREATER
	|	GREATEREQUAL
	;
//  [23]   	ValueComp	   ::=   	"eq" | "ne" | "lt" | "le" | "gt" | "ge"
valueComp
	:	EQ
	|	NE
	|	LT
	|	LE
	|	GT
	|	GE
	;

//  [24]   	NodeComp	   ::=   	"is" | "<<" | ">>"
nodeComp
	:	IS
	|	LESS_LESS
	|	GREATER_GREATER
	;

//  [25]   	PathExpr	   ::=   	("/" RelativePathExpr?)
//                          | ("//" RelativePathExpr)
//                          | RelativePathExpr	/* xgs: leading-lone-slash */
pathExpr
	:	FORWARD_SLASH
	|	FORWARD_SLASH relativePathExpr
	|	FORWARD_SLASHSLASH relativePathExpr
	|	relativePathExpr
	;

//  [26]   	RelativePathExpr	   ::=   	StepExpr (("/" | "//") StepExpr)*
relativePathExpr
	:	stepExpr
	|	relativePathExpr FORWARD_SLASH stepExpr
	|	relativePathExpr FORWARD_SLASHSLASH stepExpr
	;

//  [27]   	StepExpr	   ::=   	FilterExpr | AxisStep
stepExpr
	:	axisStep
	|	filterExpr
	;

//  [28]   	AxisStep	   ::=   	(ReverseStep | ForwardStep) PredicateList
axisStep
	:	forwardStep predicateList
	|	reverseStep predicateList
	;

//  [29]   	ForwardStep	   ::=   	(ForwardAxis NodeTest) | AbbrevForwardStep
forwardStep
	:	forwardAxis nodeTest
	|	abbrevForwardStep
	;

//  [30]   	ForwardAxis	   ::=   	("child" "::")
//                          | ("descendant" "::")
//                          | ("attribute" "::")
//                          | ("self" "::")
//                          | ("descendant-or-self" "::")
//                          | ("following-sibling" "::")
//                          | ("following" "::")
//                          | ("namespace" "::")
forwardAxis
	:	CHILD COLONCOLON
	|	DESCENDANT COLONCOLON
	|	ATTRIBUTE COLONCOLON
	|	SELF COLONCOLON
	|	DESCENDANT_OR_SELF COLONCOLON
	|	FOLLOWING_SIBLING COLONCOLON
	|	FOLLOWING COLONCOLON
	|	NAMESPACE COLONCOLON
	;

//  [31]   	AbbrevForwardStep	   ::=   	"@"? NodeTest
abbrevForwardStep
	:	AT_SYM nodeTest
	|	nodeTest
	;

//  [32]   	ReverseStep	   ::=   	(ReverseAxis NodeTest) | AbbrevReverseStep
reverseStep
	:	reverseAxis nodeTest
	|	abbrevReverseStep
	;

//  [33]   	ReverseAxis	   ::=   	("parent" "::")
//                          | ("ancestor" "::")
//                          | ("preceding-sibling" "::")
//                          | ("preceding" "::")
//                          | ("ancestor-or-self" "::")
reverseAxis
	:	PARENT COLONCOLON
	|	ANCESTOR COLONCOLON
	|	PRECEDING_SIBLING COLONCOLON
	|	PRECEDING COLONCOLON
	|	ANCESTOR_OR_SELF COLONCOLON
	;

//  [34]   	AbbrevReverseStep	   ::=   	".."
abbrevReverseStep
	:	DOTDOT
	;

//  [35]   	NodeTest	   ::=   	KindTest | NameTest
nodeTest
	:	kindTest
	|	nameTest
	;

//  [36]   	NameTest	   ::=   	QName | Wildcard
nameTest
	:	qName
	|	wildcard
	;

//  [37]   	Wildcard	   ::=   	"*"
//                          | (NCName ":" "*")
//                          | ("*" ":" NCName)	/* ws: explicit */
wildcard
	:	STAR
	|	nCName COLON STAR
	|	STAR COLON nCName
	;

//  [38]   	FilterExpr	   ::=   	PrimaryExpr PredicateList
filterExpr
	:	primaryExpr predicateList
	;

//  [39]   	PredicateList	   ::=   	Predicate*
predicateList
	:	predicate*
	;

//  [40]   	Predicate	   ::=   	"[" Expr "]"
predicate
	:	LBRACKET expr RBRACKET
	;

//  [41]   	PrimaryExpr	   ::=   	Literal | VarRef | ParenthesizedExpr | ContextItemExpr | FunctionCall
primaryExpr
	:	literal
	|	varRef
	|	parenthesizedExpr
	|	contextItemExpr
	|	functionCall
	;

//  [42]   	Literal	   ::=   	NumericLiteral | StringLiteral
literal
	:	numericLiteral
	|	stringLiteral
	;

//  [43]   	NumericLiteral	   ::=   	IntegerLiteral | DecimalLiteral | DoubleLiteral
numericLiteral
	:	integerLiteral
	|	decimalLiteral
	|	doubleLiteral
	;

//  [44]   	VarRef	   ::=   	"$" VarName

varRef
	:	DOLLAR varName
	;

//  [45]   	VarName	   ::=   	QName
varName
    :	qName ;

//  [46]   	ParenthesizedExpr	   ::=   	"(" Expr? ")"
parenthesizedExpr
	:	LPAR RPAR
	|	LPAR expr RPAR
	;

//  [47]   	ContextItemExpr	   ::=   	"."
contextItemExpr
	:	DOT
	;

//  [48]   	FunctionCall	   ::=   	QName "(" (ExprSingle ("," ExprSingle)*)? ")"	/* xgs: reserved-function-names */
functionCall
	:	reservedFunctionNames LPAR RPAR
	|	reservedFunctionNames LPAR functionCallMiddle RPAR
	;

functionCallMiddle
    :	exprSingle
    |	functionCallMiddle COMMA exprSingle
    ;

//  [49]   	SingleType	   ::=   	AtomicType "?"?
singleType
	:	atomicType
	|	atomicType QUESTIONMARK
	;

//  [50]   	SequenceType	   ::=   	("empty-sequence" "(" ")")
//                              | (ItemType OccurrenceIndicator?)
sequenceType
	:	itemType
	|	itemType occurrenceIndicator
	|	EMPTY_SEQUENCE LPAR RPAR
	;

//  [51]   	OccurrenceIndicator	   ::=   	"?" | "*" | "+"	/* xgs: occurrence-indicators */
occurrenceIndicator
	:	QUESTIONMARK
	|	STAR
	|	PLUS
	;

//  [52]   	ItemType	   ::=   	KindTest | ("item" "(" ")") | AtomicType
itemType
	:	atomicType
	|	kindTest
	|	ITEM LPAR RPAR
	;

//  [53]   	AtomicType	   ::=   	QName
atomicType
	:	qName
	;

//  [54]   	KindTest	   ::=   	DocumentTest
//                          | ElementTest
//                          | AttributeTest
//                          | SchemaElementTest
//                          | SchemaAttributeTest
//                          | PITest
//                          | CommentTest
//                          | TextTest
//                          | AnyKindTest
kindTest
	:	documentTest
	|	elementTest
	|	attributeTest
	|	schemaElementTest
	|	schemaAttributeTest
	|	pITest
	|	commentTest
	|	textTest
	|	anyKindTest
	;

//  [55]   	AnyKindTest	   ::=   	"node" "(" ")"
anyKindTest
	:	NODE LPAR RPAR
	;

//  [56]   	DocumentTest	   ::=   	"document-node" "(" (ElementTest | SchemaElementTest)? ")"
documentTest
	:	DOCUMENT_NODE LPAR RPAR
	|	DOCUMENT_NODE LPAR elementTest RPAR
	|	DOCUMENT_NODE LPAR schemaElementTest RPAR
	;

//  [57]   	TextTest	   ::=   	"text" "(" ")"
textTest
	:	TEXT LPAR RPAR
	;

//  [58]   	CommentTest	   ::=   	"comment" "(" ")"
commentTest
	:	COMMENT LPAR RPAR
	;

//  [59]   	PITest	   ::=   	"processing-instruction" "(" (NCName | StringLiteral)? ")"
pITest
	:	PROCESSING_INSTRUCTION LPAR RPAR
	|	PROCESSING_INSTRUCTION LPAR nCName RPAR
	|	PROCESSING_INSTRUCTION LPAR stringLiteral RPAR
	;

//  [60]   	AttributeTest	   ::=   	"attribute" "(" (AttribNameOrWildcard ("," TypeName)?)? ")"
attributeTest
	:	ATTRIBUTE LPAR RPAR
	|	ATTRIBUTE LPAR attribNameOrWildcard RPAR
	|	ATTRIBUTE LPAR attribNameOrWildcard COMMA typeName RPAR
	;

//  [61]   	AttribNameOrWildcard	   ::=   	AttributeName | "*"
attribNameOrWildcard
	:	attributeName
	|	STAR
	;

//  [62]   	SchemaAttributeTest	   ::=   	"schema-attribute" "(" AttributeDeclaration ")"
schemaAttributeTest
	:	SCHEMA_ATTRIBUTE LPAR attributeDeclaration RPAR
	;

//  [63]   	AttributeDeclaration	   ::=   	AttributeName
attributeDeclaration
	:	attributeName
	;

//  [64]   	ElementTest	   ::=   	"element" "(" (ElementNameOrWildcard ("," TypeName "?"?)?)? ")"
elementTest
	:	ELEMENT LPAR RPAR
	|	ELEMENT LPAR elementNameOrWildcard RPAR
	|	ELEMENT LPAR elementNameOrWildcard COMMA typeName RPAR
	|	ELEMENT LPAR elementNameOrWildcard COMMA typeName QUESTIONMARK RPAR
	;

//  [65]   	ElementNameOrWildcard	   ::=   	ElementName | "*"
elementNameOrWildcard
	:	elementName
	|	STAR
	;

//  [66]   	SchemaElementTest	   ::=   	"schema-element" "(" ElementDeclaration ")"
schemaElementTest
	:	SCHEMA_ELEMENT LPAR elementDeclaration RPAR
	;
//  [67]   	ElementDeclaration	   ::=   	ElementName
elementDeclaration
	:	elementName
	;
//  [68]   	AttributeName	   ::=   	QName
attributeName
	:	qName
	;

//  [69]   	ElementName	   ::=   	QName
elementName
	:	qName
	;

//  [70]   	TypeName	   ::=   	QName
typeName
	:	qName
	;

//  [71]   	IntegerLiteral	   ::=   	Digits
integerLiteral
	:	DIGITS
	;

//  [72]   	DecimalLiteral	   ::=   	("." Digits) | (Digits "." [0-9]*)	/* ws: explicit */
decimalLiteral
	:	DECIMAL_LITERAL
	;

//[73]   	DoubleLiteral	   ::=   	(("." Digits) | (Digits ("." [0-9]*)?)) [eE] [+-]? Digits	/* ws: explicit */
doubleLiteral
	:	DOUBLE_LITERAL
	;

//[74]   	StringLiteral	   ::=   	('"' (EscapeQuot | [^"])* '"') | ("'" (EscapeApos | [^'])* "'")	/* ws: explicit */
stringLiteral
	:	STRING_LITERAL
	;

//[75]   	EscapeQuot	   ::=   	'""'
//[76]   	EscapeApos	   ::=   	"''"
//[77]   	Comment	   ::=   	"(:" (CommentContents | Comment)* ":)"	/* ws: explicit */
///* gn: comments */

//[78]   	QName	   ::=   	[http://www.w3.org/TR/REC-xml-names/#NT-QName]Names	/* xgs: xml-version */
     qName
     	:	nCName
     	|	nCName COLON nCName
     	;

//[79]   	NCName	   ::=   	[http://www.w3.org/TR/REC-xml-names/#NT-NCName]Names	/* xgs: xml-version */
nCName
	:	NCNAME
	|	TO
	|	CHILD
	|	DESCENDANT
	|	ATTRIBUTE
	|	SELF
	|	DESCENDANT_OR_SELF
	|	FOLLOWING_SIBLING
	|	FOLLOWING
	|	NAMESPACE
	|	PARENT
	|	ANCESTOR
	|	PRECEDING_SIBLING
	|	PRECEDING
	|	ANCESTOR_OR_SELF
	|	UNION
	|	INTERSECT
	|	EXCEPT
	|	INSTANCE
	|	OF
	|	AS
	|	TREAT
	|	CASTABLE
	|	CAST
	|	IS
	|	FOR
	|	IN
	|	RETURN
	|	SATISFIES
	|	SOME
	|	EVERY
	|	IF
	|	THEN
	|	ELSE
	|	AND
	|	OR
	|	DIV
	|	IDIV
	|	COMMENT
	|	DOCUMENT_NODE
	|	ELEMENT
	|	EMPTY_SEQUENCE
	|	ITEM
	|	NODE
	|	PROCESSING_INSTRUCTION
	|	SCHEMA_ATTRIBUTE
	|	SCHEMA_ELEMENT
	|	TEXT
	|	TYPESWITCH
	|	MOD
	|	EQ
	|	NE
	|	LT
	|	LE
	|	GT
	|	GE
	;

// extends rule 48
reservedFunctionNames
	:	reservedFunctionNCNames
	|	nCName COLON nCName
	;

reservedFunctionNCNames
    :	NCNAME
    |	TO
    |	CHILD
    |	DESCENDANT
    |	SELF
    |	DESCENDANT_OR_SELF
    |	FOLLOWING_SIBLING
    |	FOLLOWING
    |	NAMESPACE
    |	PARENT
    |	ANCESTOR
    |	PRECEDING_SIBLING
    |	PRECEDING
    |	ANCESTOR_OR_SELF
    |	UNION
    |	INTERSECT
    |	EXCEPT
    |	INSTANCE
    |	OF
    |	AS
    |	TREAT
    |	CASTABLE
    |	CAST
    |	IS
    |	FOR
    |	IN
    |	RETURN
    |	SATISFIES
    |	SOME
    |	EVERY
    |	THEN
    |	ELSE
    |	AND
    |	OR
    |	DIV
    |	IDIV
    |	MOD
    |	EQ
    |	NE
    |	LT
    |	LE
    |	GT
    |	GE
    ;

