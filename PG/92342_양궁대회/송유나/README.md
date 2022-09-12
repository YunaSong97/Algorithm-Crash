![image](https://user-images.githubusercontent.com/33195517/189420844-5600ecdd-cc61-4afd-a5a8-c8e985f27981.png)

**완전탐색**</br>
가지치기 : 어피치가 맞춘 개수보다 라이언이 맞춘 개수가 1크면 점수를 따기 때문에, 라이언 배열을 만들 때 어피치보다 같거나 작을 때만 수행한다.</br></br>
시간초과 : 점수 계산할 때, 둘이 점수가 같으면서 0이면 continue를 먼저 해줘야된다. 마지막에 else안에 같으면서 0인거 처리하니까 시간초과남</br>

**배열 복사**</br>
<li>
Arrays.clone() : 깊은 복사<br>
Arrays.copyOf() : 깊은 복사 & 마지막 인덱스 지정<br>
Arrays.copyOfRange() : 깊은 복사 & 시작, 마지막 인덱스 지정
</li>
