![image](https://user-images.githubusercontent.com/33195517/183450735-138d53c2-0d71-4a6b-b354-7dadbd7aa01b.png)

1. findNum 함수는 num을 인자로 받는데, num * 10 + i (i=1 ... 9)가 소수이면 이를 다시 인자로 넘겨주면서 재귀를 한다.
2. 종료 조건은 start == n-1 +1(n: 자릿수) 이다.
3. 소수 찾기를 홀수부터 시작해서 i+=2를 하면 짝수는 검사하지 않아서 시간을 줄일 수 있다.
