# 2023번: 신기한 소수

| 메모리 | 시간 |
| --- | --- |
| 17808 KB | 224ms |

맨 윗자리부터 한자리씩 늘린 모든 값이 소수가 되어야 하기 때문에 백트래킹으로 횟수를 줄임, 한자리 중 소수인 숫자는 2, 3, 5, 7이므로 이 숫자들 부터 시작을 하고 두자리일 경우 짝수일 경우 소수가 아니기 때문에 홀수만 소수 검정을 함.