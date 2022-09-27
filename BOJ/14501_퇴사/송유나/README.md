![image](https://user-images.githubusercontent.com/33195517/192412137-432267de-7260-4cd0-8188-c2de98ac28bd.png)

### 완전탐색

idx 날짜에 상담을 하면 idx+걸리는 날짜, pay+받는 돈

idx 날짜에 상담을 안하면 idx+1, pay

idx >= n 이면 return 하고, idx == n이면 answer를 갱신한다.
