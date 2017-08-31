/*******************************************************************************
 Grammar based on https://www.w3.org/TR/xpath20/
 *******************************************************************************/
lexer grammar XPath2Lexer;


// rule [71]
fragment Digits
	:	[0-9]+
	;

// -> to remove : implicit definition of token
DIGITS : Digits;

// rule [72]
fragment DecimalLiteral
    :	'.' Digits
    |	Digits '.' [0-9]*
    ;

// -> to remove : implicit definition of token
DECIMAL_LITERAL : DecimalLiteral;

// rule [73]
fragment DoubleLiteral
	:	(	'.' Digits
		|	Digits ('.' [0-9]*)?
		)
		[eE] [+-]? Digits
	;

// -> to remove : implicit definition of token
DOUBLE_LITERAL : DoubleLiteral;

// rule [74]
fragment StringLiteral
    :	'"' ('""' | ~'"')* '"'
    |	'\'' ('\'\'' | ~'\'')* '\''
    ;

// -> to remove : implicit definition of token
STRING_LITERAL : StringLiteral;


// [80]   	Char	   ::=   	[http://www.w3.org/TR/REC-xml#NT-Char]XML ->
//      ->  Char	   ::=   	#x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
fragment
Char
    : '\u0009'
    | '\u000A'
    | '\u000D'
    | [\u0020-\uD7FF]
    | [\uE000-\uFFFD]
	;


fragment
NCNameChar
	:	Char
	|	[0-9]
	|	'.'
	|	'-'
	|	'_'
	;

// [79] NCName	   ::=   	[http://www.w3.org/TR/REC-xml-names/#NT-NCName]Names
//      -> NCName	   ::=   	Name - (Char* ':' Char*)	/* An XML Name, minus the ":" */
fragment
NCName
	:	(Char | '_') NCNameChar*
	;

LBRACKET	:	'[';
RBRACKET	:	']';
LPAR		:	'(';
RPAR		:	')';

PLUS		:	'+';
MINUS		:	'-';
STAR		:	'*';
PIPE		:	'|';

EQUALS		:	'=';
NOTEQUALS	:	'!=';
LESSTHAN	:	'<';
LESSEQUAL	:	'<=';
GREATER		:	'>';
GREATEREQUAL:	'>=';

LESS_LESS	:	'<<';
GREATER_GREATER	:	'>>';

FORWARD_SLASH	:	'/';
FORWARD_SLASHSLASH	:	'//';

COLONCOLON	:	'::';
AT_SYM		:	'@';
DOTDOT		:	'..';
COLON		:	':';
COMMA		:	',';
DOLLAR		:	'$';
DOT			:	'.';
QUESTIONMARK:	'?';

CHILD		:	'child';
DESCENDANT	:	'descendant';
ATTRIBUTE	:	'attribute';
SELF		:	'self';
DESCENDANT_OR_SELF	:	'descendant-or-self';

FOLLOWING_SIBLING	:	'following-sibling';
FOLLOWING	:	'following';
NAMESPACE	:	'namespace';
PARENT		:	'parent';

ANCESTOR	:	'ancestor';
PRECEDING_SIBLING	:	'preceding-sibling';
PRECEDING	:	'preceding';
ANCESTOR_OR_SELF	:	'ancestor-or-self';

EQ			:	'eq';
NE			:	'ne';
LT			:	'lt';
LE			:	'le';
GT			:	'gt';
GE			:	'ge';

IDIV		:	'idiv';
DIV			:	'div';
MOD			:	'mod';

UNION		:	'union';
INTERSECT	:	'intersect';
EXCEPT		:	'except';

INSTANCE	:	'instance';
TREAT		:	'treat';
CASTABLE	:	'castable';
CAST		:	'cast';
AS			:	'as';
OF			:	'of';
IS			:	'is';

FOR			:	'for';
IN			:	'in';
RETURN		:	'return';
SATISFIES	:	'satisfies';
TO			:	'to';
SOME		:	'some';
EVERY		:	'every';
IF			:	'if';
THEN		:	'then';
ELSE		:	'else';
AND			:	'and';
OR			:	'or';

EMPTY_SEQUENCE	:	'empty-sequence';
ITEM		:	'item';
NODE		:	'node';
DOCUMENT_NODE	:	'document-node';
TEXT		:	'text';
COMMENT		:	'comment';

PROCESSING_INSTRUCTION	:	'processing-instruction';
SCHEMA_ATTRIBUTE	:	'schema-attribute';
ELEMENT		:	'element';
SCHEMA_ELEMENT	:	'schema-element';
TYPESWITCH	:	'typeswitch';

STRING		:	StringLiteral;
INTEGER		:	Digits;
DOUBLE		:	DoubleLiteral;
DECIMAL		:	DecimalLiteral;
NCNAME		:	NCName;
