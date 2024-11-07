package buddy.common.constant.pool;

/**
 * 字符/字符串相关常量池
 *
 * @author anthony
 * @version 2024/11/07
 * @since 2023/10/19 13:56
 */
public interface CharSequencePool {
	char DOT = '.';
	char COLON = ':';
	char COMMA = ','; // 半角逗号
	char SEMICOLON = ';'; // 半角分号
	char SINGLE_QUOTE = '\'';
	char DOUBLE_QUOTE = '"';

	char LEFT_BRACKET = '(';
	char RIGHT_BRACKET = ')';
	char LEFT_BRACE = '{';
	char RIGHT_BRACE = '}';
	char LEFT_SQUARE_BRACKET = '[';
	char RIGHT_SQUARE_BRACKET = ']';
	char LEFT_ANGLE_BRACKET = '<';
	char RIGHT_ANGLE_BRACKET = '>';

	char SPACE = ' '; // 半角空格
	char TAB = '\t';
	char CR = '\r';
	char LF = '\n';
	char CR_LF = CR + LF;

	char SLASH = '/';
	char BACKSLASH = '\\';
	char DASHED_LINE = '-'; // 中划线
	char UNDERSCORE = '_'; // 下划线

	char QUESTION_MARK = '?';
	char EXCLAMATION_MARK = '!';  // 半角感叹号

	char AT = '@';
	char AMPERSAND = '&';
	char POUND = '#';
	char DOLLAR = '$';
	char PERCENT = '%';
	char CARET = '^';
	char ASTERISK = '*';

	char EQUAL = '=';
	char PLUS = '+';
	char MINUS = '-';
	char DIVIDE = '/';
}