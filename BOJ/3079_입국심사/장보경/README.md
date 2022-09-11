# 3079 입국심사

![캡처](https://user-images.githubusercontent.com/72604908/189539131-430fe47c-f83c-41c6-9836-0828810da806.PNG)

1. 입력을 받아 정렬한다.
2. max값을 최대시간*인원수로 지정한다.
3. mid=(min+max)/2로 하여, 심사대에서 몇명을 심사할 수 있는지 계산 후 sum에 합을 누적시킨다.
4. 심사인원이 총인원수보다 작으면 min=mid+1, 그 반대면 max=min-1
5. while문 다 돌면 최소시간 반환
