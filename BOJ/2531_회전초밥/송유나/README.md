![image](https://user-images.githubusercontent.com/33195517/187631662-8b64ace2-b8a0-4288-8d7c-8df2379c653c.png)

1. k개 윈도우 만큼 오른쪽으로 가면서 맨 오른쪽 값이 HashMap에 있는 값인지 확인해 있으면 원래값+1, 없으면 1을 저장한다.
2. 이전 윈도우의 맨 첫번째 값은 이번 윈도우에 없으니까 HashMap의 값을 감소시킨다. 0이 되면 remove한다.
3. HashMap에 coupon값이 있으면 ans 값과 현재 HashMap size를 비교하고, coupon값이 없으면 현재 size + 1과 비교해 더 큰 값을 ans에 저장한다.
4. ans 값이 k+1값과 같아지면 최댓값이므로 출력한다.
