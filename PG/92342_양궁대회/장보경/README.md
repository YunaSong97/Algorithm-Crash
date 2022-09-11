# 92342 양궁대회

![캡처1](https://user-images.githubusercontent.com/72604908/189539810-dbb96f75-8662-478d-a75e-0c19929416d5.PNG)
![캡처](https://user-images.githubusercontent.com/72604908/189539813-688ec2bd-6b04-409a-a4be-bccf59661810.PNG)

### # dfs()
1. 10점~0점까지 모두 탐색한다.
2. 남은 화살 수가 apeach가 쏜 과녁의 화살 수 보다 더 많고, 해당 과녁에서 lion이 쏜 화살 개수가 apeach보다 작거나 같다면 lion화살 수= apeach화살 수+1
3. 쏠 수 있는 화살 갯수가 apeach가 쏜 과녁의 화살 수 보다 작다면 다 과녁0에 몰빵
4. 만약 lion이 쏜 화살수==n 이라면 어피치와 라이언 점수 계산
5. apeach와 lion의 점수차가 max보다 크다면 max갱신 및 배열 복사, 점수차가 max와 같다면 낮은 점수 많은 것을 선택
