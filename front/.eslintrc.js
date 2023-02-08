module.exports = {
  env: {
    browser: true,
    es2021: true,
  },
  extends: ['plugin:react/recommended', 'airbnb', 'prettier'],
  overrides: [],
  parserOptions: {
    ecmaVersion: 'latest',
    sourceType: 'module',
  },
  plugins: ['react', 'prettier'],
  rules: {
    'react/react-in-jsx-scope': 0, // import React 생략 가능
    'react/jsx-filename-extension': [1, { extensions: ['.js', '.jsx'] }], // js, jsx 모두 가능
    // 'prettier/prettier': 'error', // prettier의 포맷팅 이슈를 출력
    'react/button-has-type': 'off', // button 태그를 사용할 때 type 적어주지 않아도 괜찮음
    'react/prop-types': 'off', // props 사용할 떄 type 적지 않아도 괜찮음
    'prettier/prettier': [
      'error',
      { singleQuote: true, parser: 'flow', endOfLine: 'auto' },
    ], // 줄바꿈 에러해결 (정확하게 모르겠음)
    'no-shadow': 'off', // 같은 이름 사용하지 못하는 에러 off
    'no-unused-vars': ['warn'], // 사용 안한것 경고창 띄워줌
    'arrow-body-style': 0, // 화살표 함수 사용할 때 중괄호 {} 사용할 수 있음
    'no-console': 0, // console 오류 제거
    'react/self-closing-comp': ['error', { component: false, html: false }], // <div></div> or <div />인지 체크 false하면 둘다 사용가능
    'react/destructuring-assignment': [0, 'always'], // props 규정 무시하고 가능.
    'jsx-a11y/label-has-associated-control': [
      2,
      { labelAttributes: ['htmlFor'] },
    ], // <label 에러 해결>
    'import/prefer-default-export': ['off', { target: 'any' }], // recoil에서 atom default쓰기 위함
    'react/jsx-props-no-spreading': ['warn'], // props로 받은 것 바로 props로 넘기기 허용
    'import/no-extraneous-dependencies': [
      'error',
      {
        devDependencies: false,
        optionalDependencies: false,
        peerDependencies: false,
      },
    ],
  },
};
