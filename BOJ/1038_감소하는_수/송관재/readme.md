# 1038번: 감소하는 수

| 메모리 | 시간 |
| --- | --- |
| 14356 KB | 132ms |

백트래킹을 사용하는 문제, N이 10보다 작을때는 답이 N이 되기 때문에 예외처리로 두었고, depth+1이 현재 확인하는 수의 자릿수가 된다. 값을 함수로 계속 넘기면서 그 값보다 작은 값이 다음 자릿수가 되어야 조건을 만족하기 때문에 depth를 줄여가면서 값을 넘겼고, 현재 검사하는 값이 몇자리 수인지 확인하기 위해 start 값도 인자로 넘겨준다. 확인하는 수는 배열에 저장되고 인덱스는 depth를 사용한다.