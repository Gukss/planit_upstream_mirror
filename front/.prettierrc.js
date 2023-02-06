module.exports = {
  singleQuote: true, // singleQuote 사용
  tabWidth: 2, // tapWidth 2
  useTabs: false, // space 사용
  semi: true, // 문장 끝 세미콜론 사용
  bracketSpacing: true, // 중괄호 내에 공백 사용
  arrowParens: 'avoid', // 화살표 함수 단일 인자 시, 괄호 생략 -> 단일인자 사용시 괄호 생략 안하면 eslint에서 에러가 뜸
  trailingComma: 'es5', // 꼬리 콤마 사용, 객체, 배열에선 , 추가, 함수 인자
  jsxSingleQuote: true, // jsx 파일에서 쌍따옴표(") 대신 홑따옴표(')를 사용하는지 여부
};
